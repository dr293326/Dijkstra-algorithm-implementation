package model;

import java.util.ArrayList;
import java.util.List;

public class Path {
    public Node startNode;
    public Node finishNode;
    public List<Edge> usedEdges = new ArrayList<>();
    public double value = 0;
}
