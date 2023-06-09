import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;


public class App extends Application {
    public static void main(String[] args) throws Exception {
        launch(args);
    } 
    
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("LoginScreen/LoginPage.fxml"));
        Scene scene = new Scene(root);
        // Scene scene = new Scene(root, 600, 400);
        // primaryStage.setTitle("Login Page");
        // primaryStage.getIcons().add(new Image("loginicon.png"));
        // primaryStage.setResizable(false);
        primaryStage.setScene(scene);


        primaryStage.show();
        
    }
}