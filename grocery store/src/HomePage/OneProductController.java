package HomePage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class OneProductController {


    @FXML
    private Label brand;

    @FXML
    private Label category;

    @FXML
    private ImageView image;

    @FXML
    private Label price;

    @FXML
    private Label productName;

    @FXML
    private Label rate;

    
    // public Label getPrice(){return price;}
    // public Label getProductNumber(){return prouductName;}
    // public Label getRate(){return rate;}

    public OneProductController makeOneProduct (String price, String name, double rate , String category, String brand, String imagePath) throws FileNotFoundException{
        this.price.setText(price);
        this.productName.setText(name);
        this.rate.setText(Double.toString(rate));
        this.category.setText(category);
        this.brand.setText(brand);

        //set image
        InputStream stream = new FileInputStream(imagePath);
        Image imageTemp = new Image(stream);
        image.setImage(imageTemp);

        return (this);
    }

}

