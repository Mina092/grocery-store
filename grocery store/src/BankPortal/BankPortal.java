package BankPortal;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.function.UnaryOperator;
import java.util.regex.Pattern;
<<<<<<< HEAD
=======

import ProductPage.PayPage;
>>>>>>> add-folders
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;

import java.util.Properties;

public class BankPortal {
    @FXML
    private TextField cardText;
    @FXML
    private TextField cVV2Text;
    @FXML
    private TextField monthText;
    @FXML
    private TextField yearText;
    @FXML
    private PasswordField passwordField;
    @FXML
    private TextField emailField;
    @FXML
    private Button confirm;
    @FXML
    private Button cancel;
    @FXML
    private Label cardNumberLabel;
    @FXML
    private Label cvv2NumberLabel;
    @FXML
    private Label expireDateLabel;
    
    private Socket socket = null;
    private DataInputStream input = null;
    private DataOutputStream out = null;

    @FXML
    public void initialize() {
        setCardChangeListener(cardText, 19, cVV2Text);
        setMaxLength(cVV2Text, 4, monthText);
        setMonthChangeListener(monthText, 2, yearText);
        setMaxLength(yearText, 2, passwordField);
        setMaxLength(passwordField, 8, emailField);
        setMaxLength(emailField, 50, null);
        setTextRegex(cardText, Pattern.compile("[0-9 ]+", Pattern.CASE_INSENSITIVE));
        setTextRegex(cVV2Text, Pattern.compile("[0-9]+", Pattern.CASE_INSENSITIVE));
        setTextRegex(monthText, Pattern.compile("[0-9]+", Pattern.CASE_INSENSITIVE));
        setTextRegex(yearText, Pattern.compile("[0-9]+", Pattern.CASE_INSENSITIVE));
        setTextRegex(passwordField, Pattern.compile("[0-9]+", Pattern.CASE_INSENSITIVE));
    }

    private void setCardChangeListener(TextField textField, int maxLength, TextField nextTextField) {
        textField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue.length() > maxLength) {
                    textField.setText(oldValue);
                }
                if (newValue.length() == maxLength) {
                    if (nextTextField != null) {
                        nextTextField.requestFocus();
                    }
                }
                newValue = newValue.replace(" ", "");

                if (newValue.length() > 13) {
                    String firstPart = newValue.substring(0, 4);
                    String secondPart = newValue.substring(4, 8);
                    String thirdPart = newValue.substring(8, 12);
                    String fourthPart = newValue.substring(12);
                    textField.setText(firstPart + " " + secondPart + " " + thirdPart + " " + fourthPart);
                } else if (newValue.length() == 13) {
                    String firstPart = newValue.substring(0, 4);
                    String secondPart = newValue.substring(4, 8);
                    String thirdPart = newValue.substring(8, 12);
                    textField.setText(firstPart + " " + secondPart + " " + thirdPart + " " + newValue.charAt(12));
                } else if (newValue.length() > 9) {
                    String firstPart = newValue.substring(0, 4);
                    String secondPart = newValue.substring(4, 8);
                    String thirdPart = newValue.substring(8);
                    textField.setText(firstPart + " " + secondPart + " " + thirdPart);
                } else if (newValue.length() == 9) {
                    String firstPart = newValue.substring(0, 4);
                    String secondPart = newValue.substring(4, 8);
                    textField.setText(firstPart + " " + secondPart + " " + newValue.charAt(8));
                } else if (newValue.length() > 5) {
                    String firstPart = newValue.substring(0, 4);
                    String secondPart = newValue.substring(4);
                    textField.setText(firstPart + " " + secondPart);
                } else if (newValue.length() == 5) {
                    String firstPart = newValue.substring(0, 4);
                    textField.setText(firstPart + " " + newValue.charAt(4));
                }
                cardNumberLabel.setText(textField.getText());
            }
        });
    }

    private void setMonthChangeListener(TextField textField, int maxLength, TextField nextTextField) {
        textField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue.length() > maxLength) {
                    textField.setText(oldValue);
                }
                if (newValue.length() == maxLength) {
                    if (nextTextField != null) {
                        nextTextField.requestFocus();
                    }
                }
                if (Integer.parseInt(newValue) > 12) {
                    textField.setBorder(new Border(new BorderStroke(Color.RED, BorderStrokeStyle.SOLID,
                            CornerRadii.EMPTY, BorderWidths.DEFAULT)));
                }else{
                    textField.setBorder(null);
                }
                expireDateLabel.setText(yearText.getText()+"/"+monthText.getText());
            }
        });
    }

    private void setMaxLength(TextField textField, int maxLength, TextField nextTextField) {
        textField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue.length() > maxLength) {
                    textField.setText(oldValue);
                }
                if (newValue.length() == maxLength) {
                    if (nextTextField != null) {
                        nextTextField.requestFocus();
                    }
                }
                if (textField==cVV2Text){
                    cvv2NumberLabel.setText(newValue);
                }else if (textField==yearText){
                    expireDateLabel.setText(yearText.getText()+"/"+monthText.getText());
                }
            }
        });
    }

    private void setTextRegex(TextField textField, Pattern pattern) {

        UnaryOperator<TextFormatter.Change> filter = change -> {
            String newText = change.getControlNewText();
            if (pattern.matcher(newText).matches()) {
                return change;
            } else {
                return null;
            }
        };
        TextFormatter<String> textFormatter = new TextFormatter<>(filter);
        textField.setTextFormatter(textFormatter);
    }

    public void clickConfirm() {
        if (cardText.getText().length() == 19) {
            if (cVV2Text.getText().length() > 2) {
                if (monthText.getText().length() == 2 && yearText.getText().length() == 2) {
                    if (passwordField.getText().length() == 8) {
                        Pattern pattern = Pattern.compile("^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$");
                        if (pattern.matcher(emailField.getText()).matches()) {
                            int order;
                            try {
                                socket = new Socket("127.0.0.1", 5000);
                                System.out.println("Connected");

                                input = new DataInputStream(socket.getInputStream());
                                out = new DataOutputStream(socket.getOutputStream());

                            } catch (UnknownHostException u) {
                                System.out.println(u);
                            } catch (IOException exception) {
                                System.out.println(exception);
                            }
                            String line = "Email:" + emailField.getText();
                            try {
                                out.writeUTF(line);
                                order = input.readInt();
<<<<<<< HEAD
=======
                                if (order==1){
                                    PayPage.bankStage.close();
                                }
>>>>>>> add-folders
                            } catch (IOException i) {
                                System.out.println(i);
                            }
                        }else{
                            emailField.setBorder(new Border(new BorderStroke(Color.RED, BorderStrokeStyle.SOLID,
                            CornerRadii.EMPTY, BorderWidths.DEFAULT)));
                        }
                    }else{
                        passwordField.setBorder(new Border(new BorderStroke(Color.RED, BorderStrokeStyle.SOLID,
                            CornerRadii.EMPTY, BorderWidths.DEFAULT)));
                    }
                }else{
                    yearText.setBorder(new Border(new BorderStroke(Color.RED, BorderStrokeStyle.SOLID,
                            CornerRadii.EMPTY, BorderWidths.DEFAULT)));
                }
            }else{
                cVV2Text.setBorder(new Border(new BorderStroke(Color.RED, BorderStrokeStyle.SOLID,
                            CornerRadii.EMPTY, BorderWidths.DEFAULT)));
            }
        }else{
            cardText.setBorder(new Border(new BorderStroke(Color.RED, BorderStrokeStyle.SOLID,
                            CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        }
    }

    public void clickCancel(){
<<<<<<< HEAD

=======
        PayPage.bankStage.close();
>>>>>>> add-folders
    }
}
