package frames;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class Notifs implements Initializable
{
    @FXML
    public javafx.scene.control.Label sell = new javafx.scene.control.Label();
    @FXML
    public javafx.scene.control.Label buy = new javafx.scene.control.Label();
    @FXML
    public javafx.scene.control.Label rent = new javafx.scene.control.Label();
    @FXML
    public Label lend = new Label();
    String fin = "";
    Connection conn = null;
    public Notifs(){
        conn = util.Connect.connectdb();
    }
    PreparedStatement preparedStatement = null;
    PreparedStatement preparedStatement1 = null;
    PreparedStatement preparedStatement2 = null;
    PreparedStatement preparedStatement3 = null;
    PreparedStatement preparedStatement4 = null;
    ResultSet rst2 = null;
    ResultSet rst = null;
    ResultSet rst1 = null;
    ResultSet rst3 = null;
    ResultSet rst4 = null;



    @Override
    public void initialize(URL url, ResourceBundle rb) {
        String items[] = new String[10];
        String fin = "";
        String fin1 = "";
        String fin2 = "";
        String fin3 = "";
        String sql = "SELECT * FROM login WHERE id = " + Login.id;
        String sql1 = "SELECT * FROM products_sell WHERE name = '" + Login.me + "'";
        String sql3 = "SELECT * FROM buy WHERE name = '" + Login.me + "'";
        String sql4 = "SELECT * FROM rent WHERE name = '" + Login.me + "'";
        String sql5 = "SELECT * FROM products_rent WHERE name = '" + Login.me + "'";
        System.out.println(Login.me);
        try {
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement1 = conn.prepareStatement(sql1);
            preparedStatement2 = conn.prepareStatement(sql3);
            preparedStatement3 = conn.prepareStatement(sql4);
            preparedStatement4 = conn.prepareStatement(sql5);
            rst = preparedStatement.executeQuery();
            rst1 = preparedStatement1.executeQuery();
            rst2 = preparedStatement2.executeQuery();
            rst3 = preparedStatement3.executeQuery();
            rst4 = preparedStatement4.executeQuery();


            for (int i = 0; i < 10; i++) {
                while (rst1.next()) {
                    items[i] = rst1.getString("pname") + "\n";
                    fin = fin + items[i];
                }
            }
            for (int i = 0; i < 10; i++) {
                while (rst2.next()) {
                    items[i] = rst2.getString("item") + "\n";
                    fin1 = fin1 + items[i];
                }

            }
            for (int i = 0; i < 10; i++) {
                while (rst3.next()) {
                    items[i] = rst3.getString("item") + "\n";
                    fin2 = fin2 + items[i];
                }
            }
                for (int i = 0; i < 10; i++) {
                    while (rst4.next()) {
                        items[i] = rst4.getString("pname") + "\n";
                        fin3 = fin3 + items[i];
                    }
                    sell.setText(fin);
                    buy.setText(fin1);
                    rent.setText(fin2);
                    lend.setText(fin3);


                }

        }
        catch (Exception e){
            e.printStackTrace();
        }

    }
}
