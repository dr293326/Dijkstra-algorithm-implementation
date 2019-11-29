package model;

import static java.lang.Math.sqrt;
import static java.lang.Math.abs;


public class Edge {

    private long edgeID;
    private double value;
    private String direction;
    private Node start;
    private Node finish;
    private Node parentNode;
    private int numberOfFibers = 0;

    public Edge() {

    }

    public Edge(long edgeID, int value, String direction, Node start, Node finish) {
        this.edgeID = edgeID;
        this.value = value;
        this.direction = direction;
        this.start = start;
        this.finish = finish;
    }

    public Edge(long edgeID, Node start, Node finish) {
        this.edgeID = edgeID;
        this.start = start;
        this.finish = finish;
    }

    public Edge(long edgeID, Node start, Node finish, int value) {
        this.edgeID = edgeID;
        this.start = start;
        this.finish = finish;
        this.value = value;
    }

    public int getNumberOfFibers() {
        return numberOfFibers;
    }

    public void setNumberOfFibers(int numberOfFibers) {
        this.numberOfFibers = numberOfFibers;
    }

    public long getEdgeID() {
        return edgeID;
    }

    public void setEdgeID(long edgeID) {
        this.edgeID = edgeID;
    }

    public double getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public Node getStart() {
        return start;
    }

    public void setStart(Node start) {
        this.start = start;
    }

    public Node getFinish() {
        return finish;
    }

    public void setFinish(Node finish) {
        this.finish = finish;
    }

    public void setEdgeProperties(long edgeID, int value, String direction, Node start, Node finish){
        this.edgeID = edgeID;
        this.value = value;
        this.direction = direction;
        this.start = start;
        this.finish = finish;
    }

    public void printOnConsole(){
        System.out.println("Krawedz ID:" + this.edgeID + " ID wezla start:" + start.getNodeID() + " ID wezla koniec:" + finish.getNodeID() + " Warosc: " + this.value);
    }

    public void calculateValue(){
        int x1 = this.start.getX();
        int y1 = this.start.getY();
        int x2 = this.finish.getX();
        int y2 = this.finish.getY();

        double absx = (double)Math.abs(x1-x2);
        double absy = (double)Math.abs(y1-y2);

        double res = sqrt(absx*absx + absy*absy);

        this.value = res;
    }

    public Node getParentNode() {
        return parentNode;
    }

    public void setParentNode(Node parentNode)
    {
        this.parentNode = parentNode;
    }

    public int getCenterX() {
        int rtn = (this.start.getX() + this.finish.getX())/2;
        return rtn;
    }

    public int getCenterY() {
        int rtn = (this.start.getY() + this.finish.getY())/2;
        return rtn;
    }
}
