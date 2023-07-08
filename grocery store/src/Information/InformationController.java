package Information;

import java.io.IOException;

import HomePage.browserController;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import javafx.stage.Stage;
import javafx.stage.Window;

public class InformationController {
    @FXML
    private TextField addressText;

    @FXML
    private TextField commentText;

    @FXML
    private Button finishBut;

    @FXML
    private TextField nameText;

    @FXML
    private Button nextButt;

    @FXML
    private TextField phoneText;

    @FXML
    private TextField postalText;

    @FXML
    private Label starAddress;

    @FXML
    private Label starName;

    @FXML
    private Label starPhone;

    @FXML
    private Label starPost;

    @FXML
    private Label uncompleteLabel;

    @FXML
    void initialize() {

        starName.setVisible(false);
        starAddress.setVisible(false);
        starPhone.setVisible(false);
        starPost.setVisible(false);
        uncompleteLabel.setVisible(false);
        nextButt.setDisable(true);

    }

    static String name, address, phone, post;

    @FXML
    public void finishButton() {
        name = nameText.getText();
        address = addressText.getText();
        phone = phoneText.getText();
        post = postalText.getText();

        if (IsPayable(name, address, phone, post))
            nextButt.setDisable(false);
    }

    public boolean IsPayable(String name, String address, String phone, String post) {
        boolean IsComplete = true;
        if (name == null) {
            starName.setVisible(true);
            IsComplete = false;
        } else
            starName.setVisible(false);

        if (address == null) {
            starAddress.setVisible(true);
            IsComplete = false;
        } else
            starAddress.setVisible(false);

        if (phone == null) {
            starPhone.setVisible(true);
            IsComplete = false;
        } else
            starPhone.setVisible(false);

        if (post == null) {
            starPost.setVisible(true);
            IsComplete = false;
        } else
            starPost.setVisible(false);

        if (!IsComplete)
            uncompleteLabel.setVisible(true);
        else
            uncompleteLabel.setVisible(false);

        return IsComplete;
    }

    @FXML
    private Stage stage;
    // private Scene scene;
    // private Parent root;

    @FXML
    public void nextPage(ActionEvent event) throws IOException {

        try {
            Parent root = FXMLLoader.load(getClass().getResource("/ProductPage/payPage.fxml"));
            browserController.cartStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            browserController.scene = new Scene(root);
            browserController.cartStage.setScene(browserController.scene);
            browserController.cartStage.show();

            // Window window = addressText.getScene().getWindow();
            // Stage thisStage = (Stage) addressText.getScene().getWindow();
            // thisStage.hide();

            // FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("payPage.fxml"));
            // Parent root = (Parent) fxmlLoader.load();

            // stage.setScene(new Scene(root));
            // stage.show();
            // // stage.getIcons().add(new Image("storeicon.png"));
        } finally {

        }
    }
}
// TODO:
// save information