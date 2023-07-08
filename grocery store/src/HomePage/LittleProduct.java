package HomePage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class LittleProduct {

    @FXML
    private Label price ;

    @FXML
    private Label prouductName;
    
    @FXML
    public Label rate ;
    
    @FXML
    private AnchorPane wholePane ;
    
    @FXML
    private Label imageLable;

    @FXML
    private ImageView productImage;

    private String brand, category;

    public void setBrand(String brand) {this.brand = brand;}
    public void setCategory(String category) {this.category = category;}

    public String getBrand() {return this.brand;}
    public String getCategory() {return this.category;}

    public Label getPrice(){return price;}
    public Label getProductNumber(){return prouductName;}
    public Label getRate(){return rate;}
    public AnchorPane getWholePane(){return wholePane;}

    public LittleProduct makeOneLittleProduct (String price, String name, double rate , String brand , String category , String imagePath ) throws FileNotFoundException{
        setBrand(brand);
        setCategory(category);
        this.price.setText(price);
        this.prouductName.setText(name);
        this.rate.setText(Double.toString(rate));

        //set image
        imageLable.setVisible(false);
        InputStream stream = new FileInputStream(imagePath);
        Image image = new Image(stream);
        productImage.setImage(image);

        return (this);
    }

    public void openProductPage() throws NumberFormatException, IOException{

        FXMLLoader loader = new FXMLLoader(getClass().getResource("one product.fxml"));
                
        Parent root = loader.load();
        
        OneProductController controller = loader.getController();
        
        String imagePath = category;
        if (imagePath.contains("protein"))  imagePath = "protein foods";
        imagePath = "src/Resources/" +imagePath +".png";
 
        OneProductController product = controller.makeOneProduct(price.getText(), prouductName.getText(), Double.parseDouble(rate.getText()), category ,brand , imagePath);

        Stage productStage = new Stage();
        try {
            // FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("one product.fxml"));
            // Parent root1 = (Parent) fxmlLoader.load();
            productStage.setScene(new Scene(root));  
            // productStage.initStyle(StageStyle.UNDECORATED);
            productStage.show();

        }finally{
          
        }
        
    }
    
}
