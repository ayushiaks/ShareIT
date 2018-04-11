package frames;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.MultipleSelectionModel;
import javafx.scene.control.TreeView;
import javafx.stage.Stage;

import javax.annotation.Resource;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class Buy implements Initializable {
    @FXML
    private ComboBox comboBox;
    @FXML
    TreeView<String> tree;

    Stage dialogStage = new Stage();
    Scene scene;

        Connection connection = null;
    PreparedStatement preparedStatement = null;
    PreparedStatement prst = null;
    PreparedStatement prst1 = null;
    ResultSet rst1 = null;
    ResultSet resultSet = null;
    ResultSet rst = null;
    public static String print = "";
    public static String seller = Login.me;


    public Buy() {
        connection = util.Connect.connectdb();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        String items[] = new String[55];
        int id = 0;
        String sql = "SELECT id FROM products_sell";
        try {
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            for(int i=0; i<55; i++) {
                while (resultSet.next()) {

                    comboBox.getItems().add(resultSet.getInt("id"));
                }
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    @FXML
    private void handleBuy(ActionEvent event){
        int item = Integer.parseInt(comboBox.getSelectionModel().getSelectedItem().toString());
        System.out.println(item);
        String sql1 = "SELECT * FROM products_sell WHERE id = " + item;
        try{
            preparedStatement = connection.prepareStatement(sql1);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                print = "" + resultSet.getString("pname");
                seller = "" + resultSet.getString("name");
                String sql2 = "DELETE FROM products_sell WHERE id = " + item  ;
                String sql3 = "insert into buy (name, item)" + " values (?, ?)";
                try{
                    prst = connection.prepareStatement(sql2);
                    prst1 = connection.prepareStatement(sql3);
                    prst1.setString(1, seller);
                    prst1.setString(2, print);
                    prst1.executeUpdate();

                    // = prst.executeQuery();
                    int result = prst.executeUpdate();
                    System.out.println("result is "+ result);
                   /* if (result !=0){
                       private void handleUpdate(ActionEvent event){

                        }
                    }*/
                infoBox("Product " + print + " Purchased", "Success", null);
                }
                catch (Exception e){
                    e.printStackTrace();
                }//catch(SQLException e){}


            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public static void infoBox(String infoMessage, String titleBar, String headerMessage)
    {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titleBar);
        alert.setHeaderText(headerMessage);
        alert.setContentText(infoMessage);
        alert.showAndWait();
    }

}
