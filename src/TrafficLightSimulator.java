import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

/* TODO:
 *  -clean up code
 *   */


public class TrafficLightSimulator extends Application {
    protected Circle circle1 = new Circle();
    protected Circle circle2 = new Circle();
    protected Circle circle3 = new Circle();


    public static void main(String[] args) {
        launch(args);
    }

    public BorderPane getButtonPane() {
        // Create pane for hbox
        BorderPane pane = new BorderPane();

        // Create the hbox, set the spacing in between the buttons and set the alignment
        HBox paneForRadioButtons = new HBox();
        // Spacing in between buttons
        paneForRadioButtons.setSpacing(15);
        // Padding around everything else
        paneForRadioButtons.setPadding(new Insets(20));
        paneForRadioButtons.setAlignment(Pos.BASELINE_CENTER);
        // Create the 3 radio buttons
        RadioButton btRed = new RadioButton("Red");
        RadioButton btYellow = new RadioButton("Yellow");
        RadioButton btGreen = new RadioButton("Green");
        // Add the buttons to the hbox
        paneForRadioButtons.getChildren().addAll(btRed, btYellow, btGreen);
        // set pane at bottom of stage
        pane.setBottom(paneForRadioButtons);

        pane.setStyle("-fx-border-color: red");

        /* this toggles the radio buttons on and off if another has been selected otherwise all the radio buttons
        will stay on
         */
        ToggleGroup group = new ToggleGroup();
        btRed.setToggleGroup(group);
        btYellow.setToggleGroup(group);
        btGreen.setToggleGroup(group);

        /*
        Sets the colours of the circles depending on which radio button was selected
         */
        btRed.setOnAction(e -> {
            circle1.setFill(Color.RED);
            circle2.setFill(Color.WHITE);
            circle3.setFill(Color.WHITE);
        });
        btYellow.setOnAction(e -> {
            circle1.setFill(Color.WHITE);
            circle2.setFill(Color.YELLOW);
            circle3.setFill(Color.WHITE);
        });
        btGreen.setOnAction(e -> {
            circle1.setFill(Color.WHITE);
            circle2.setFill(Color.WHITE);
            circle3.setFill(Color.GREEN);
        });

        return pane;
    }

    @Override// Override the start method in the Application class
    public void start(Stage primaryStage) throws Exception {
        // create a traffic pane object
        TrafficPane trafficPane = new TrafficPane();

        // Create a scene and add the panes to the stage
        Scene scene = new Scene(trafficPane.getTrafficPane(), 300, 300);

        primaryStage.setTitle("Traffic Light Simulator"); // Set title
        primaryStage.setScene(scene); // Place the scene in the stage
        primaryStage.show(); // Display the stage
    }

    public class TrafficPane extends TrafficLightSimulator {
        // build the traffic pane
        public BorderPane getTrafficPane() {
            // use the ButtonPane as the parent pane
            BorderPane pane = super.getButtonPane();

            // Create the vBox, set the spacing in between the circles and set the alignment
            VBox paneForCircles = new VBox(5);
            // Padding around everything else
            paneForCircles.setPadding(new Insets(20));
            // Centres the circles in the box
            paneForCircles.setAlignment(Pos.TOP_CENTER);

            // setup the circles
            circle1.setCenterX(20 + 25);
            circle1.setCenterY(20 + 25);
            circle1.setRadius(20);
            circle1.setStroke(Color.BLACK);
            circle1.setFill(Color.WHITE);
            circle2.setCenterX(20 + 25);
            circle2.setCenterY(20 + 25);
            circle2.setRadius(20);
            circle2.setStroke(Color.BLACK);
            circle2.setFill(Color.WHITE);
            circle3.setCenterX(20 + 25);
            circle3.setCenterY(20 + 25);
            circle3.setRadius(20);
            circle3.setStroke(Color.BLACK);
            circle3.setFill(Color.WHITE);

            paneForCircles.getChildren().addAll(circle1, circle2, circle3);
                
//            // Create the 3 radio buttons

//            RadioButton btBlack = new RadioButton("Black");
//            RadioButton btPurple = new RadioButton("Purple");
//            RadioButton btGrey = new RadioButton("Grey");
//            // Add the circles to the vBox
//            paneForCircles.getChildren().addAll(btBlack, btPurple, btGrey);
            // draws pane at top of stage
            pane.setTop(paneForCircles);
            paneForCircles.setStyle("-fx-border-color: black");
            //paneForCircles.setMaxWidth(100);

            // sets the margins of space for each side of the pane
            BorderPane.setMargin(paneForCircles, new Insets(25, 100, 25, 100));

            //paneForCircles.prefWidthProperty().bind(primaryStage.widthProperty().multiple(0.5));


//            btRed.setOnAction(e -> System.out.println(btRed.getText()));
//            btYellow.setOnAction(e -> System.out.println(btYellow.getText()));
//            btGreen.setOnAction(e -> System.out.println(btGreen.getText()));

            return pane;
        }

    }
}

