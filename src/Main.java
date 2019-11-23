import gui.MainWindowController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.Edge;
import model.Network;

import java.util.List;

public class Main extends Application {

    public static void main(String[] args) {
        //System.out.println("Hello World!");

        System.out.println("");
        Network network = new Network();
        List <Edge> primAlgorithmEdges;
        network.readFromFile("resources/example.txt");


        network.printNetwork();
/*
         primAlgorithmEdges = network.doPrimMST(0);

        System.out.println("");
        System.out.println("krawedzie MST:");

         for(Edge currentEdge:primAlgorithmEdges) {
             currentEdge.printOnConsole();
         }


        Network netTest = new Network();
        netTest.readFromFile("resources/ex2.txt");
        netTest.zad1();
*/
        launch();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        FXMLLoader fxloader = new FXMLLoader();
        fxloader.setLocation(getClass().getResource("/gui/MainWindow.fxml"));
        Pane pane = fxloader.load();

        //MainWindowController mainWindowController = fxloader.getController();


        Scene scene = new Scene(pane);

        primaryStage.setScene(scene);
        //blokuje zmiane rozmiaru
        primaryStage.setResizable(false);
        primaryStage.setTitle("Projekt 1 [AISDE]");
        primaryStage.show();
    }
}
