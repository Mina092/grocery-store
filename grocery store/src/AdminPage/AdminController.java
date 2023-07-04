package AdminPage;

import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.chart.PieChart;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;

public class AdminController {
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
    private AnchorPane massangerPane;
    @FXML
    private PieChart pieChart;
    @FXML
    private TilePane sellersChat;
    @FXML
    private AnchorPane chatPane;

    private int sellersCount = 5;
    private ImageView[] profileImages;
    private AnchorPane[] chatPanes;

    @FXML
    private void initialize(){
        setMenuImagesEventHandler(financialReport);
        setMenuImagesEventHandler(inventoryManagment);
        setMenuImagesEventHandler(chat);
        setPieChart();
        setSellersChat();
    }
    private void setMenuImagesEventHandler(ImageView imageView){
        imageView.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

    @Override
    public void handle(MouseEvent event) {
        if (event.getSource() == financialReport) {
            financialReportPane.toFront();
        }else if (event.getSource() == inventoryManagment) {
            inventoryManagmentPane.toFront();
        } else if (event.getSource() == chat) {
            massangerPane.toFront();
        }
        }
    });
    }
    private void setPieChart(){
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
            new PieChart.Data("Apples",10),
            new PieChart.Data("Oranges",20),
            new PieChart.Data("Grapes",25),
            new PieChart.Data("Melons",15)
        );
        pieChartData.forEach(data ->
            data.nameProperty().bind(
                Bindings.concat(
                    data.getName()," amount: ",data.pieValueProperty()
                )
            )
        );
        pieChart.getData().addAll(pieChartData);
    }
    private void setSellersChat(){
        sellersChat.setMaxWidth(100);
        sellersChat.setPrefColumns(1);
        sellersChat.setPrefRows(sellersCount);
        sellersChat.setAlignment(Pos.TOP_CENTER);
        sellersChat.setVgap(10);
        sellersChat.setPadding(new Insets(10));
        chatPanes = new AnchorPane[sellersCount];
        profileImages = new ImageView[sellersCount];
        for(int i = 0 ; i<sellersCount;i++){
            profileImages[i] = new ImageView("/Resources/shopkeeper.png");
            chatPanes[i] = new AnchorPane();
            profileImages[i].setFitHeight(50);
            profileImages[i].setFitWidth(50);
            chatPanes[i].setPrefWidth(600);
            chatPanes[i].setPrefHeight(550);
            chatPane.getChildren().add(chatPanes[i]);
            
            setChatProfileEventHandler(profileImages[i]);
            switch (i) {
                case 0:
                    chatPanes[i].setBackground(new Background(new BackgroundFill(Color.PINK, CornerRadii.EMPTY, Insets.EMPTY)));
                    break;
                case 1:
                    chatPanes[i].setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
                    break;
                case 2:
                    chatPanes[i].setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
                    break;
                case 3:
                    chatPanes[i].setBackground(new Background(new BackgroundFill(Color.ORANGE, CornerRadii.EMPTY, Insets.EMPTY)));
                    break;
            }
            sellersChat.getChildren().add(profileImages[i]);
        }
    }
    private void setChatProfileEventHandler(ImageView imageView){
        imageView.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

    @Override
    public void handle(MouseEvent event) {
        for(int i=0;i<sellersCount;i++){
            if(event.getSource()==profileImages[i]){
                chatPanes[i].toFront();
            }
        }
    }
    });
    }
}
