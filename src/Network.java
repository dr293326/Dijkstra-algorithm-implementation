import java.io.File;
import java.io.FileNotFoundException;
import java.io.LineNumberReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.util.Objects.isNull;

public class Network {
    private static long networkCounter = 0;
    private long networkID;
    private List<Edge> edgeList = null; //ArrayList<>();
    private List<Node> nodeList = null; // ArrayList<>();
    private File file;
    private int numberOfNodes;
    private int numberOfEdges;


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

                            switch (result[0]) {
                                case "WEZLY":
                                    numberOfNodes = Integer.parseInt(result[2]);
                                    nodeList = new ArrayList<>(numberOfNodes);
                                    for (int i = 0; i < numberOfNodes; i++) {
                                        line = scanner.nextLine();
                                            Node tmp = new Node(Long.parseLong(result[0]), Integer.parseInt(result[1]),
                                                    Integer.parseInt(result[2]));
                                            nodeList.add(tmp);
                                        }
                                    break;

                                /*case "LACZA":
                                    numberOfEdges = Integer.parseInt(result[2]);
                                    for (int i = 0; i < numberOfEdges; i++) {
                                        Edge tmp = new Edge(Long.parseLong(result[0]), 0, "nie wiem",
                                                Integer.parseInt(result[1]), Integer.parseInt(result[1]));
                                        edgeList.add(tmp);
                                    }
                                    break;*/
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
}
