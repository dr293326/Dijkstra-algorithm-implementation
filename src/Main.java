import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        //System.out.println("Hello World!");
        System.out.println("");
        Network network = new Network();
        List <Edge> primAlgorithmEdges;
        network.readFromFile("resources/example.txt");


        network.printNetwork();

         primAlgorithmEdges = network.doPrimMST(0);

        System.out.println("");
        System.out.println("krawedzie MST:");

         for(Edge currentEdge:primAlgorithmEdges) {
             currentEdge.printOnConsole();
         }
    }
}
