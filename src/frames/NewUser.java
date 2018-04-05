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

public class NewUser implements Initializable{

    @FXML
    private TextField textName;

    @FXML
    private PasswordField textPassword;

    Stage dialogStage = new Stage();
    Scene scene;

    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    public NewUser() {
        connection = util.Connect.connectdb();
    }

    @FXML
    private void handleNewUser(ActionEvent event) {
        String name = textName.getText();
        String password = textPassword.getText();

        String sql = " insert into login (name, password)"
                + " values (?, ?)";

        try{
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, password);
            //resultSet = preparedStatement.executeQuery();
            preparedStatement.executeUpdate();
            System.out.println(resultSet);
            infoBox("Created New User", "Success", null);
            Node source = (Node) event.getSource();
            dialogStage = (Stage) source.getScene().getWindow();
            dialogStage.close();
            scene = new Scene(FXMLLoader.load(getClass().getResource("/frames/FXMLLogin.fxml")));
            dialogStage.setScene(scene);
            dialogStage.show();


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

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }
}