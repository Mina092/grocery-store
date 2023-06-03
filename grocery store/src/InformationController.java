
import javax.naming.NameNotFoundException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class InformationController {

    @FXML
    private static TextField nameText;
    private static TextField addressText;
    private static TextField phoneText;
    private static TextField postalText;

    private static TextField commentText;
    private static TextField discountText;

    private static Button checkDiscount;
    private static Button payBut;
    private static Button finishBut;

    private static Label starAddress;
    private static Label starName;
    private static Label starPhone;
    private static Label starPost;
    private static Label uncompleteLabel;

    private static String name;
    private static String address;
    private static String phone;
    private static String post; 


    // static void Informatin(){
        
    //     starName.setVisible(true);
    //     starAddress.setVisible(true);
    //     starPhone.setVisible(true);
    //     starPost.setVisible(true);
    //     uncompleteLabel.setVisible(true);
    //     payBut.setDisable(true);

    //     name = nameText.getText();
    //     address = addressText.getText();
    //     phone = phoneText.getText();
    //     post = postalText.getText();

    // }

    static void finishButton(ActionEvent event){
        name = nameText.getText();
        address = addressText.getText();
        phone = phoneText.getText();
        post = postalText.getText();
        
        if(IsPayable(name, address, phone, post)) 
            payBut.setDisable(false);
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
}
// TODO: link to pay page
// save information
// check discount code
// 