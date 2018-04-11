package frames;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import java.awt.*;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.apache.felix.ipojo.annotations.Controller;
import javafx.scene.input.MouseEvent;

public class Menu implements Initializable {


    @FXML
    TreeView<String> tree;
    @FXML
    Pane sell_id;
    @FXML
    BorderPane main_border_pane;
    @FXML
    HBox center_hbox;
    Button b = new Button("button");
    @FXML
    public javafx.scene.control.Label gdg = new javafx.scene.control.Label();
    @FXML
    Button UserProfile;


    int iteration;
    private ObservableList<ObservableList> data;
    private TableView tableview;

        @FXML
        public void treeClick(MouseEvent e) {
            try {
                main_border_pane.setRight(null);

                TreeItem<String> item = tree.getSelectionModel().getSelectedItem();

                if (item.getValue() == "SELL") {

                    AnchorPane sellPane = FXMLLoader.load(getClass().getResource("FXMLSell.fxml"));
                    main_border_pane.setCenter(sellPane);
                }

                if (item.getValue() == "BUY") {
                    Connection c ;
                    javafx.event.ActionEvent event = new javafx.event.ActionEvent();


                    data = FXCollections.observableArrayList();
                    try{
                        c = util.Connect.connectdb();
                        String SQL = "SELECT * from products_sell";
                        ResultSet rs = c.createStatement().executeQuery(SQL);
                        //System.out.println("i = " + rs.getMetaData().getColumnCount());
                        if(iteration==0){
                            for(int i=0 ; i<rs.getMetaData().getColumnCount(); i++){
                                final int j = i;
                                TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i+1));
                                col.setCellValueFactory(new Callback<CellDataFeatures<ObservableList,String>,ObservableValue<String>>(){
                                    public ObservableValue<String> call(CellDataFeatures<ObservableList, String> param) {
                                        return new SimpleStringProperty(param.getValue().get(j).toString());
                                    }
                                });

                                tableview.getColumns().addAll(col);
                                System.out.println("Column ["+i+"] ");
                            }}

                        while (rs.next()) {
                            //Iterate Row
                            ObservableList<String> row = FXCollections.observableArrayList();
                            for (int k = 1; k <= rs.getMetaData().getColumnCount(); k++) {
                                //Iterate Column
                                row.add(rs.getString(k));
                            }
                            System.out.println("Row [1] added " + row);
                            data.add(row);

                        }

                        iteration = 1;

                        tableview.setItems(data);
                        main_border_pane.setCenter(tableview);


                    AnchorPane sellPane = FXMLLoader.load(getClass().getResource("FXMLBuy.fxml"));
                        main_border_pane.setRight(sellPane);
                    }
                    catch(Exception ex){
                        ex.printStackTrace();
                        System.out.println("Error on Building Data");
                    }

                }
                if (item.getValue() == "User Profile") {
                    AnchorPane sellPane = FXMLLoader.load(getClass().getResource("FXMLUserProfile.fxml"));
                       main_border_pane.setCenter(sellPane);

                }
                if (item.getValue() == "Notifications") {
                    AnchorPane sellPane = FXMLLoader.load(getClass().getResource("FXMLNotify.fxml"));
                    main_border_pane.setCenter(sellPane);
//                    main_border_pane.setRight();

                }
                if (item.getValue() == "RENT" ) {
                    Connection c ;
                    javafx.event.ActionEvent event = new javafx.event.ActionEvent();


                    data = FXCollections.observableArrayList();
                    try{

                        c = util.Connect.connectdb();
                        String SQL = "SELECT * from products_rent";
                        ResultSet rs = c.createStatement().executeQuery(SQL);
                        if (iteration == 0){
                            for(int i=0 ; i<rs.getMetaData().getColumnCount(); i++){
                                final int j = i;
                                TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i+1));
                                col.setCellValueFactory(new Callback<CellDataFeatures<ObservableList,String>,ObservableValue<String>>(){
                                    public ObservableValue<String> call(CellDataFeatures<ObservableList, String> param) {
                                        return new SimpleStringProperty(param.getValue().get(j).toString());
                                    }
                                });

                                tableview.getColumns().addAll(col);
                                System.out.println("Column ["+i+"] ");
                            }
                        }



                        while(rs.next()){
                            //Iterate Row
                            ObservableList<String> row = FXCollections.observableArrayList();
                            for(int k=1 ; k<=rs.getMetaData().getColumnCount(); k++){
                                //Iterate Column
                                row.add(rs.getString(k));
                            }
                            System.out.println("Row [1] added "+row );
                            data.add(row);

                        }

                        tableview.setItems(data);
                        main_border_pane.setCenter(tableview);
                        AnchorPane sellPane = FXMLLoader.load(getClass().getResource("FXMLRent.fxml"));
                        main_border_pane.setRight(sellPane);
                    }
                    catch(Exception ex){
                        ex.printStackTrace();
                        System.out.println("Error on Building Data");
                    }

                    iteration = 1;
                }
           } catch(IOException ex){ Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex); }
        }



    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tableview = new TableView();
        TreeItem<String> root = new TreeItem<String>("root");
        TreeItem<String> buy = new TreeItem<String>("BUY");
        TreeItem<String> sell = new TreeItem<String>("SELL");
        TreeItem<String> rent = new TreeItem<String>("RENT");
        TreeItem<String> user_profile = new TreeItem<String>("User Profile");
        TreeItem<String> notifs = new TreeItem<String>("Notifications");

        root.getChildren().addAll(buy, sell, rent, user_profile, notifs);

        try{
            AnchorPane sellPane = FXMLLoader.load(getClass().getResource("FXMLSell.fxml"));
            main_border_pane.setCenter(sellPane);
        }catch(IOException ex){
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }

        tree.setRoot(root);
        tree.setShowRoot(false);
        MultipleSelectionModel msm = tree.getSelectionModel();
        msm.select(sell);
    }
}