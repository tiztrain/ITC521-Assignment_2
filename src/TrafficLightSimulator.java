import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
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

        /* this toggles the radio buttons on and off if another has been selected otherwise all the radio buttons
        will stay on
         */
        ToggleGroup group = new ToggleGroup();
        btRed.setToggleGroup(group);
        btYellow.setToggleGroup(group);
        btGreen.setToggleGroup(group);

        // when a button is selected, run the action
        btRed.setOnAction(event -> {
            if (btRed.isSelected()) {
                System.out.println(btRed.getText());
            }
        });
        btRed.setOnAction(event -> {
            if (btYellow.isSelected()) {
                System.out.println(btYellow.getText());
            }
        });
        btRed.setOnAction(event -> {
            if (btGreen.isSelected()) {
                System.out.println(btGreen.getText());
            }
        });

        return pane;
    }

    @Override// Override the start method in the Application class
    public void start(Stage primaryStage) throws Exception {
        // create a traffic pane object
        TrafficPane trafficPane = new TrafficPane();

        // Create a scene and add the panes to the stage
        Scene scene = new Scene(trafficPane.getTrafficPane(), 300, 300);

        primaryStage.setTitle("TrafficLightSimulator"); // Set title
        primaryStage.setScene(scene); // Place the scene in the stage
        primaryStage.show(); // Display the stage
    }

    public class TrafficPane extends TrafficLightSimulator {
        // build the traffic pane
        public BorderPane getTrafficPane() {
            // use the ButtonPane as the parent pane
            BorderPane pane = super.getButtonPane();

            // Create the vBox, set the spacing in between the circles and set the alignment
            VBox paneForCircles = new VBox();
            // Spacing in between circles
            paneForCircles.setSpacing(5);
            // Padding around everything else
            paneForCircles.setPadding(new Insets(20));
            paneForCircles.setAlignment(Pos.TOP_CENTER);
            // Create the 3 radio buttons
            //TODO: replace with circles
            RadioButton btBlack = new RadioButton("Black");
            RadioButton btPurple = new RadioButton("Purple");
            RadioButton btGrey = new RadioButton("Grey");
            // Add the circles to the vBox
            paneForCircles.getChildren().addAll(btBlack, btPurple, btGrey);
            // set pane at bottom of stage
            pane.setTop(paneForCircles);


            // when a button is selected, run the action
            btBlack.setOnAction(event -> {
                if (btBlack.isSelected()) {
                    System.out.println(btBlack.getText());
                }
            });
            btBlack.setOnAction(event -> {
                if (btPurple.isSelected()) {
                    System.out.println(btPurple.getText());
                }
            });
            btBlack.setOnAction(event -> {
                if (btGrey.isSelected()) {
                    System.out.println(btGrey.getText());
                }
            });

            return pane;
        }

    }


//    // The handler method which the to be used when a button is pressed
//    public void handleButtonAction(ActionEvent event){
//        Button button = (Button)event.getSource();
//        System.out.println(button.getText());
//    }
}

