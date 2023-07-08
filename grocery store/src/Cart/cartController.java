package Cart;

import java.io.IOException;

import org.controlsfx.control.action.Action;

import HomePage.browserController;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Node;
public class cartController {

    public void showInformation(ActionEvent event) throws IOException{

        Parent root = FXMLLoader.load(getClass().getResource("/Information/Information.fxml"));
        browserController.cartStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        browserController.scene = new Scene(root);
        browserController.cartStage.setScene(browserController.scene);
        browserController.cartStage.show();

    }
}
