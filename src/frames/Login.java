package frames;

import java.net.URL;
import java.sql.Connection;
import java.lang.String;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Login implements Initializable {

    @FXML
    private TextField textName;

    @FXML
    private PasswordField textPassword;

    Stage dialogStage = new Stage();
    Scene scene;

    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    public Login() {
        connection = util.Connect.connectdb();
    }

    @FXML
    private void handleButtonAction(ActionEvent event) {
        String name = textName.getText();
        String password = textPassword.getText();

        String sql = "SELECT * FROM login WHERE name = ? and password = ?";


        try{
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, password);
            resultSet = preparedStatement.executeQuery();
            System.out.println(resultSet);
            if(!resultSet.next()){
                infoBox("Enter Correct Name and Password", "Failed", null);
            }else{
                infoBox("Login Successfull", "Success", null);
                Node source = (Node) event.getSource();
                dialogStage = (Stage) source.getScene().getWindow();
                dialogStage.close();
                scene = new Scene(FXMLLoader.load(getClass().getResource("/frames/FXMLMenu.fxml")));
                dialogStage.setScene(scene);
                dialogStage.show();
            }

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public static void infoBox(String infoMessage, String titleBar, String headerMessage)
    {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(titleBar);
        alert.setHeaderText(headerMessage);
        alert.setContentText(infoMessage);
        alert.showAndWait();
    }

    @FXML
    private void handlebuttonNewUSer(ActionEvent event) {
        try{
            Node source = (Node) event.getSource();
            dialogStage = (Stage) source.getScene().getWindow();
            dialogStage.close();
            scene = new Scene(FXMLLoader.load(getClass().getResource("/frames/FXMLNewUser.fxml")));
            dialogStage.setScene(scene);
            dialogStage.show();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }


        @Override
    public void initialize(URL url, ResourceBundle rb) {

    }
}
























