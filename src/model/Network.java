package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.util.Objects.isNull;

public class Network {
    private static long networkCounter = 0;
    private long networkID;
    private List<Edge> edgeList = new ArrayList<>();
    private List<Node> nodeList = new ArrayList<>();
    private File file;
    private int numberOfNodes;
    private int numberOfEdges;
    private boolean isNode = false;
    private boolean isEdge = false;


    public Network() {
        this.networkID = networkCounter + 1;
        networkCounter++;
    }

    public List<Node> getNodeList() {
        return nodeList;
    }

    public List<Edge> getEdgeList() {
        return edgeList;
    }

    //TODO: rozbic wczytywanie na podmetody
    //TODO: sprawdzac czy krawedzie sie nie powtarzaja np 2 i 8 w przykladowym pliku
    public int readFromFile(String pathFile) {
        if(!isNull(pathFile))
        {
            try {
                this.file = new File(pathFile);

                if (file.exists()) {

                    Scanner scanner = new Scanner(file);
                    while (scanner.hasNextLine()) {

                        //Zaciagam linie
                        String line = scanner.nextLine();

                        //Pomijam linie z #
                        if (!(line.substring(0, 1).contains("#"))) {
                            //Rozdzielam kazda linie za pomoca regexa w split
                            //Otrzymuje tablice stringow ktore byly rodzielone spacja
                            String[] result = line.split(" ");

                            try {
                                //ustawiam flagi
                                if (result[0].contains("WEZLY")) {
                                    isEdge = false;
                                    isNode = true;
                                    numberOfNodes = Integer.parseInt(result[2]);
                                    line = scanner.nextLine();
                                    result = line.split(" ");
                                }

                                if (result[0].contains("LACZA")) {
                                    isEdge = true;
                                    isNode = false;
                                    numberOfEdges = Integer.parseInt(result[2]);
                                    line = scanner.nextLine();
                                    result = line.split(" ");
                                }
                                //wczytuje do obiektow
                                if (isEdge == false && isNode == true && (!result[0].contains("#"))) {
                                    Node tmpnode = new Node(Long.parseLong(result[0]),Integer.parseInt(result[1]),Integer.parseInt(result[2]));
                                    nodeList.add(tmpnode);
                                }

                                if (isEdge == true && isNode == false && (!result[0].contains("#"))) {
                                    //id - 1 bo tablice indeksowane od 0 a nie 1
                                    Edge tmpedge = new Edge((Long.parseLong(result[0])),nodeList.get(Integer.parseInt(result[1])-1),nodeList.get(Integer.parseInt(result[2])-1));
                                    //sprawdzam czy poprawnie wprowadzilo do tmpedge
                                    if (isNull(tmpedge.getEdgeID()) || isNull(tmpedge.getStart()) || isNull(tmpedge.getFinish())) {
                                        throw new NumberFormatException("Blad podczas wczytywania krawedzi grafu z pliku!");
                                    }
                                    tmpedge.calculateValue();
                                    edgeList.add(tmpedge);

                                }
                            }
                            catch (NumberFormatException nfex) {
                                System.out.println("Wystapil blad podczas wczytywania grafu z pliku!");
                                System.out.println("Wyjatek :" + nfex.getMessage());
                                return 0;
                            }
                        }
                    }
                }
                else{
                    return -1;
                }
            }


            catch(FileNotFoundException ex){
                System.out.println("Nie znaleziono pliku o podanej sciezce!");
                return -1;
            }
            return 1;
        }
        return -1;
    }

    public void printNetwork() {
        System.out.println("/----------------------------------------------------------------/");
        for (Node nodes:nodeList) {
            nodes.printOnConsole();
        }
        System.out.println("/----------------------------------------------------------------/");
        for (Edge edges:edgeList) {
            edges.printOnConsole();
        }
    }

    public List<Edge> doPrimMST(int startID){
        Node startNode = nodeList.get(startID);

        if (!isNull(startNode)) {
            Node currentNode = new Node();
            int maxEdges = nodeList.size() -  1;                // gdy n wierzcholkow to  n-1 krawedzi

            int i = 0;
            List <Edge> usedEdges = new ArrayList<>();          // lista uzytych juz wierzcholkow
            List <Node> usedNodes = new ArrayList<>();
            usedNodes.add(startNode);

            currentNode = startNode;
            for (i=1;i < numberOfNodes;i++){
                Edge newEdge;
                newEdge = this.findMinEdge(usedNodes,usedEdges);
                if (usedNodes.contains(newEdge.getStart())){
                    usedNodes.add(newEdge.getFinish());
                }
                else{
                    usedNodes.add(newEdge.getStart());
                }
                usedEdges.add(newEdge);
            }
            return usedEdges;
        }
        return null;
    }

    // znajduje krawedz o najmniejszej wadze dla danego wierzchołka
    // metoda uwzglednia krawedzie niedozwolone
    public Edge findMinEdge(List<Node> usedNodes,List<Edge> usedEdges){
        double minValue = 0.0;
        Edge minValueEdge = new Edge();

        List <Edge> allowedEdges = new ArrayList<>();
        //przeszukaj liste krawedzi i znajdz te ktore lacza sie z wierzcholkiem i nie sa na liscie niedozwolonych
        for (Edge edge:edgeList){
            if (((usedNodes.contains(edge.getStart())) || (usedNodes.contains(edge.getFinish()))) && (!((usedNodes.contains(edge.getStart())) && (usedNodes.contains(edge.getFinish())))) && (!usedEdges.contains(edge))){

                if (edge.getValue() < minValue || minValue == 0){
                    minValue = edge.getValue();
                    minValueEdge = edge;
                }
            }
        }
        return minValueEdge;
    }
//-----------------------------------------------------------------------------------------------
//-----------------------------------------------------------------------------------------------
//-----------------------------------------------------------------------------------------------
//-----------------------------------------------------------------------------------------------
//-----------------------------------------------------------------------------------------------

    public List<Edge> algDijkstra(int startID, int finishID){
        Node start = nodeList.get(startID-1);
        Node finish = nodeList.get(finishID-1);

        //ustawiam odleglosci poczatkowe od startowego wezla
        for (Node node:nodeList) {
            if (node == start){
                node.setDistanceToStartNode(0.0);
            }
            else{
                node.setDistanceToStartNode(Double.MAX_VALUE);
            }
        }

        //tworze liste visited
        List<Node> visitedNodes = new ArrayList<>();
        List<Edge> visitedEdges = new ArrayList<>();

        //pierwszy odlg == 0 od siebie
        start.setParentNode(start);
        //dodaje startowy wezel do visitedNodes
        visitedNodes.add(start);

        while (!visitedNodes.contains(finish)){
            Node tmpNode;
            tmpNode = visitedNodes.get(visitedNodes.size()-1);

            Node foundNode = findNeighboursAndCalculate(tmpNode,visitedNodes,visitedEdges);

            Edge foundEdge = findEdge(foundNode.getParentNode(),foundNode,visitedEdges);
            foundEdge.setParentNode(foundNode.getParentNode());

            visitedEdges.add(foundEdge);
            visitedNodes.add(foundNode);
        }

        //wyciagam edge do sciezki
        List<Edge> visualEdges = new ArrayList<>();
        Node currentNode = finish;
        while(currentNode != start){
            for(Edge edge:visitedEdges){
                if( (edge.getStart() == currentNode && edge.getFinish() == currentNode.getParentNode()) || (edge.getStart() == currentNode.getParentNode() && edge.getFinish() == currentNode)){
                    visualEdges.add(edge);
                    break;
                }
            }
            currentNode = currentNode.getParentNode();
        }

        //resetuje odleglosci i parentnode bo juz mam liste krawedzi
        resetNodeDistances();
        for (Node node:nodeList){
            node.setParentNode(null);
        }


        return visualEdges;
    }

    //metoda ustawia odl na -
    private void resetNodeDistances() {
        for (Node node:nodeList){
            node.setDistanceToStartNode(-1.0);
        }
    }

    //metoda znajduje nieodwiedzonych sasiadow
    //przelicza im odleglosci
    //zwraca wezel dla ktorego odleglosc jest najmniejsza w ramach calej dotychczasowej sieci
    private Node findNeighboursAndCalculate(Node node, List<Node> alreadyVisited, List<Edge> visitedEdges){

        for(Edge edge : edgeList){
            if (edge.getStart() == node && !alreadyVisited.contains(edge.getFinish()) && !visitedEdges.contains(edge)) {
                //jesli nowowyliczona odleglosc mniejsza zmieniam
                if( (node.getDistanceToStartNode() + edge.getValue()) < edge.getFinish().getDistanceToStartNode() ){
                    edge.getFinish().setDistanceToStartNode(node.getDistanceToStartNode() + edge.getValue());
                    edge.getFinish().setParentNode(edge.getStart());
                }
                //neighbourNodes.add(edge.getFinish());
            }
            else if(edge.getFinish() == node && !alreadyVisited.contains(edge.getStart()) && !visitedEdges.contains(edge)) {
                //jesli nowowyliczona odleglosc mniejsza zmieniam
                if( (node.getDistanceToStartNode() + edge.getValue()) < edge.getStart().getDistanceToStartNode() ){
                    edge.getStart().setDistanceToStartNode(node.getDistanceToStartNode() + edge.getValue());
                    edge.getStart().setParentNode(edge.getFinish());
                }
                //neighbourNodes.add(edge.getFinish());
            }
        }

        List<Node> neighbourNodes = findNeigbours(alreadyVisited, visitedEdges);
        Node shortestNode = null;
        //znajduje wezel o najkrotszej odlg
        for (Node currNode :neighbourNodes){
            if (isNull(shortestNode)){
                shortestNode = currNode;
            }
            if (currNode.getDistanceToStartNode() < shortestNode.getDistanceToStartNode()){
                shortestNode = currNode;
            }
        }
        return shortestNode;
    }

    private List<Node> findNeigbours(List<Node> visitedNodes, List<Edge> visitedEdges){
        List<Node> neighNodes = new ArrayList<>();
        for (Edge edge:edgeList){
            if (visitedNodes.contains(edge.getStart())  && !( visitedNodes.contains(edge.getStart()) && visitedNodes.contains(edge.getFinish())) && !neighNodes.contains(edge.getFinish()) && !visitedEdges.contains(edge)) {
                neighNodes.add(edge.getFinish());
                edge.setParentNode(edge.getStart());
            }
            else if (visitedNodes.contains(edge.getFinish())  && !(visitedNodes.contains(edge.getStart()) && visitedNodes.contains(edge.getFinish())) && !neighNodes.contains(edge.getStart()) && !visitedEdges.contains(edge)) {
                neighNodes.add(edge.getStart());
                edge.setParentNode(edge.getFinish());
            }
        }
        return neighNodes;
    }

    private Edge findEdge (Node parent,Node current, List<Edge>visitedEdges){
        for (Edge edge:edgeList){
            if (!visitedEdges.contains(edge) && ((parent == edge.getStart() && current == edge.getFinish())||(parent == edge.getFinish() && current == edge.getStart()))){
                return edge;
            }
        }
        return null;
    }
}
