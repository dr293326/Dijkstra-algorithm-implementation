package gui;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import model.Edge;
import model.Node;
import model.Task1;

import java.util.ArrayList;
import java.util.List;

public class GraphDrawingController {

    List<Line> edgeLineList = new ArrayList<>();
    public MainWindowController mainWindowController;
    public boolean isDrawn = false;
    public int scale = 10;

    @FXML
    private Pane insidePane;
    @FXML
    private AnchorPane pane;
    @FXML
    private Button mstbtn;
    @FXML
    private Button closebtn;
    @FXML
    private TextField mstStartTextField;
    @FXML
    private TextField dijkstraFinishTextField;
    @FXML
    private TextField dijkstraStartTextField;
    @FXML
    private Button dijkstrabtn;




    @FXML
    public void closeWindow(){
        Stage stage = (Stage) closebtn.getScene().getWindow();
        //
        stage.close();
    }

    @FXML
    public void mstClicked(){
        clearAlgorithmLines();

        try{
            if (Integer.parseInt(mstStartTextField.getText()) > mainWindowController.getNetwork().getNodeList().size() || Integer.parseInt(mstStartTextField.getText()) < 1){
                throw new NumberFormatException("Podaje poprawny wierzchołek startowy!");
            }
            List<Edge> mstEdges = mainWindowController.getNetwork().doPrimMST(Integer.parseInt(mstStartTextField.getText())-1);
            mainWindowController.setPrimAlgorithmEdges(mstEdges);
            drawAlgorithmEdges(mstEdges);
        }
        catch (NumberFormatException nfex){
            Alert infoAlert = new Alert(Alert.AlertType.ERROR);
            infoAlert.setTitle("Błąd!");
            infoAlert.setHeaderText("Podaj poprawny wierzchołek startowy!");
            infoAlert.showAndWait();
            isDrawn = false;
        }
    }

    @FXML
    public void dijkstraClicked(){
        clearAlgorithmLines();

        try{
            if (Integer.parseInt(dijkstraStartTextField.getText()) < 1 || Integer.parseInt(dijkstraStartTextField.getText()) > mainWindowController.getNetwork().getNodeList().size() || Integer.parseInt(dijkstraStartTextField.getText()) == Integer.parseInt(dijkstraFinishTextField.getText()) || Integer.parseInt(dijkstraFinishTextField.getText()) < 1 || Integer.parseInt(dijkstraFinishTextField.getText()) > mainWindowController.getNetwork().getNodeList().size()){
                throw new NumberFormatException("Podaje poprawny wierzchołek startowy i końcowy!");
            }
            List<Edge> dijkstraEdges = mainWindowController.getNetwork().algDijkstra(Integer.parseInt(dijkstraStartTextField.getText()),Integer.parseInt(dijkstraFinishTextField.getText()));
            mainWindowController.setDijkstraAlgorithmEdges(dijkstraEdges);
            drawAlgorithmEdges(dijkstraEdges);
        }
        catch (NumberFormatException nfex){
            Alert infoAlert = new Alert(Alert.AlertType.ERROR);
            infoAlert.setTitle("Błąd!");
            infoAlert.setHeaderText("Podaj poprawny wierzchołek startowy i końcowy!");
            infoAlert.showAndWait();
            isDrawn = false;
        }
    }




    private void drawAlgorithmEdges(List<Edge> edges) {
        for (Edge currentEdge : edges) {
            Line currentLine = new Line();
            currentLine.setLayoutX(0);
            currentLine.setLayoutY(0);
            currentLine.setStartX(currentEdge.getStart().getX() * mainWindowController.getScale());
            currentLine.setStartY(currentEdge.getStart().getY() * mainWindowController.getScale());
            currentLine.setEndX(currentEdge.getFinish().getX() * mainWindowController.getScale());
            currentLine.setEndY(currentEdge.getFinish().getY() * mainWindowController.getScale());
            currentLine.setStroke(Color.RED);
            edgeLineList.add(currentLine);
            pane.getChildren().add(currentLine);
        }
        /*
        for (Node currentNode:mainWindowController.getNetwork().getNodeList()){
            Circle currentCircle = new Circle(currentNode.getX()*scale,currentNode.getY()*scale,20, Color.BLUE);
            //Circle currentCircle = new Circle(currentNode.getX(),currentNode.getY(),15, Color.BLUE);
            Label currentLabel = new Label();

            currentLabel.setText(String.valueOf(currentNode.getNodeID()));
            currentLabel.setLayoutX(currentCircle.getCenterX());
            currentLabel.setLayoutY(currentCircle.getCenterY());
            currentLabel.setTextFill(Color.WHITE);
            //currentLabel.setFont(Font.font(14));

            //nodeCircleList.add(currentCircle);
            pane.getChildren().add(currentCircle);
            pane.getChildren().add(currentLabel);
        }
        */

        isDrawn = true;
    }

    private void clearAlgorithmLines() {
        if (isDrawn == true) {
            pane.getChildren().removeAll(edgeLineList);
            isDrawn = false;
        }
    }

    public void setMainWindowController(MainWindowController mainWindowController) {
        this.mainWindowController = mainWindowController;
    }

    public TextField getDijkstraStartTextField() {
        return dijkstraStartTextField;
    }

    public TextField getDijkstraFinishTextField() {
        return dijkstraFinishTextField;
    }
}
