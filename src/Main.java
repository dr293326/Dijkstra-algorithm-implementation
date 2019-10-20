public class Main {

    public static void main(String[] args) {
        //System.out.println("Hello World!");
        System.out.println("");
        Network network = new Network();
        network.readFromFile("resources/example.txt");

        network.printNetwork();
    }
}
