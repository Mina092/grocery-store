import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class PayPageController {

    @FXML
    private Button checkDiscountButt;

    @FXML
    private TextField discountText;

    @FXML
    private Button payBut;

    @FXML
    private Text totalPay;
//TO DO:
// CHECK DISCOUNT 
//GO TO PAY
//CHANGE TOTAL PAY
    public void checkDiscount(){
        
       String dicount =  discountText.getText();
       
        System.out.println("HEYYY");
    }
}