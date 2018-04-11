package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.TreeView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Controller implements Initializable{
    @FXML
    TreeView <String> tree;
    @FXML
    Pane sell_id;
    @FXML
    BorderPane main_border_pane;
    @FXML
    HBox center_hbox;

    Button b = new Button("button");
    //Pane root = FXMLLoader.load(getClass().getResource("Foo.fxml"));

   // VBox sell_box = new VBox

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        TreeItem<String> root = new TreeItem<String>("root");
        TreeItem<String> buy = new TreeItem<String>("BUY");
        TreeItem<String> sell = new TreeItem<String>("SELL");
        TreeItem<String> rent = new TreeItem<String>("RENT");

        root.getChildren().addAll(buy, sell, rent);
        tree.setRoot(root);
        tree.setShowRoot(false);
    }
    @FXML
    public void treeClick(MouseEvent e) {
        try {
            TreeItem<String> item = tree.getSelectionModel().getSelectedItem();

            if (item.getValue() == "SELL") {
                AnchorPane sellPane = FXMLLoader.load(getClass().getResource("sell.fxml"));
                main_border_pane.setCenter(sellPane);
            }
            if (item.getValue() == "BUY") {
                AnchorPane sellPane = FXMLLoader.load(getClass().getResource("buy.fxml"));
                main_border_pane.setCenter(sellPane);
            }
            if (item.getValue() == "RENT") {
                AnchorPane sellPane = FXMLLoader.load(getClass().getResource("rent.fxml"));
                main_border_pane.setCenter(sellPane);
            }
        } catch(IOException ex){ Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex); }
    }
}
