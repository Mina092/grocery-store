import java.io.IOException;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import javafx.stage.Stage;
import javafx.stage.Window;



public class InformationController {
    @FXML
    private static TextField addressText;

    @FXML
    private TextField commentText;

    @FXML
    private Button finishBut;

    @FXML
    private static TextField nameText;

    @FXML
    private static Button nextButt;

    @FXML
    private static TextField phoneText;

    @FXML
    private static TextField postalText;

    @FXML
    private static Label starAddress;

    @FXML
    private static Label starName;

    @FXML
    private static Label starPhone;

    @FXML
    private static Label starPost;

    @FXML
    private static Label uncompleteLabel;
     

    @FXML
    static void initialize(){
        
        starName.setVisible(false);
        starAddress.setVisible(false);
        starPhone.setVisible(false);
        starPost.setVisible(false);
        uncompleteLabel.setVisible(false);
        nextButt.setDisable(true);

    }

    static String name, address, phone, post;
    @FXML
    static void finishButton(ActionEvent event){
        name = nameText.getText();
        address = addressText.getText();
        phone = phoneText.getText();
        post = postalText.getText();
        
        System.out.println("HEYYY");
        
        // if(IsPayable(name, address, phone, post)) 
        //     nextButt.setDisable(false);
    }

    static boolean IsPayable(String name ,String address, String phone , String post ){
        boolean IsComplete = true ;
        if(name == null){
            starName.setVisible(true);
            IsComplete = false;
        }else starName.setVisible(false);

        if(address ==null){
            starAddress.setVisible(true);
            IsComplete = false;
        }else starAddress.setVisible(false);

        if(phone ==null){
            starPhone.setVisible(true);
            IsComplete = false;
        }else starPhone.setVisible(false);

        if(post ==null){
            starPost.setVisible(true);
            IsComplete = false;
        }else starPost.setVisible(false);

        if(!IsComplete) uncompleteLabel.setVisible(true);
        else  uncompleteLabel.setVisible(false);

        return IsComplete;
    }

    @FXML
    private Stage stage;
    // private Scene scene;
    // private Parent root;
    
    @FXML
    public void nextPage (ActionEvent event)throws IOException{

            try {
            Window window = addressText.getScene().getWindow();
            Stage thisStage = (Stage) addressText.getScene().getWindow();
            thisStage.hide();
            
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("payPage.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            
        //     stage.setScene(new Scene(root));  
        //     stage.show();
        //     // stage.getIcons().add(new Image("storeicon.png"));
        // }finally{
          
        // }
      }

    
}
// TODO: 
// save information