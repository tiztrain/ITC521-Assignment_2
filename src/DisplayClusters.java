import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;


public class DisplayClusters extends Application {
    public DisplayClusters() {

    }

    public static void main(String[] args) throws IOException {

        // create a task2 object
        DisplayClusters task2 = new DisplayClusters();

        // call the ScanText method
        ArrayList<Cluster> clusters = task2.ScanText();
        launch(args);

    }


    public void start(Stage primaryStage) throws FileNotFoundException {
        // I need to get used to doing this
        DisplayClusters task2 = new DisplayClusters();
        ArrayList<Cluster> clusters = new ArrayList<>();
        clusters = task2.ScanText();

        // Create a pane to hold the circles
        Pane pane = new Pane();

        // Create each circle and set its properties
        for (int i = 0; i < clusters.size(); i++) {
            // calling each clusters x&y values and assigning them to the variable
            double x = clusters.get(i).getX();
            double y = clusters.get(i).getY();
            String colour = clusters.get(i).getColour();

            // create each circle for each cluster
            Circle circle = new Circle();

            // multiplied the values by 100 so they could be seen more easily
            circle.setCenterX(x * 100);
            circle.setCenterY(y * 100);
            circle.setRadius(2);

            // set each circles colour
            if (colour.equals("RED")) {
                circle.setStroke(Color.RED);
                circle.setFill(Color.RED);
            } else if (colour.equals("GREEN")) {
                circle.setStroke(Color.GREEN);
                circle.setFill(Color.GREEN);
            } else if (colour.equals("BLUE")) {
                circle.setStroke(Color.BLUE);
                circle.setFill(Color.BLUE);
            } else if (colour.equals("ORANGE")) {
                circle.setStroke(Color.ORANGE);
                circle.setFill(Color.ORANGE);
            } else {
                circle.setStroke(Color.BLACK);
                circle.setStroke(Color.BLACK);
            }

            // add circle to pane
            pane.getChildren().add(circle);
        }


        // Create a scene and place it in the stage
        Scene scene = new Scene(pane, 1000, 1000);
        primaryStage.setTitle("Show Circle"); // Set the stage title
        primaryStage.setScene(scene); // Place the scene in the stage
        primaryStage.show(); // Display the stage
    }

    public ArrayList<Cluster> ScanText() throws FileNotFoundException {
        // variables
        String colour;
        // a list to store all the individual items of the text file
        LinkedList<String> valList = new LinkedList<>();
        // create a list of clusters
        ArrayList<Cluster> clusters = new ArrayList<>();

        // Create a File instance
        File inputFile = new File("Cluster.txt");
        if (inputFile.exists()) {

            // Create a Scanner for the file
            Scanner input = new Scanner(inputFile);

            // Read data from the input file
            int i = 0;

            /* while there is another value add to valList and move on to the next
            next for loop will catch any clusters that use strings in the x & y coloumn and remove them from the cluster
             */
            while (input.hasNext()) {
                String tempString;
                // Save value to output variable
                tempString = input.next();
                valList.add(i, tempString);
                i = i + 1;
            }

            // assign 3 values to each cluster and move onto the next
            for (i = 0; i < valList.size(); i = i + 3) {
                /* checking whether the value was numeric or not
                if numeric, create a new cluster, if not then move to next
                 */
                boolean numeric = true;
                // if the value is not numeric the catch stops the program from ending but changes numeric to false
                try {
                    double num = Double.parseDouble(valList.get(i));
                }
                // changes numeric to false if there is an error
                catch (NumberFormatException e) {
                    numeric = false;
                }
                // only runs if numeric is true
                if (numeric) {
                    // create each cluster
                    Cluster cluster = new Cluster(Double.parseDouble(valList.get(i)),
                            Double.parseDouble(valList.get(i + 1)),
                            valList.get(i + 2));

                    // create colour value depending on the cluster number
                    if (valList.get(i + 2).equals("Cluster1")) {
                        cluster.setColour("RED");
                    } else if (valList.get(i + 2).equals("Cluster2")) {
                        cluster.setColour("GREEN");
                    } else if (valList.get(i + 2).equals("Cluster3")) {
                        cluster.setColour("BLUE");
                    } else if (valList.get(i + 2).equals("Cluster4")) {
                        cluster.setColour("ORANGE");
                    } else {
                        cluster.setColour("PURPLE");
                    }

                    // store cluster
                    clusters.add(cluster);
//                    // print cluster
//                    cluster.print();
                } else {
                    continue;
                }
            }
        }

        return clusters;
    }
}

class Cluster {
    double x;
    double y;
    String cluster;
    String colour;

    public Cluster(double x, double y, String cluster) {
        this.x = x;
        this.y = y;
        this.cluster = cluster;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public String getCluster() {
        return cluster;
    }

    public void setCluster(String cluster) {
        this.cluster = cluster;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

//    void print() {
//        System.out.printf("%-15.3f%-15.3f%-15s%-15s\n", x, y, cluster, colour);
//    }
}
