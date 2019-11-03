package gui;


import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import model.Edge;
import model.Network;
import model.Node;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class MainWindowController {

    //----------------------------------------
    //model
    private Network network = new Network();
    private List<Edge> primAlgorithmEdges;
    private List<Edge> dijkstraAlgorithmEdges;
    //----------------------------------------
    private boolean isLoaded = false;

    List<Circle> nodeCircleList = new ArrayList<>();
    List<Line> edgeLineList = new ArrayList<>();

    final static private int scale = 10;

    @FXML
    private Button loadButton;
    @FXML
    private Button mst;

    @FXML
    private Button exitButton;

    @FXML
    private TextField textField;

    @FXML
    void initialize() {

    }

    public static int getScale() {
        return scale;
    }

    public void openGraphDrawingWindow(){
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/gui/GraphDrawing.fxml"));
        Pane pane = null;
        try {
            pane = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
            //TODO:obsluga wyjatku
        }

        GraphDrawingController graphDrawingController = fxmlLoader.getController();
        graphDrawingController.setMainWindowController(this);

        List<Node> nodeList = network.getNodeList();

        for (Node currentNode:nodeList){
            Circle currentCircle = new Circle(currentNode.getX()*scale,currentNode.getY()*scale,20, Color.BLUE);
            //Circle currentCircle = new Circle(currentNode.getX(),currentNode.getY(),15, Color.BLUE);
            Label currentLabel = new Label();

            currentLabel.setText(String.valueOf(currentNode.getNodeID()));
            currentLabel.setLayoutX(currentCircle.getCenterX());
            currentLabel.setLayoutY(currentCircle.getCenterY());
            currentLabel.setTextFill(Color.WHITE);
            //currentLabel.setFont(Font.font(14));

            nodeCircleList.add(currentCircle);
            pane.getChildren().add(currentCircle);
            pane.getChildren().add(currentLabel);
        }
        List<Edge> edgeList = network.getEdgeList();

        for (Edge currentEdge:edgeList){
            Line currentLine = new Line();
            currentLine.setLayoutX(0);
            currentLine.setLayoutY(0);
            currentLine.setStartX(currentEdge.getStart().getX()*scale);
            currentLine.setStartY(currentEdge.getStart().getY()*scale);
            currentLine.setEndX(currentEdge.getFinish().getX()*scale);
            currentLine.setEndY(currentEdge.getFinish().getY()*scale);
            edgeLineList.add(currentLine);
            pane.getChildren().add(currentLine);
        }

        Stage graphStage = new Stage();
        graphStage.setTitle("Wizualizacja grafu");
        graphStage.setScene(new Scene(pane));
        //graphStage.setResizable(false);
        graphStage.show();
    }

    @FXML
    public void loadGraph(){
        if (isLoaded == true){
            this.setNetwork(new Network());
            isLoaded = false;
        }

        int ret = -1;
        ret = network.readFromFile(textField.getText());

        network.printNetwork();

        if (ret == 1){
            Alert infoAlert = new Alert(Alert.AlertType.INFORMATION);
            infoAlert.setTitle("Sukces!");
            infoAlert.setHeaderText("Wczytywanie grafu z pliku powiodło się!");
            infoAlert.showAndWait();
            isLoaded = true;
        }
        else if (ret == -1){
            Alert infoAlert = new Alert(Alert.AlertType.ERROR);
            infoAlert.setTitle("Błąd!");
            infoAlert.setHeaderText("Niepoprawna ścieżka pliku!");
            infoAlert.showAndWait();
        }
        else if (ret == 0){
            Alert infoAlert = new Alert(Alert.AlertType.ERROR);
            infoAlert.setTitle("Błąd!");
            infoAlert.setHeaderText("Wystąpił błąd podczas wczytywania pliku! Sprawdź format wskazanego pliku." );
            infoAlert.showAndWait();
        }
    }

    @FXML
    public void exitApp(){
        Platform.exit();
    }

    @FXML
    public void mstClicked(){
        if (isLoaded == true){
            this.openGraphDrawingWindow();
        }
        else{
            Alert infoAlert = new Alert(Alert.AlertType.ERROR);
            infoAlert.setTitle("Błąd!");
            infoAlert.setHeaderText("Nie wczytano pliku z grafem!");
            infoAlert.showAndWait();
        }
    }

    public Network getNetwork() {
        return network;
    }

    public void setNetwork(Network network) {
        this.network = network;
    }

    public List<Edge> getPrimAlgorithmEdges() {
        return primAlgorithmEdges;
    }

    public void setPrimAlgorithmEdges(List<Edge> primAlgorithmEdges) {
        this.primAlgorithmEdges = primAlgorithmEdges;
    }

    public List<Edge> getDijkstraAlgorithmEdges() {
        return dijkstraAlgorithmEdges;
    }

    public void setDijkstraAlgorithmEdges(List<Edge> dijkstraAlgorithmEdges) {
        this.dijkstraAlgorithmEdges = dijkstraAlgorithmEdges;
    }
}
