import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class TrafficLightSimulator extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override// Override the start method in the Application class
    public void start(Stage primaryStage) throws Exception {
        // Hold the 3 radio buttons in an HBox
        HBox hBox = new HBox();
        hBox.setSpacing(10);
        hBox.setAlignment(Pos.BASELINE_CENTER);
        Button btRed = new Button("Red");
        Button btYellow = new Button("Yellow");
        Button btGreen = new Button("Green");
        hBox.getChildren().addAll(btRed, btYellow, btGreen);

        // Create and register the handler
        btRed.setOnAction(new EventHandler<ActionEvent>() {
            @Override // Override the handle method
            public void handle(ActionEvent e) {
                System.out.println("RED");
            }
        });

        btYellow.setOnAction(new EventHandler<ActionEvent>() {
            @Override // Override the handle method
            public void handle(ActionEvent e) {
                System.out.println("YELLOW");
            }
        });

        btGreen.setOnAction(new EventHandler<ActionEvent>() {
            @Override // Override the handle method
            public void handle(ActionEvent e) {
                System.out.println("GREEN");
            }
        });

        // Create a scene and place it in the stage
        Scene scene = new Scene(hBox, 300, 300);
        primaryStage.setTitle("TrafficLightSimulator"); // Set title
        primaryStage.setScene(scene); // Place the scene in the stage
        primaryStage.show(); // Display the stage
    }
}

