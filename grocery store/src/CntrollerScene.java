
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;


public class CntrollerScene {

    @FXML
    private Circle myCircle;
    private double x;
    private double y;
    
    private Stage stage;
    private Scene scene;
    private Parent root;

    public void switchToSceneOne(ActionEvent event) throws IOException{
        root = FXMLLoader.load(getClass().getResource("circle.fxml"));
        stage= (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        
    }
    public void switchToSceneTwo(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("test.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();  
    }




    public void UP(ActionEvent e){
        myCircle.setCenterY(y-=9);
        System.out.println("UP");
        
    }
    public void DOWN(ActionEvent e){
        myCircle.setCenterY(y+=9);
        System.out.println("DOWN");
    }
    public void LEFT(ActionEvent e){
        myCircle.setCenterX(x-=9);
        System.out.println("LEFT");
    }
    public void RIGHT(ActionEvent e){
        myCircle.setCenterX(x+=9);
        System.out.println("RIGHT");
    }


    @FXML
    private TextField ageTxt;

    @FXML
    private TextField conpanytxt;

    @FXML
    private TextField nameTxt;
    
    @FXML
    void Add(ActionEvent event){

        String url = "jdbc:mysql://localhost/MyFirstConnection";
        String user ="root";
        String password = "gh991067m";

        Connection conn =null;
        Statement pst = null;

        String name = nameTxt.getText();
        String age = ageTxt.getText();
        String company = conpanytxt.getText();

        try {
            Class.forName("com.mysql.jdbc.Drivers");
            conn = DriverManager.getConnection(url,user,password);

            pst = conn.createStatement();

            String sql = "INSERT INTO `new_schema`.`new_table`" +  "(`id`, `username`, `password`)" + "VALUES ('9', age, company)";

        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }



    
}



