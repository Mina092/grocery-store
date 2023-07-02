package HomePage;

import javafx.fxml.FXML;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.*;

import java.io.IOException;

import javafx.fxml.FXMLLoader;



import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.sql.Connection;

import ProductPage.OneProuduct;

public class browserController {
    @FXML


    public TabPane tabpane;
    public Tab logoutTab, browseTab;
    public Button logoutButton, backToBrowseButton;

    
    @FXML
    private GridPane gridPane ;

    boolean diary = false , protein = false, snack = false, drink = false;
    boolean priceD = false , priceA = false,  rateD = false, rateA = false;
    public void sort(String sortBy) throws IOException{

        System.out.println("diary" + diary);
        System.out.println("pro" + protein);
        System.out.println("snack" + snack);
        System.out.println("drink" + drink );


        String username = "root";
        String password = "gh991067m";
        String url = "jdbc:mysql://localhost:3306/groceryStore";
        String query = "SELECT * FROM products " + sortBy ;


        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        }catch(ClassNotFoundException e){
            e.printStackTrace();
        }
        try {
            Connection con = DriverManager.getConnection(url, username, password);
            Statement statement = con.createStatement();

            ResultSet result = statement.executeQuery(query);

            String name, price, rate;
            
            for(int j=0 ; j< 2 ; j++){
                for(int k=0 ; k< 4; k++){
                    
                    // while(result.next()){
                        if(result.next()){
                        name =result.getString(1);
                        price = result.getString(2); 
                        rate = result.getString(3); 

                        // System.out.println(name+" " + price+" " + rate);


                        FXMLLoader loader = new FXMLLoader();
                        
                        Parent root = (Parent) loader.load(getClass().getResource("/ProductPage/product.fxml").openStream());
                        OneProuduct controller = loader.getController();
                        
                        controller.makeOneProuduct(price , name, Double.parseDouble(rate));
                            //test
                        // System.out.println(name + " " + j +" "+ k);
                        GridPane.setRowIndex(controller.getHolePane(), j);
                        GridPane.setColumnIndex(controller.getHolePane(), k);


                        gridPane.getChildren().addAll(controller.getHolePane());
                    }
                    else{

                        for(  ; j< 2 ; j++){
                            if(k==4 && j==1) k=0;
                            for( ; k< 4; k++){
                                AnchorPane pane = new AnchorPane(); 
                                pane.setStyle("-fx-background-color : #fcfcfc;");
                                GridPane.setRowIndex(pane, j);
                                GridPane.setColumnIndex(pane, k);
                                gridPane.getChildren().addAll(pane);
                            }
                        }

                        break;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
   
   public void allSort() throws IOException{
    //to do:sort form date
    sort("");
   }
   
   public void diarySort() throws IOException{

    diary = true ; protein = false; snack = false; drink = false;
    if (priceD) sort("WHERE category = 'diary' ORDER BY price DESC;");
    else if(priceA) sort("WHERE category = 'diary' ORDER BY price ASC;");
    //todo
    else if(rateA) sort("WHERE category = 'diary' ORDER BY rate ASC;");
    else if(rateD) sort("WHERE category = 'diary' ORDER BY rate DESC;");
    else sort("WHERE category = 'diary';");
   }

   public void proteinSort() throws IOException{
    diary = false ; protein = true; snack = false; drink = false;
    if (priceD) sort("WHERE category = 'protein foods' ORDER BY price DESC;");
    else if(priceA) sort("WHERE category = 'protein foods' ORDER BY price ASC;");
    else if(rateA) sort("WHERE category = 'protein foods' ORDER BY rate ASC;");
    else if(rateD) sort("WHERE category = 'protein foods' ORDER BY rate DESC;");
    else sort("WHERE category = 'protein foods';");
   }

   public void snackSort() throws IOException{
    diary = false ; protein = false; snack = true; drink = false;
    if (priceD) sort("WHERE category = 'snacks' ORDER BY price DESC;");
    else if(priceA) sort("WHERE category = 'snacks' ORDER BY price ASC;");
    else if(rateA) sort("WHERE category = 'snacks' ORDER BY rate ASC;");
    else if(rateD) sort("WHERE category = 'snacks' ORDER BY rate DESC;");
    else sort("WHERE category = 'snacks';");
   }

   public void drinksSort() throws IOException{
    diary = false ; protein = false; snack = false; drink = true;
    if (priceD) sort("WHERE category = 'drinks' ORDER BY price DESC;");
    else if(priceA) sort("WHERE category = 'drinks' ORDER BY price ASC;");
    else if(rateA) sort("WHERE category = 'drinks' ORDER BY rate ASC;");
    else if(rateD) sort("WHERE category = 'drinks' ORDER BY rate DESC;");
    else sort("WHERE category = 'drinks';");
   }

   public void priceDSort() throws IOException{
     priceD = true ; priceA = false;  rateD = false;  rateA = false;

    if (diary) sort("WHERE category = 'diary' ORDER BY price DESC;");
    else if(protein) sort("WHERE category = 'protein foods' ORDER BY price DESC;");
    else if(snack) sort("WHERE category = 'snacks' ORDER BY price DESC;");
    else if(drink) sort("WHERE category = 'drinks'ORDER BY price DESC;");

    else sort("ORDER BY price DESC;");

   }

   public void priceASort() throws IOException{

    priceD = false ; priceA = true;  rateD = false;  rateA = false;

    if (diary) sort("WHERE category = 'diary' ORDER BY price ASC;");
    else if(protein) sort("WHERE category = 'protein foods' ORDER BY price ASC;");
    else if(snack) sort("WHERE category = 'snack' ORDER BY price ASC;");
    else if(drink) sort("WHERE category = 'drinks' ORDER BY price ASC;");
    else sort("ORDER BY price ASC;");

   }
   
   public void rateASort() throws IOException{

    priceD = false ; priceA = false;  rateD = false;  rateA = true;

    if (diary) sort("WHERE category = 'diary' ORDER BY rate ASC;");
    else if(protein) sort("WHERE category = 'protein foods' ORDER BY rate ASC;");
    else if(snack) sort("WHERE category = 'snack' ORDER BY rate ASC;");
    else if(drink) sort("WHERE category = 'drinks' ORDER BY rate ASC;");
    else sort("ORDER BY rate ASC;");

   }

   public void rateDSort() throws IOException{

    priceD = false ; priceA = false;  rateD = true;  rateA = false;

    if (diary) sort("WHERE category = 'diary' ORDER BY rate DESC;");
    else if(protein) sort("WHERE category = 'protein foods' ORDER BY rate DESC;");
    else if(snack) sort("WHERE category = 'snack' ORDER BY rate DESC;");
    else if(drink) sort("WHERE category = 'drinks' ORDER BY rate DESC;");
    else sort("ORDER BY rate DESC;");

   }
   

    void loading() throws IOException{
        Stage loadingstage = new Stage();
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/loading.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            loadingstage.setScene(new Scene(root1));  
            loadingstage.initStyle(StageStyle.UNDECORATED);
            loadingstage.show();

        }finally{
          
        }
        
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
         loadingstage.close();
    }



    public void backToBrowse(){
        // ClickSound.sound();
        tabpane.getSelectionModel().select(browseTab);
    }


    public void logout(){
        // ClickSound.sound();
        // try {
        //     loading();
        // } catch (IOException e) {
        //     // TODO Auto-generated catch block
        //     e.printStackTrace();
        // }
        // LoginPage.stage.close();
        //open login window
    }
}