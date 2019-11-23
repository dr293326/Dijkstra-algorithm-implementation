package model;

public class Cable {

    int cableID;
    int capacity;
    int cost;
    double ratio;

    public Cable(int cableID, int capacity, int cost) {
        this.cableID = cableID;
        this.capacity = capacity;
        this.cost = cost;
    }

    public void setRatio(double ratio) {
        this.ratio = ratio;
    }

    public double getRatio() {
        return ratio;
    }

    public int getCableID() {
        return cableID;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getCost() {
        return cost;
    }

    public void setCableID(int cableID) {
        this.cableID = cableID;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public void printOnConsole(){
        System.out.println("Kabel ID:" + this.cableID + " Pojemnosc:" + this.capacity + " Koszt:" + this.cost + " Ratio: " + this.ratio);
    }
}
