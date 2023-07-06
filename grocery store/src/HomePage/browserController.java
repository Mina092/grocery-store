package HomePage;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.*;
import java.io.IOException;

import javafx.fxml.FXMLLoader;



import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import LoginScreen.LoginPage;

import java.sql.Connection;


public class browserController {

    @FXML
    private Button categoryFilterB;
    
    @FXML
    private Button sortFilterB;
        
    @FXML
    private Button brandFilterB;
    @FXML
    private Slider slider;
    
    @FXML
    private ScrollPane scroller;
    
    @FXML
    public TabPane tabpane;
    public Tab logoutTab, browseTab;
    public Button logoutButton, backToBrowseButton;

    
    @FXML
    private GridPane gridPane ;

    @FXML
    public Text accUser, accPass;

    @FXML
    public void initialize(){
        accUser.setText(LoginPage.user);
        accPass.setText(LoginPage.pass);
    }

    boolean dairy = false , protein = false, snack = false, drink = false;
    boolean priceD = false , priceA = false,  rateD = false, rateA = false , newest = false ,oldest = false;
    boolean brand1 = false , brand2 = false , brand3 = false, brand4 = false;

    public void sort(String sortBy) throws IOException{

        //test
        // System.out.println("dairy" + dairy);
        // System.out.println("pro" + protein);
        // System.out.println("snack" + snack);
        // System.out.println("drink" + drink );


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

            String name, price, rate , category;
            
            for(int j=0 ; j< 8 ; j++){
                for(int k=0 ; k< 4; k++){
                    
                    // while(result.next()){
                        if(result.next()){
                        name =result.getString(1);
                        price = result.getString(2); 
                        rate = result.getString(3);
                        category = result.getString(5);
                        
                        int max = Integer.parseInt(result.getString(8));

                        //test
                        // System.out.println(name+" " + price+" " + rate);

                        
                        FXMLLoader loader = new FXMLLoader();
                        
                        loader.load(getClass().getResource("product.fxml").openStream());
                        OneProuduct controller = loader.getController();
                        
                        if (category.contains("protein"))  category = "protein foods";
                        category = "src/" +category +".png";
                        //test
                        // System.out.println(category);
                        // controller.makeOneProuduct(price , name, Double.parseDouble(rate) , max , category);
                            //test
                        // System.out.println(name + " " + j +" "+ k);
                        // GridPane.setRowIndex(controller.getWholePane(), j);
                        // GridPane.setColumnIndex(controller.getWholePane(), k);
                        
                        
                        // gridPane.getChildren().addAll(controller.getWholePane());
                    }
                    else{

                        for(  ; j< 8 ; j++){
                            if((k==4 && j==1)|| (k==4 && j==2) || (k==4 && j==3) || (k==4 && j==4) || (k==4 && j==5) || (k==4 && j==6)) k=0;
                            for( ; k< 4; k++){

                                // Node node = getNodeFromGridPane(gridPane, k, j) ;
                                // node = null;
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
            
            scroller.setContent(gridPane);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
   
   public void allSort() throws IOException{

    dairy = false ; protein = false; snack = false; drink = false;newest = false ; oldest = false;
    categoryFilterB.setText("all (newest)");
    
    String sortBy = null , brand = null;

    //sortBy
    if (priceD) sortBy = "price DESC";
    else if(priceA) sortBy = "price ASC";
    else if(rateA) sortBy = "rate ASC";
    else if(rateD) sortBy = "rate DESC";
    else if (newest) sortBy = "date DESC";
    else if (oldest) sortBy = "date ASC";

    //brand
    if (brand1) brand = "brand1";
    else if(brand2) brand = "brand2";
    else if(brand3) brand = "brand3";
    else if(brand4) brand = "brand4";
    
    if(brand != null && sortBy == null) sort("WHERE brand = '"+ brand +"' ordered by date DESC;");
    else if(brand == null && sortBy != null) sort(" ORDER BY " +sortBy+ ";");
    else if(brand == null && sortBy == null) sort(" ORDER BY date DESC;");
    else if(brand != null && sortBy != null) sort(" and brand = '" + brand +"' ORDER BY " +sortBy+ ";");
   }

   public void categorySort(String category) throws IOException{
    String sortBy = null , brand = null;

    //sortBy
    if (priceD) sortBy = "price DESC";
    else if(priceA) sortBy = "price ASC";
    else if(rateA) sortBy = "rate ASC";
    else if(rateD) sortBy = "rate DESC";
    else if (newest) sortBy = "date DESC";
    else if (oldest) sortBy = "date ASC";

    //brand
    if (brand1) brand = "brand1";
    else if(brand2) brand = "brand2";
    else if(brand3) brand = "brand3";
    else if(brand4) brand = "brand4";
    
    if(brand != null && sortBy == null) sort("WHERE category = '"+ category +"' and brand = '" + brand +"' ordered by date DESC;");
    else if(brand == null && sortBy != null) sort(" WHERE category = '" + category +"' ORDER BY " +sortBy+ ";");
    else if(brand == null && sortBy == null) sort(" WHERE category = '" + category +"' ORDER BY date DESC;");
    else if(brand != null && sortBy != null) sort(" WHERE category = '"+ category +"' and brand = '" + brand +"' ORDER BY " +sortBy+ ";");

   }
   
   public void dairySort() throws IOException{
    
    categoryFilterB.setText("dairy");

    dairy = true ; protein = false; snack = false; drink = false;

    categorySort("dairy");
   }


   public void proteinSort() throws IOException{
    
    categoryFilterB.setText("protein");
    dairy = false ; protein = true; snack = false; drink = false;
    categorySort("protein foods");
   }

   public void snackSort() throws IOException{
    
    categoryFilterB.setText("snacks");
    dairy = false ; protein = false; snack = true; drink = false;
    categorySort("snacks");
   }

   public void drinksSort() throws IOException{
    
    categoryFilterB.setText("drinks");
    dairy = false ; protein = false; snack = false; drink = true;
    categorySort("drinks");
   }



   public void sortBySort(String sortBy) throws IOException{
    String category = null , brand = null;

    if(dairy) category = "dairy";
    else if(protein) category = "protein foods";
    else if(drink) category = "drinks";
    else if(snack)category = "snacks";

    if (brand1) brand = "brand1";
    else if(brand2) brand = "brand2";
    else if(brand3) brand = "brand3";
    else if(brand4) brand = "brand4";

    
    if(brand != null && category == null) sort("WHERE brand = '"+ brand +"' ORDER BY " + sortBy+ ";");
    else if(brand == null && category != null) sort(" WHERE category = '" + category +"' ORDER BY " +sortBy+ ";");
    else if(brand == null && category == null) sort(" ORDER BY " +sortBy+ ";");
    else if(brand != null && category != null) sort(" WHERE category = '"+ category +"' and brand = '" + brand +"' ORDER BY " +sortBy+ ";");
   }

   public void priceDSort() throws IOException{
    sortFilterB.setText("price descending");
     priceD = true ; priceA = false;  rateD = false;  rateA = false; newest = false ; oldest = false;
     sortBySort("price DESC");

   }

   public void priceASort() throws IOException{
    
    sortFilterB.setText("price ascending");

    priceD = false ; priceA = true;  rateD = false;  rateA = false; newest = false ; oldest = false;
    
    sortBySort("price ASC");
   }
   
   public void rateASort() throws IOException{
    
    sortFilterB.setText("rate ascending");

    priceD = false ; priceA = false;  rateD = false;  rateA = true; newest = false ; oldest = false;

    sortBySort("rate ASC");
   }

   public void rateDSort() throws IOException{

    
    sortFilterB.setText("rate descending");

    priceD = false ; priceA = false;  rateD = true;  rateA = false; newest = false ; oldest = false;
    sortBySort("rate DESC");
   }

   public void newestFilter() throws IOException{
    
    sortFilterB.setText("Newest first");

    priceD = false ; priceA = false;  rateD = false;  rateA = false; newest = true ; oldest = false;

    sortBySort("date DESC");
   }

   public void oldestFilter() throws IOException{

    
    sortFilterB.setText("Oldest first");

    priceD = false ; priceA = false;  rateD = false;  rateA = false; newest = false ; oldest = true;
    
    sortBySort("date ASC");

   }
   

   public void noSortFilterBYSORT() throws IOException{
    
    priceD = false ; priceA = false;  rateD = false;  rateA = false ; newest = false ; oldest = false;
    sortFilterB.setText("no filter");
    sortBySort("date DESC");

   }

   
   public void Brand1() throws IOException{
    brand1 = true; brand2 = false; brand3 = false; brand4 = false;
    
    brandFilterB.setText("brand1");
    brandSort("brand1");
   }

   public void Brand2() throws IOException{
    brand1 = false; brand2 = true; brand3 = false; brand4 = false;
    
    brandFilterB.setText("brand2");
    brandSort("brand2");
   }
   public void Brand3() throws IOException{
    brand1 = false; brand2 = false; brand3 = true; brand4 = false;
    
    brandFilterB.setText("brand3");
    brandSort("brand3");
   }
   
   public void Brand4() throws IOException{
    brand1 = false; brand2 = false; brand3 = false; brand4 = true;
    
    brandFilterB.setText("brand4");
    brandSort("brand4");
   }

   public void noFilterBrand() throws IOException{
            
    brand1 = false ; brand2 = false;  brand3 = false;  brand4 = false ;

    String category = null , sortBy = null;

    if(dairy) category = "dairy";
    else if(protein) category = "protein foods";
    else if(drink) category = "drinks";
    else if(snack)category = "snacks";

    if(priceA) sortBy = "price ASC";
    else if(priceD) category = "price DESC";
    else if(rateA) category = "rate ASC";
    else if(rateD)category = "rate DESC";
    else if(newest) category = "date DESC";
    else if(oldest)category = "date ASC";
    
    if(category != null && sortBy == null) sort("WHERE category = '"+ category +"' ORDER BY date DESC;");
    else if(category == null && sortBy != null) sort(" ORDER BY " +sortBy+ ";");
    else if(category == null && sortBy == null) sort(" ORDER BY date DESC;");
    else if(category != null && sortBy != null) sort(" WHERE category = '"+ category +"' ORDER BY " +sortBy+ ";");

    brandFilterB.setText("no filter");
   }

   public void brandSort( String brand) throws IOException{
    String category = null , sortBy = null;

    if(dairy) category = "dairy";
    else if(protein) category = "protein foods";
    else if(drink) category = "drinks";
    else if(snack)category = "snacks";

    if(priceA) sortBy = "price ASC";
    else if(priceD) category = "price DESC";
    else if(rateA) category = "rate ASC";
    else if(rateD)category = "rate DESC";
    else if(newest) category = "date DESC";
    else if(oldest)category = "date ASC";
    
    if(category != null && sortBy == null) sort("WHERE category = '"+ category +"' and brand = '" + brand +"' ORDER BY date DESC;");
    else if(category == null && sortBy != null) sort(" WHERE brand = '" + brand +"' ORDER BY " +sortBy+ ";");
    else if(category == null && sortBy == null) sort(" WHERE brand = '" + brand +"' ORDER BY date DESC;");
    else if(category != null && sortBy != null) sort(" WHERE category = '"+ category +"' and brand = '" + brand +"' ORDER BY " +sortBy+ ";");
    
   }


    void loading() throws IOException{
        Stage loadingstage = new Stage();
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("loading.fxml"));
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


    // RangeSlider slider1 = new RangeSlider(0, 100, 10, 90);
    // //Setting the slider properties
    // slider.setShowTickLabels(true);
    // slider.setShowTickMarks(true);
    // slider.setMajorTickUnit(25);
    // slider.setBlockIncrement(10);
    // //VBox to arrange circle and the slider
    // VBox vbox = new VBox();
    // vbox.setPadding(new Insets(75));
    // vbox.setSpacing(150);
    // vbox.getChildren().addAll(slider);

    public void sliderController(){
        // Slider slider = new Slider(0, 500, 0);
        slider.setMin(0);
        slider.setValue(0);
        slider.setMax(500);

        slider.setShowTickLabels(true);
        slider.setShowTickMarks(true);
        slider.setMajorTickUnit(100);
        slider.setBlockIncrement(50);
        //Setting the width of the slider
        slider.setMaxWidth(300);
    }

    public void backToBrowse(){
        //ClickSound.sound();
        tabpane.getSelectionModel().select(browseTab);
    }


    public void logout(){
        //ClickSound.sound();
        try {
            loading();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        LoginPage.stage.close();
        //open login window
    }
}