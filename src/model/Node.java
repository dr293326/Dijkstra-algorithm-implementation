package model;

import java.util.ArrayList;
import java.util.List;

public class Node {

    private long nodeID;
    private int x;
    private int y;
    private double distanceToStartNode;
    private Node parentNode = null;
    public double resultOlt;

    private int numberOfClients;

    List<Path> pathList = new ArrayList<>();
    List<Path> resultPathList = new ArrayList<>();
    List<Path> sortedPathList = new ArrayList<>();

    List<Edge> visualisationEdges = new ArrayList<>();

    public int getNumberOfClients() {
        return numberOfClients;
    }

    public void setNumberOfClients(int numberOfClients) {
        this.numberOfClients = numberOfClients;
    }

    public List<Edge> getVisualisationEdges() {
        return visualisationEdges;
    }

    public void setVisualisationEdges(List<Edge> visualisationEdges) {
        this.visualisationEdges = visualisationEdges;
    }

    public void setParentNode(Node parentNode) {
        this.parentNode = parentNode;
    }

    public Node getParentNode() {
        return parentNode;
    }

    public void setDistanceToStartNode(double distanceToStartNode) {
        this.distanceToStartNode = distanceToStartNode;
    }

    public double getDistanceToStartNode() {
        return distanceToStartNode;
    }

    public Node(){

    }

    public List<Path> getPathList() {
        return pathList;
    }

    public void setPathList(List<Path> pathList) {
        this.pathList = pathList;
    }

    public List<Path> sortPathList(List<Path> currPath){
        List<Path> tmp = new ArrayList<>(currPath);
        List<Path> sorted = new ArrayList<>();
        Path tmpPath = new Path();
        while (tmp.size() != 0){
            tmpPath = findMinPath(tmp);
            sorted.add(tmpPath);
            tmp.remove(tmpPath);
        }
        return sorted;
    }

    public Path findMinPath(List<Path> listPath){
        Path minPath = null;
        for(Path p:listPath){
            if (minPath == null){
                minPath = p;
            }
            if(p.value < minPath.value){
                minPath = p;
            }
        }
        return minPath;
    }


    public Node(long nodeID, int x, int y, int numberOfClients) {
        this.nodeID = nodeID;
        this.x = x;
        this.y = y;
        this.numberOfClients = numberOfClients;
    }

    public void setNodeProperties(long nodeID, int x, int y){
        this.x = x;
        this.y = y;
        this.nodeID = nodeID;
    }

    public int getX(){
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public long getNodeID() {
        return nodeID;
    }

    public Node getNode() {
        return this;
    }

    public void printOnConsole() {
        System.out.println("Wezel ID:" + this.nodeID + " X:" + this.x + " Y:" + this.y);
    }

    /*
    public void calculateFibers(){
        for (Edge edge:visualisationEdges){
            for(Path path:pathList){
                List<Edge> pathEdges= path.getUsedEdges();
                for(Edge pathEdge:pathEdges){
                    if(pathEdge == edge){

                    }
                }
            }
        }
    }
     */
}
