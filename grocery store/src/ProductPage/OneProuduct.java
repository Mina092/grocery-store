package ProductPage;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class OneProuduct {

    @FXML
    private Label price ;

    @FXML
    private Label prouductName;
    
    @FXML
    public Label rate ;
    
    @FXML
    private AnchorPane holePane ;
    
    public Label getPrice(){return price;}
    public Label getProductNumber(){return prouductName;}
    public Label getRate(){return rate;}
    public AnchorPane getHolePane(){return holePane;}

    public OneProuduct makeOneProuduct (String price, String name, double rate){
        this.price.setText(price);
        this.prouductName.setText(name);
        this.rate.setText(Double.toString(rate));

        return (this);
    }
}
