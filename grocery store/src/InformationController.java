import java.io.IOException;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import javafx.stage.Stage;
import javafx.stage.Window;



public class InformationController {
    @FXML
    private  TextField addressText;

    @FXML
    private TextField commentText;

    @FXML
    private Button finishBut;

    @FXML
    private  TextField nameText;

    @FXML
    private  Button nextButt;

    @FXML
    private  TextField phoneText;

    @FXML
    private  TextField postalText;

    @FXML
    private  Label starAddress;

    @FXML
    private  Label starName;

    @FXML
    private  Label starPhone;

    @FXML
    private  Label starPost;

    @FXML
    private  Label uncompleteLabel;
     

    @FXML
    public void initialize(){
        
        starName.setVisible(false);
        starAddress.setVisible(false);
        starPhone.setVisible(false);
        starPost.setVisible(false);
        uncompleteLabel.setVisible(false);
        nextButt.setDisable(true);

    }

     String name, address, phone, post;
    @FXML
     public void finishButton(){
        name = nameText.getText();
        address = addressText.getText();
        phone = phoneText.getText();
        post = postalText.getText();
        
        
        if(IsPayable(name, address, phone, post)) 
            nextButt.setDisable(false);
        else nextButt.setDisable(true);

    }

    public boolean IsPayable(String name ,String address, String phone , String post ){
        boolean IsComplete = true ;
        int swName =1, swAddress =1, swPhone =1 , swPost =1;

        if(name == null || name == ""){
            starName.setVisible(true);
            swName = 0;
            
        }else {
            starName.setVisible(false);
            swName = 1;
        }

        if(address ==null || address == ""){
            starAddress.setVisible(true);
            swAddress = 0;

        }else {
            starAddress.setVisible(false);
            swAddress = 1;
        }

        if(phone ==null || phone == ""){
            starPhone.setVisible(true);
            swPhone =0;
        }else{ 
            starPhone.setVisible(false);
            swPhone = 1;
        }

        if(post ==null|| post == ""){
            starPost.setVisible(true);
            swPost = 0;
        }else {
            starPost.setVisible(false);
            swPost = 1;
        }
        

        if(swAddress ==1 && swName==1 && swPhone==1 && swPost==1) IsComplete = true;
        else IsComplete = false;

        if(!IsComplete){
            uncompleteLabel.setVisible(true);
        } 
        else {
            uncompleteLabel.setVisible(false);
        } 

        return IsComplete;
    }

    @FXML
    private Stage stage = new Stage();
    
    @FXML
    public void nextPage (ActionEvent event)throws IOException{

            try {
            Window window = addressText.getScene().getWindow();
            Stage thisStage = (Stage) addressText.getScene().getWindow();
            thisStage.hide();
            
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("payPage.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            
            stage.setScene(new Scene(root));  
            stage.show();
            // stage.getIcons().add(new Image("storeicon.png"));
        }finally{
          
        }
      }

    
}
// TODO: 
// save information