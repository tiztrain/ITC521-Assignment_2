import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class DisplayClusters {
    public DisplayClusters() {

    }

    public static void main(String[] args) throws IOException {
        DisplayClusters task2 = new DisplayClusters();
        task2.ScanText();

    }

    public void ScanText() throws FileNotFoundException {
        // variables
        LinkedList<String> valList = new LinkedList<>();

        // Create a File instance
        File inputFile = new File("Cluster.txt");
        if (inputFile.exists()) {

            // Create a Scanner for the file
            Scanner input = new Scanner(inputFile);

            // Read data from the input file
            int i = 0;

            // while there is another string add value to valList and move on to the next
            while (input.hasNext()) {
                String tempString;
                // Save value to output variable
                tempString = input.next();
                valList.add(i, tempString);
                i = i + 1;
            }

            // create a list of clusters
            List<Cluster> clusters = new ArrayList<>();

            // assign 3 values to each cluster and move onto the next
            for (i = 0; i < valList.size(); i = i + 3) {
                Cluster cluster = new Cluster(valList.get(i), valList.get(i + 1), valList.get(i + 2));

                // store cluster
                clusters.add(cluster);
                // print cluster
                cluster.print();
            }

        }

    }

    class Cluster {
        String x;
        String y;
        String cluster;

        Cluster(String x, String y, String cluster) {
            this.x = x;
            this.y = y;
            this.cluster = cluster;
        }

        void print() {
//            System.out.println(x + ' ' + y + ' ' + cluster);
            System.out.printf("%-15s%-15s%-15s\n", x, y, cluster);
        }
    }

}
