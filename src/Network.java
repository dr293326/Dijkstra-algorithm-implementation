import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.util.Objects.isNull;

public class Network {
    private static long networkCounter = 0;
    private long networkID;
    private List<Edge> edgeList = new ArrayList<Edge>();
    private List<Node> nodeList = new ArrayList<Node>();
    private File file;


    public Network() {
        this.networkID = networkCounter + 1;
        networkCounter++;
    }

    public void readFromFile(String pathFile) {
        if(!isNull(pathFile))
        {
            try {
                this.file = new File(pathFile);
                Scanner sc = new Scanner(file);
                //TODO: nie koniecznie uzywac tutaj klasy Scanner
                //TODO: proba odczytania z pliku i zmapowanie zmiennych za pomocą line.split
                //TODO: trzeba odszukac kluczowych slow WEZLY i LACZA
                //TODO: nalezy tez uwzglednic omijanie linijek z hashem #
                }
                catch(FileNotFoundException e){
                    System.out.println("Nie znaleziono pliku o podanej sciezce!");
                }

        }
    }
}
