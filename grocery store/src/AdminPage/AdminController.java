package AdminPage;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

import LoginScreen.LoginPage;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Popup;

public class AdminController implements Initializable {
    @FXML
    private ImageView financialReport;
    @FXML
    private ImageView inventoryManagment;
    @FXML
    private ImageView chat;
    @FXML
    private AnchorPane financialReportPane;
    @FXML
    private AnchorPane inventoryManagmentPane;
    @FXML
    private AnchorPane messengerPane;
    @FXML
    private PieChart pieChart;
    @FXML
    private TilePane sellersChat;
    @FXML
    private AnchorPane chatPane;
    @FXML
    private Button addStorehouse;
    @FXML
    private Button editStoreHouse;
    @FXML
    private Button deleteStoreHouse;

    TableView<Inventory> inventoryTable;
    Popup addPopup;

    private int sellersCount;
    private ImageView[] profileImages;
    private AnchorPane[] chatPanes;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setSellersCount();
        setInventoryTable();
        setInventory();
        setPopup();
        setMenuImagesEventHandler(financialReport);
        setMenuImagesEventHandler(inventoryManagment);
        setMenuImagesEventHandler(chat);
        setPieChart();
        setSellersChat();
    }

    private void setMenuImagesEventHandler(ImageView imageView) {
        imageView.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                if (event.getSource() == financialReport) {
                    financialReportPane.toFront();
                } else if (event.getSource() == inventoryManagment) {
                    inventoryManagmentPane.toFront();
                } else if (event.getSource() == chat) {
                    messengerPane.toFront();
                }
            }
        });
    }

    private void setPieChart() {
        String username = "root";
        String password = "Rezam14369";
        String url = "jdbc:mysql://localhost:3306/groceryStore";
        double expenses = 0, incomes = 0;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            Connection con = DriverManager.getConnection(url, username, password);

            String query = "select * from bank_accounts";
            Statement statement = con.createStatement();
            ResultSet result = statement.executeQuery(query);
            while (result.next()) {
                double amount = result.getInt("amount");
                if (amount > 0) {
                    incomes += amount;
                }
                if (amount < 0) {
                    expenses -= amount;
                }
            }
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
                new PieChart.Data("Expenses", expenses),
                new PieChart.Data("Incomes", incomes));
        // for (PieChart.Data data : pieChartData) {
        //     System.out.println(data.getName());
        //     if (data.getName().equals("Expenses"))
        //         data.setStyle("-fx-pie-color: red");
        // }
        pieChartData.forEach(data -> data.nameProperty().bind(
            Bindings.concat(
                data.getName(), " amount: ", data.pieValueProperty())));
                pieChart.getData().addAll(pieChartData);
                pieChartData.get(0).getNode().setStyle("-fx-pie-color: red");
                pieChartData.get(1).getNode().setStyle("-fx-pie-color: green");
    }

    private void setInventoryTable() {
        // creat table and columns
        TableColumn nameColumn = new TableColumn("name");
        TableColumn managerColumn = new TableColumn("manager");
        TableColumn addressColumn = new TableColumn("address");
        TableColumn proteinColumn = new TableColumn("protein");
        TableColumn snackColumn = new TableColumn("snack");
        TableColumn drinkColumn = new TableColumn("drink");
        TableColumn dairyColumn = new TableColumn("dairy");
        inventoryTable = new TableView<>();

        // set width of columns
        nameColumn.setMinWidth(10);
        nameColumn.setPrefWidth(75);
        managerColumn.setMinWidth(10);
        managerColumn.setPrefWidth(75);
        addressColumn.setMinWidth(50);
        addressColumn.setPrefWidth(220);
        proteinColumn.setMinWidth(10);
        proteinColumn.setPrefWidth(75);
        snackColumn.setMinWidth(10);
        snackColumn.setPrefWidth(75);
        drinkColumn.setMinWidth(10);
        drinkColumn.setPrefWidth(75);
        dairyColumn.setMinWidth(10);
        dairyColumn.setPrefWidth(75);

        // set columns alignment
        nameColumn.setStyle("-fx-alignment: CENTER;");
        managerColumn.setStyle("-fx-alignment: CENTER;");
        addressColumn.setStyle("-fx-alignment: CENTER;");
        proteinColumn.setStyle("-fx-alignment: CENTER;");
        snackColumn.setStyle("-fx-alignment: CENTER;");
        drinkColumn.setStyle("-fx-alignment: CENTER;");
        dairyColumn.setStyle("-fx-alignment: CENTER;");

        // set value factory (most important part)
        nameColumn.setCellValueFactory(new PropertyValueFactory("name"));
        managerColumn.setCellValueFactory(new PropertyValueFactory("manager"));
        addressColumn.setCellValueFactory(new PropertyValueFactory("address"));
        proteinColumn.setCellValueFactory(new PropertyValueFactory("protein"));
        snackColumn.setCellValueFactory(new PropertyValueFactory<>("snack"));
        drinkColumn.setCellValueFactory(new PropertyValueFactory<>("drink"));
        dairyColumn.setCellValueFactory(new PropertyValueFactory<>("dairy"));

        // add columns to table
        inventoryTable.getColumns().addAll(nameColumn, managerColumn, addressColumn, proteinColumn, snackColumn,
                drinkColumn, dairyColumn);

        // add table to pane
        inventoryManagmentPane.getChildren().addAll(inventoryTable);

        inventoryTable.setLayoutX(14);
        inventoryTable.setLayoutY(14);
        inventoryTable.setPrefWidth(672);
        inventoryTable.setPrefHeight(450);
    }

    private void setInventory() {

        String username = "root";
        String password = "Rezam14369";
        String url = "jdbc:mysql://localhost:3306/groceryStore";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            Connection con = DriverManager.getConnection(url, username, password);

            String query = "SELECT inventory.name, stores.name, address, protein, snack, drink, dairy FROM inventory join stores on manager=id;";
            Statement statement = con.createStatement();
            ResultSet result = statement.executeQuery(query);
            String name, manager, address;
            boolean protein, snack, drink, dairy;
            ObservableList<Inventory> data = FXCollections.observableArrayList();
            while (result.next()) {
                name = result.getString(1);
                manager = result.getString(2);
                address = result.getString(3);
                protein = result.getBoolean(4);
                snack = result.getBoolean(5);
                drink = result.getBoolean(6);
                dairy = result.getBoolean(7);
                data.add(new Inventory(name, manager, address, protein, snack, drink, dairy));
            }
            inventoryTable.setItems(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setPopup() {
        addPopup = new Popup();

        AnchorPane anchorPane = new AnchorPane();
        anchorPane.setPrefHeight(300);
        anchorPane.setPrefWidth(300);

        anchorPane.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
        anchorPane.setStyle("-fx-border-color: black;");

        // define popup components
        Text name = new Text("name:");
        Text manager = new Text("manager:");
        Text address = new Text("address:");
        Text protein = new Text("protein:");
        Text snack = new Text("snack:");
        Text drink = new Text("drink:");
        Text dairy = new Text("dairy:");
        CheckBox proteinCheckBox = new CheckBox();
        CheckBox snackCheckBox = new CheckBox();
        CheckBox drinkCheckBox = new CheckBox();
        CheckBox dairyCheckBox = new CheckBox();
        TextField nameTextField = new TextField();
        TextField managerTextField = new TextField();
        TextField addressTextField = new TextField();
        Button confirmAdd = new Button("Confirm");
        // add components to anchorPane
        anchorPane.getChildren().addAll(name, manager, address, protein, snack, drink, dairy,
                proteinCheckBox, snackCheckBox, drinkCheckBox, dairyCheckBox,
                nameTextField, managerTextField, addressTextField, confirmAdd);
        // set components pos
        name.setLayoutX(45);
        name.setLayoutY(45);
        manager.setLayoutX(28);
        manager.setLayoutY(95);
        address.setLayoutX(35);
        address.setLayoutY(145);
        protein.setLayoutX(40);
        protein.setLayoutY(185);
        snack.setLayoutX(51);
        snack.setLayoutY(220);
        drink.setLayoutX(160);
        drink.setLayoutY(185);
        dairy.setLayoutX(160);
        dairy.setLayoutY(220);
        proteinCheckBox.setLayoutX(110);
        proteinCheckBox.setLayoutY(170);
        snackCheckBox.setLayoutX(110);
        snackCheckBox.setLayoutY(205);
        drinkCheckBox.setLayoutX(220);
        drinkCheckBox.setLayoutY(170);
        dairyCheckBox.setLayoutX(220);
        dairyCheckBox.setLayoutY(205);
        nameTextField.setLayoutX(110);
        nameTextField.setLayoutY(25);
        managerTextField.setLayoutX(110);
        managerTextField.setLayoutY(80);
        addressTextField.setLayoutX(110);
        addressTextField.setLayoutY(128);
        confirmAdd.setLayoutX(100);
        confirmAdd.setLayoutY(240);
        // set font size
        name.setFont(Font.font(16));
        manager.setFont(Font.font(16));
        address.setFont(Font.font(16));
        protein.setFont(Font.font(16));
        snack.setFont(Font.font(16));
        drink.setFont(Font.font(16));
        dairy.setFont(Font.font(16));
        confirmAdd.setFont(Font.font(18));
        // set prompt text
        nameTextField.setPromptText("name");
        managerTextField.setPromptText("manager");
        addressTextField.setPromptText("address");
        // add anchor pane to popup
        addPopup.getContent().addAll(anchorPane);
        EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                if (!addPopup.isShowing())
                    addPopup.show(LoginPage.stage);
                else
                    addPopup.hide();

            }
        };
        EventHandler<ActionEvent> confirm = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                addStorehouseToSql(
                        new Inventory(nameTextField.getText(), managerTextField.getText(), addressTextField.getText(),
                                proteinCheckBox.isSelected(), snackCheckBox.isSelected(), drinkCheckBox.isSelected(),
                                dairyCheckBox.isSelected()));
                addStorehouseToTable(
                        new Inventory(nameTextField.getText(), managerTextField.getText(), addressTextField.getText(),
                                proteinCheckBox.isSelected(), snackCheckBox.isSelected(), drinkCheckBox.isSelected(),
                                dairyCheckBox.isSelected()));
                addPopup.hide();
            }
        };

        addStorehouse.setOnAction(event);
        confirmAdd.setOnAction(confirm);
    }

    public void addStorehouseToSql(Inventory inventory) {
        String username = "root";
        String password = "Rezam14369";
        String url = "jdbc:mysql://localhost:3306/groceryStore";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            Connection con = DriverManager.getConnection(url, username, password);
            String sql = " insert into inventory (name, manager, address, protein, snack, drink, dairy)"
                    + " values (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStmt = con.prepareStatement(sql);
            preparedStmt.setString(1, inventory.getName());
            preparedStmt.setInt(2, Integer.parseInt(inventory.getManager()));
            preparedStmt.setString(3, inventory.getAddress());
            preparedStmt.setInt(4, inventory.getProtein() ? 1 : 0);
            preparedStmt.setInt(5, inventory.getSnack() ? 1 : 0);
            preparedStmt.setInt(6, inventory.getDrink() ? 1 : 0);
            preparedStmt.setInt(7, inventory.getDairy() ? 1 : 0);
            preparedStmt.execute();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addStorehouseToTable(Inventory inventory) {
        String username = "root";
        String password = "Rezam14369";
        String url = "jdbc:mysql://localhost:3306/groceryStore";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            Connection con = DriverManager.getConnection(url, username, password);

            String query = "SELECT name FROM stores WHERE id=" + Integer.parseInt(inventory.getManager()) + ";";
            Statement statement = con.createStatement();
            ResultSet result = statement.executeQuery(query);
            System.out.println(result.getString(1));
            inventory.setManager(result.getString(1));
            con.close();
        } catch (SQLException e) {
            // TODO: handle exception
        }
        inventoryTable.getItems().add(inventory);
    }

    public void deleteButtonClick() {
        String username = "root";
        String password = "Rezam14369";
        String url = "jdbc:mysql://localhost:3306/groceryStore";

        ObservableList<Inventory> itemSelected, allItems;
        allItems = inventoryTable.getItems();
        itemSelected = inventoryTable.getSelectionModel().getSelectedItems();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            Connection con = DriverManager.getConnection(url, username, password);

            String query = "delete from inventory where name='" + itemSelected.get(0).getName() + "'";
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.execute();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        itemSelected.forEach(allItems::remove);

    }

    private void setSellersCount() {
        try {
            sellersCount = (int) Files.lines(Paths.get("src/LoginScreen/stores.txt")).count();
        } catch (Exception e) {
            System.out.println("File doesn't open");
        }
    }

    private void setSellersChat() {
        sellersChat.setMaxWidth(100);
        sellersChat.setPrefColumns(1);
        sellersChat.setPrefRows(sellersCount);
        sellersChat.setAlignment(Pos.TOP_CENTER);
        sellersChat.setVgap(10);
        sellersChat.setPadding(new Insets(10));
        chatPanes = new AnchorPane[sellersCount];
        profileImages = new ImageView[sellersCount];
        for (int i = 0; i < sellersCount; i++) {
            profileImages[i] = new ImageView("/Resources/shopkeeper.png");
            chatPanes[i] = new AnchorPane();
            profileImages[i].setFitHeight(50);
            profileImages[i].setFitWidth(50);
            chatPane.getChildren().add(chatPanes[i]);
            AnchorPane.setRightAnchor(chatPanes[i], 5.0);
            AnchorPane.setBottomAnchor(chatPanes[i], 10.0);
            AnchorPane.setLeftAnchor(chatPanes[i], 5.0);
            AnchorPane.setTopAnchor(chatPanes[i], 10.0);

            setChatProfileEventHandler(profileImages[i]);
            switch (i) {
                case 0:
                    chatPanes[i].setBackground(
                            new Background(new BackgroundFill(Color.PINK, CornerRadii.EMPTY, Insets.EMPTY)));
                    break;
                case 1:
                    chatPanes[i].setBackground(
                            new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
                    break;
            }
            sellersChat.getChildren().add(profileImages[i]);
        }
    }

    private void setChatProfileEventHandler(ImageView imageView) {
        imageView.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                for (int i = 0; i < sellersCount; i++) {
                    if (event.getSource() == profileImages[i]) {
                        chatPanes[i].toFront();
                    }
                }
            }
        });
    }

}
