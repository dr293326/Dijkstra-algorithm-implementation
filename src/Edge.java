public class Edge {



    private long edgeID;
    private int value;
    private String direction;
    private Node start;
    private Node finish;

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

    public long getEdgeID() {
        return edgeID;
    }

    public void setEdgeID(long edgeID) {
        this.edgeID = edgeID;
    }

    public int getValue() {
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
        System.out.println("Krawedz ID:" + this.edgeID + " ID wezla start:" + start.getNodeID() + " ID wezla koniec:" + finish.getNodeID() );
    }
}
