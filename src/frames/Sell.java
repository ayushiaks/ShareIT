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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Sell implements Initializable {

    @FXML
    private TextField textPName;

//    @FXML
//    private TextField textCategory;

    @FXML
    private TextField textRating;

    @FXML
    private TextArea textDetails;

    @FXML
    private TextField textCost;

    Stage dialogStage = new Stage();
    Scene scene;

    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    public Sell() {
        connection = util.Connect.connectdb();
    }

    @FXML
    private void handleSell(ActionEvent event) {
        String pname = textPName.getText();
        String rating = textRating.getText();
        String details = textDetails.getText();
        String cost = textCost.getText();

        String sql = " insert into products (pname, rating, cost, details)" + " values (?, ?, ?, ?)";

        try{
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, pname);
            preparedStatement.setString(2, rating);
            preparedStatement.setString(3, cost);
            preparedStatement.setString(4, details);
           //resultSet = preparedStatement.executeQuery();
            preparedStatement.executeUpdate();
            System.out.println(resultSet);
            infoBox("Product Added", "Success", null);
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
























