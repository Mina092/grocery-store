package ProductPage;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import LoginScreen.*;
import Sounds.*;

public class ProductsPage{


    // void loading() throws IOException{
    //     // Stage loadingstage = new Stage();

    //     // loadingPopup.show(LoginPage.stage);
    //     try {
    //         // FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("loading.fxml"));
    //         // Parent root1 = (Parent) fxmlLoader.load();
            
    //         Parent root2 = FXMLLoader.load(getClass().getClassLoader().getResource("LoginScreen/loading.fxml"));
    //         loadingstage.setScene(new Scene(root2));  
    //         loadingstage.initStyle(StageStyle.UNDECORATED);
    //         loadingstage.show();

    //     }finally{
          
    //     }
    //     try {
    //         TimeUnit.SECONDS.sleep(8);
    //     } catch (InterruptedException e) {
    //         // TODO Auto-generated catch block
    //         e.printStackTrace();
    //     }
    //     loadingPopup.hide();
    //     // loadingstage.close();
    // }


    public TabPane tabpane;
    public Tab logoutTab, browseTab;
    public Button logoutButton, backToBrowseButton;


    public void backToBrowse(){
        ClickSound.sound();
        tabpane.getSelectionModel().select(browseTab);
    }
    public void logout(){
        ClickSound.sound();
        // try {
        //     // loading();
        // } catch (IOException e) {
        //     // TODO Auto-generated catch block
        //     e.printStackTrace();
        // }
        LoginPage.stage.close();
        // open login window
    }
}