package HomePage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class OneProuduct {

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

    @FXML
    private Spinner<Integer> spinner;
    
    public Label getPrice(){return price;}
    public Label getProductNumber(){return prouductName;}
    public Label getRate(){return rate;}
    public AnchorPane getWholePane(){return wholePane;}

    public OneProuduct makeOneProuduct (String price, String name, double rate , int max , String imagePath) throws FileNotFoundException{
        this.price.setText(price);
        this.prouductName.setText(name);
        this.rate.setText(Double.toString(rate));

        spinner.setValueFactory(
            new SpinnerValueFactory.IntegerSpinnerValueFactory(0, max)
        );
        //set image
        imageLable.setVisible(false);
        InputStream stream = new FileInputStream(imagePath);
        Image image = new Image(stream);
        productImage.setImage(image);

        return (this);
    }
    
}
