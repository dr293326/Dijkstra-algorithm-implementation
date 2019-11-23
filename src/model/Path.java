package model;

import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.isNull;

public class Path {
    public Node startNode;
    public Node finishNode;

    public List<Edge> usedEdges = new ArrayList<>();

    public double value = 0;
    public int totalCapacity = 0;

    public List<Cable> usedCables = new ArrayList<>();

    public Path(Node startNode, Node finishNode, List<Edge> usedEdges) {
        this.startNode = startNode;
        this.finishNode = finishNode;
        this.usedEdges = usedEdges;
    }

    public Path() {

    }

    //------ gettery i settery ------

    public Node getStartNode() {
        return startNode;
    }

    public Node getFinishNode() {
        return finishNode;
    }

    public List<Edge> getUsedEdges() {
        return usedEdges;
    }

    public double getValue() {
        return value;
    }

    public int getTotalCapacity() {
        return totalCapacity;
    }

    public List<Cable> getUsedCables() {
        return usedCables;
    }

    public void setStartNode(Node startNode) {
        this.startNode = startNode;
    }

    public void setFinishNode(Node finishNode) {
        this.finishNode = finishNode;
    }

    public void setUsedEdges(List<Edge> usedEdges) {
        this.usedEdges = usedEdges;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public void setTotalCapacity(int totalCapacity) {
        this.totalCapacity = totalCapacity;
    }

    public void setUsedCables(List<Cable> usedCables) {
        this.usedCables = usedCables;
    }

    //---------------------------------------------------------------------
    //---------------------------------------------------------------------
    //---------------------------------------------------------------------

    public void assignCablesToEdges(List<Cable> availableCables){

        Cable currentCable = findBestCable(availableCables);

        int clients = finishNode.getNumberOfClients();

        for(Edge edge:usedEdges){
            int currentCapacity = 0;
            while( currentCapacity < clients){

                addCable(currentCable);
                currentCapacity = currentCapacity + currentCable.getCapacity();
            }
        }
    }

    public void addCable(Cable cable){
        usedCables.add(cable);
        setTotalCapacity(getTotalCapacity() + cable.getCapacity());
    }

    public Cable findBestCable(List<Cable> cableList){

        Cable bestCable = null;

        for (Cable currentCable:cableList) {
            if (isNull(bestCable)) {
                bestCable = currentCable;
            }
            else if(bestCable.getRatio() < currentCable.getRatio() ){
                bestCable = currentCable;
            }
        }
        return bestCable;
    }

    public void printOnConsole() {
        System.out.println("Od punktu: " + startNode.getNodeID() + " Do punktu: " + finishNode.getNodeID()  + " Ilosc krawedzi:" + usedEdges.size() + " Ilosc kabli: " + usedCables.size() + " Pojemnosc kabli: " + totalCapacity);
    }
}
