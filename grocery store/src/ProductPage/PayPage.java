package ProductPage;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class PayPage {
    @FXML
    private Button payBut;

    public static Stage bankStage = new Stage();

    public void clickPayBut(){
        try{
            Parent root1 = FXMLLoader.load(getClass().getResource("/BankPortal/BankPortal.fxml"));
            bankStage.setScene(new Scene(root1));  
            bankStage.show();
            bankStage.getIcons().add(new Image("/Resources/bank.png"));
        }catch(IOException ignore){
        }
        finally{
            
        }
    }
}
