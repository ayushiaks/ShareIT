package frames;

import java.net.URL;
import java.sql.*;
import java.lang.String;
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


public class UserProfile implements Initializable{

    Connection conn = null;
    public UserProfile() {
        conn = util.Connect.connectdb();
    }

    @FXML
    private void handleButtonUserProfile(ActionEvent event) {

        try {
            String sql = "SELECT * FROM login";

            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                String name = rs.getString("name");

                System.out.printf("%s\n", name);
            }
            st.close();
        } catch (Exception e) {
            System.err.println("Exception");
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }
}
