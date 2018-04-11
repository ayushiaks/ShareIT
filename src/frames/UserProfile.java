package frames;

import javafx.fxml.Initializable;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import java.awt.*;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.apache.felix.ipojo.annotations.Controller;
import javafx.scene.input.MouseEvent;

public class UserProfile implements Initializable {
    @FXML
    public javafx.scene.control.Label name = new javafx.scene.control.Label();
    @FXML
    public javafx.scene.control.Label hostel = new javafx.scene.control.Label();
    @FXML
    public javafx.scene.control.Label contact = new javafx.scene.control.Label();


    Connection conn = null;
    public UserProfile(){
        conn = util.Connect.connectdb();
    }
    PreparedStatement preparedStatement = null;
    PreparedStatement preparedStatement1 = null;
    PreparedStatement preparedStatement2 = null;
    PreparedStatement preparedStatement3 = null;
    ResultSet rst2 = null;
    ResultSet rst = null;
    ResultSet rst1 = null;
    ResultSet rst3 = null;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //int me = 0;
        //int me = Login.id;
        String items[] = new String[10];
        String fin = "";
        String fin1 = "";
        String fin2 = "";
        String sql = "SELECT * FROM login WHERE id = " + Login.id;
        String sql1 = "SELECT * FROM products_sell WHERE name = '" + Login.me + "'";
        String sql3 = "SELECT * FROM buy";
        String sql4 = "SELECT * FROM rent";
        System.out.println(Login.me);
        try {
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement1 = conn.prepareStatement(sql1);
            preparedStatement2 = conn.prepareStatement(sql3);
            preparedStatement3 = conn.prepareStatement(sql4);
            rst = preparedStatement.executeQuery();
            rst1 = preparedStatement1.executeQuery();
            rst2 = preparedStatement2.executeQuery();
            rst3 = preparedStatement3.executeQuery();

            while (rst.next()) {
                name.setText("" + rst.getString("name"));
                hostel.setText("" + rst.getString("hostel"));
                contact.setText("" + rst.getString("contact"));
                //sell.setText("" + rst1.getString("pname"));
            }




        }
        catch (Exception e){
            e.printStackTrace();
        }



    }
}
