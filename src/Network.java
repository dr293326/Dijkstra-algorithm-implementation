import com.sun.java.swing.plaf.windows.WindowsButtonListener;

import java.util.List;

public class Network {
    static long networkCounter = 0;
    public long networkID;
    private List<Edge> edgeList;
    private List<Node> nodeList;

    public Network() {
        this.networkID = networkCounter + 1;
        networkCounter++;
    }

    public void readFromFile(){

    }

}
