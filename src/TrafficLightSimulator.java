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

public class TrafficLightSimulator extends Application {

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
        Committed out because it is not being ran now anyways
         */
        // when a button is selected, run the action
        btRed.setOnAction(e -> System.out.println(btRed.getText()));
        btYellow.setOnAction(e -> System.out.println(btYellow.getText()));
        btGreen.setOnAction(e -> System.out.println(btGreen.getText()));

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

            //TODO: replace with circles

            // create the circles
            for (int i = 1; i <= 3; i++) {
                Circle circle = new Circle();
//        circle.setCenterX(50);
//        circle.setCenterY(50);

                circle.setCenterX(20 + i * 25);
                circle.setCenterY(20 + i * 25);

                circle.setRadius(20);
                circle.setStroke(Color.BLACK);
                circle.setFill(Color.WHITE);

                paneForCircles.getChildren().addAll(circle);
            }
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


//            // when a button is selected, run the action
//            pane.btBlack.setOnAction(event -> {
//                if (btBlack.isSelected()) {
//                    System.out.println(btBlack.getText());
//                }
//            });
//            btBlack.setOnAction(event -> {
//                if (btPurple.isSelected()) {
//                    System.out.println(btPurple.getText());
//                }
//            });
//            btBlack.setOnAction(event -> {
//                if (btGrey.isSelected()) {
//                    System.out.println(btGrey.getText());
//                }
//            });

            return pane;
        }

    }
}

