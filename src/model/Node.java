package model;

public class Node {

    private long nodeID;
    private int x;
    private int y;
    private double distanceToStartNode;
    private Node parentNode = null;

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

    public Node(long nodeID, int x, int y) {
        this.nodeID = nodeID;
        this.x = x;
        this.y = y;
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
}
