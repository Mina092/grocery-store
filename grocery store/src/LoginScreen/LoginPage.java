package LoginScreen;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.BufferedWriter;
import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;
import java.util.zip.CRC32C;

import sounds.*;
public class LoginPage{
    public static String user, pass;
    public RadioButton customerRadioButton;
    public RadioButton storeRadioButton;
    public RadioButton adminRadioButton;
    public TextField usernameText;
    public TextField captchaText;
    public PasswordField passwordText;
    public Hyperlink noAccountLink;
    public Hyperlink back;
    public Button loginbutton;
    public Button signupButton;
    public ToggleGroup toggle1;
    public Text roleWarning;
    public Text wrongUserWarning;
    public Text captchaWarning;
    public BorderPane pane;
    @FXML
    public ImageView cap1;
    public ImageView cap2;
    public ImageView cap3;
    
    public void generatePic(int num){
      if(num==1){
          cap1.setVisible(true);
      }
      if(num==2){
        cap2.setVisible(true);
      }
      if(num==3){
        cap3.setVisible(true);
      }
    }

    @FXML
    public void initialize(){
      Random x= new Random();
      int rand = x.nextInt(1,4);
      generatePic(rand);
    }

    int checkCaptcha(){
      int sw=0;
      String answer= captchaText.getText();
      if(cap1.isVisible()){
        if(answer.equals("W93BX")){
          sw=1;
        }
      }
      if(cap2.isVisible()){
        if(answer.equals("RBSKW")){
          sw=1;
        }
      }
      if(cap3.isVisible()){
        if(answer.equals("TSMS9")){
          sw=1;
        }
      }
      if(sw==0) return 0;
      else return 1;
    }
    public static Stage stage = new Stage();
    void stage2customer() throws IOException{
          try {
          
        //   FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("LoginScreen/Demo.fxml"));
        //   Parent root1 = (Parent) fxmlLoader.load();

          Parent root1 = FXMLLoader.load(getClass().getResource("/HomePage/homePage.fxml"));
          stage.setScene(new Scene(root1));  
          stage.show();
          stage.getIcons().add(new Image("/Resources/storeicon.png"));
      }finally{
        
      }
    }
    void stage2seller() throws IOException{
        try {
        
      //   FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("LoginScreen/Demo.fxml"));
      //   Parent root1 = (Parent) fxmlLoader.load();

        Parent root1 = FXMLLoader.load(getClass().getResource("/HomePage/homePage Seller.fxml"));
        stage.setScene(new Scene(root1));  
        stage.show();
        stage.getIcons().add(new Image("/Resources/storeicon.png"));
    }finally{
      
    }
  }
  void stage2admin() throws IOException{
      try {
          
        //   FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("LoginScreen/Demo.fxml"));
        //   Parent root1 = (Parent) fxmlLoader.load();

          Parent root1 = FXMLLoader.load(getClass().getResource("/AdminPage/adminPage.fxml"));
          stage.setScene(new Scene(root1));  
          stage.show();
          stage.getIcons().add(new Image("/Resources/storeicon.png"));
      }finally{
        
      }
  }
    void signupCustomer(String user, String pass){
      try {
        BufferedWriter output = new BufferedWriter(new FileWriter("src/LoginScreen/customers.txt", true));
        output.write(user+" "+pass+"\n");
        System.out.println("Successfully wrote to the file.");
        output.close();
      } catch (IOException e) {
        System.out.println("An error occurred.");
        e.printStackTrace();
      }
    }
    void signupAdmin(String user, String pass){
      try {
        BufferedWriter output = new BufferedWriter(new FileWriter("src/LoginScreen/admins.txt", true));
        output.write(user+" "+pass+"\n");
        System.out.println("Successfully wrote to the file.");
        output.close();
      } catch (IOException e) {
        System.out.println("An error occurred.");
        e.printStackTrace();
      }
    }
    void signupStore(String user, String pass){
      try {
        BufferedWriter output = new BufferedWriter(new FileWriter("src/LoginScreen/stores.txt", true));
        output.write(user+" "+pass+"\n");
        System.out.println("Successfully wrote to the file.");
        output.close();
      } catch (IOException e) {
        System.out.println("An error occurred.");
        e.printStackTrace();
      }
    }


    boolean checkUserPassCustomer(String user, String pass){
        try {
            File myObj = new File("src/LoginScreen/customers.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
              String user1 = myReader.next();
              String pass1= myReader.next();
              if(user1.equals(user) && pass1.equals(pass)){
                return true;
              }
            }
            myReader.close();
          } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
          }
        
        return false;

    }

    boolean checkUserPassAdmin(String user, String pass){
        try {
            File myObj = new File("src/LoginScreen/admins.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
              String user1 = myReader.next();
              String pass1= myReader.next();
              if(user1.equals(user) && pass1.equals(pass)){
                return true;
              }
            }
            myReader.close();
          } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
          }
        
        return false;

    }

    boolean checkUserPassStore(String user, String pass){
        try {
            File myObj = new File("src/LoginScreen/stores.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
              String user1 = myReader.next();
              String pass1= myReader.next();
              if(user1.equals(user) && pass1.equals(pass)){
                return true;
              }
            }
            myReader.close();
          } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
          }
        
        return false;

    }


    public void clickLogin(){
        ClickSound.sound();
        roleWarning.setVisible(false);
        wrongUserWarning.setVisible(false);
        if(checkCaptcha()==0){
          captchaWarning.setVisible(true);
        }
        if(toggle1.getSelectedToggle()==null){
            roleWarning.setVisible(true);
        }
        if(checkCaptcha()==1 && toggle1.getSelectedToggle()!=null){
            user= usernameText.getText();
             pass= passwordText.getText();
            RadioButton selectedRadioButton = (RadioButton) toggle1.getSelectedToggle();
            String toggleGroupValue = selectedRadioButton.getText();

            int sw=0; 
            if( toggleGroupValue.equals("customer") && checkUserPassCustomer(user,pass)){
                System.out.print("yes whale"); 
                //close login window
                try {
                    stage2customer();
                } catch (IOException e) {
                  // TODO Auto-generated catch block
                  e.printStackTrace();
                }
                sw=1;
            }
            if( toggleGroupValue.equals("admin") && checkUserPassAdmin(user,pass)){
                System.out.print("yes whale");
                //close login window
                try {
                  stage2admin();
                } catch (IOException e) {
                  // TODO Auto-generated catch block
                  e.printStackTrace();
                }
                sw=1;
            }
            if( toggleGroupValue.equals("store / company") && checkUserPassStore(user,pass)){
                System.out.print("yes whale");
                //close login window
                try {
                  stage2seller();
                } catch (IOException e) {
                  // TODO Auto-generated catch block
                  e.printStackTrace();
                }
                sw=1;
            }

            if(sw==0){
                wrongUserWarning.setVisible(true);
            }
        }
    }


    public void clickSignup() throws IOException{
    
      ClickSound.sound();
      roleWarning.setVisible(false);
      wrongUserWarning.setVisible(false);
      if(toggle1.getSelectedToggle()==null){
          roleWarning.setVisible(true);
      }
      else{
          String user= usernameText.getText();
          String pass= passwordText.getText();
          RadioButton selectedRadioButton = (RadioButton) toggle1.getSelectedToggle();
          String toggleGroupValue = selectedRadioButton.getText();
          if(usernameText.getText()!=null && passwordText.getText()!=null){
            if( toggleGroupValue.equals("customer")){
              signupCustomer(user, pass);
            }
            if( toggleGroupValue.equals("admin")){
                signupAdmin(user, pass);
            }
            if( toggleGroupValue.equals("store / company")){
                signupStore(user, pass);
            }
            back();
          }
 
      }

  }



    public void noAccount(){
        passwordText.setText(null);
        usernameText.setText(null);
        noAccountLink.setVisible(false);
        loginbutton.setVisible(false);
        signupButton.setVisible(true);
        back.setVisible(true);
        roleWarning.setVisible(false);
        wrongUserWarning.setVisible(false);
        captchaWarning.setVisible(false);
        captchaText.setVisible(false);
        cap1.setVisible(false);
        cap2.setVisible(false);
        cap3.setVisible(false);
    }
    public void back(){
        passwordText.setText(null);
        usernameText.setText(null);
        noAccountLink.setVisible(true);
        loginbutton.setVisible(true);
        signupButton.setVisible(false);
        back.setVisible(false);
        roleWarning.setVisible(false);
        wrongUserWarning.setVisible(false);
        captchaText.setVisible(true);
        initialize();
    }
}