//package frames;
//
//import javafx.event.ActionEvent;
//import javafx.fxml.FXML;
//import javafx.fxml.FXMLLoader;
//import javafx.scene.Node;
//import javafx.scene.Scene;
//import javafx.stage.Stage;
//
//public class RentMenu {
//
//    @FXML
//    private void handlebuttonRent(ActionEvent event) {
//        try{
//            Node source = (Node) event.getSource();
//            dialogStage = (Stage) source.getScene().getWindow();
//            dialogStage.close();
//            scene = new Scene(FXMLLoader.load(getClass().getResource("/frames/FXMLNewUser.fxml")));
//            dialogStage.setScene(scene);
//            dialogStage.show();
//        }
//        catch(Exception e){
//            e.printStackTrace();
//        }
//    }
//}
