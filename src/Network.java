import java.io.File;
import java.io.FileNotFoundException;
import java.io.LineNumberReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.text.ParseException;
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

    public void readFromFile(String pathFile) {
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
                                    edgeList.add(tmpedge);

                                }
                            }
                            catch (NumberFormatException nfex) {
                                System.out.println("Wystapil blad podczas wczytywania grafu z pliku!");
                                System.out.println("Wyjatek :" + nfex.getMessage());
                            }
                        }
                    }
                }
            }

                //TODO: trzeba odszukac kluczowych slow WEZLY i LACZA
            catch(FileNotFoundException ex){
                System.out.println("Nie znaleziono pliku o podanej sciezce!");
            }
        }
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
}
