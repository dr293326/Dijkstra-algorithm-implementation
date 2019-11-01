package gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import model.Edge;
import model.Node;

import java.util.ArrayList;
import java.util.List;

public class GraphDrawingController {

    MainWindowController mainWindowController;


    @FXML
    private Pane insidePane;

    @FXML
    private AnchorPane pane;

    @FXML
    private Button mstbtn;

    @FXML
    private Button closebtn;

    @FXML
    public void closeWindow(){
        Stage stage = (Stage) closebtn.getScene().getWindow();
        //
        stage.close();
    }

    public void mstClicked(){
        List<Edge> mstEdges = mainWindowController.getNetwork().doPrimMST(0);

        for (Edge currentEdge:mstEdges){
            Line currentLine = new Line();
            currentLine.setLayoutX(0);
            currentLine.setLayoutY(0);
            currentLine.setStartX((currentEdge.getStart().getX()*2) + 250);
            currentLine.setStartY((currentEdge.getStart().getY()*2) + 150);
            currentLine.setEndX((currentEdge.getFinish().getX()*2) + 250);
            currentLine.setEndY((currentEdge.getFinish().getY()*2) + 150);
            currentLine.setStroke(Color.RED);
            //edgeLineList.add(currentLine);
            pane.getChildren().add(currentLine);
        }
    }

    public void setMainWindowController(MainWindowController mainWindowController) {
        this.mainWindowController = mainWindowController;
    }
}
