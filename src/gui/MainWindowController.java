package gui;


import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
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
            Circle currentCircle = new Circle((currentNode.getX()*2)+250,(currentNode.getY()*2)+150,15, Color.BLUE);
            //Circle currentCircle = new Circle(currentNode.getX(),currentNode.getY(),15, Color.BLUE);

            nodeCircleList.add(currentCircle);
            pane.getChildren().add(currentCircle);
        }
        List<Edge> edgeList = network.getEdgeList();

        for (Edge currentEdge:edgeList){
            Line currentLine = new Line();
            currentLine.setLayoutX(0);
            currentLine.setLayoutY(0);
            currentLine.setStartX((currentEdge.getStart().getX()*2) + 250);
            currentLine.setStartY((currentEdge.getStart().getY()*2) + 150);
            currentLine.setEndX((currentEdge.getFinish().getX()*2) + 250);
            currentLine.setEndY((currentEdge.getFinish().getY()*2) + 150);
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
}
