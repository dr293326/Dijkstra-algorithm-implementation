import java.io.File;
import java.io.FileNotFoundException;
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


    public Network() {
        this.networkID = networkCounter + 1;
        networkCounter++;
    }

    public void readFromFile(String pathFile) {
        boolean isNode = false, isEdge = false;

        if(!isNull(pathFile))
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

                            if (result[1].contains("WEZLY") && isNode == false) {
                                isNode = true;
                                isEdge = false;
                            }
                            if (result[1].contains("LACZA")) {
                                isNode = false;
                                isEdge = true;
                            }

                            if (isNode == true) {
                                //TODO zrobic try parse do intow
                            }
                        }
                    }
                }
            } catch (FileNotFoundException ex) {
                System.out.println("Nie znaleziono pliku o podanej sciezce!");
            }
    }
}
