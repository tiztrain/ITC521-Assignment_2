import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class TrafficLightSimulator extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    public BorderPane getPane() {
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

//        // The commented out below has been replaced by the simplier handleButtonAction method below
//        btRed.setOnAction(event -> handleButtonAction(event));
//        btRed.setOnAction(new EventHandler<ActionEvent>() {
//            @Override // Override the handle method
//            public void handle(ActionEvent e) {
//                System.out.println("RED");
//            }
//        });
//        btYellow.setOnAction(event -> handleButtonAction(event));
//        btYellow.setOnAction(new EventHandler<ActionEvent>() {
//            @Override // Override the handle method
//            public void handle(ActionEvent e) {
//                System.out.println("YELLOW");
//            }
//        });
//        btGreen.setOnAction(event -> handleButtonAction(event));
//        btGreen.setOnAction(new EventHandler<ActionEvent>() {
//            @Override // Override the handle method
//            public void handle(ActionEvent e) {
//                System.out.println("GREEN");
//            }
//        });

        return pane;
    }

    @Override// Override the start method in the Application class
    public void start(Stage primaryStage) throws Exception {
        // Create a scene and place it in the stage
        Scene scene = new Scene(getPane(), 300, 300);
        primaryStage.setTitle("TrafficLightSimulator"); // Set title
        primaryStage.setScene(scene); // Place the scene in the stage
        primaryStage.show(); // Display the stage
    }


//    // The handler method which the to be used when a button is pressed
//    public void handleButtonAction(ActionEvent event){
//        Button button = (Button)event.getSource();
//        System.out.println(button.getText());
//    }
}

