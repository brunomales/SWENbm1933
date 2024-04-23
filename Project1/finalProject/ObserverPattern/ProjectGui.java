import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ProjectGui extends Application {
    private static Button addFood = new Button("AddFood");
    private static Button dLog = new Button("Daily Log");
    private static Button pLog = new Button("Progress");

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Screen Chooser");

        Label titleLabel = new Label("Diet Manager");
        titleLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;"); // Style the title label
        VBox titleBox = new VBox(titleLabel);
        titleBox.setAlignment(Pos.CENTER);

        VBox box = new VBox(10);
        box.setAlignment(Pos.CENTER);
        box.getChildren().addAll(titleBox, addFood, dLog, pLog); // Include titleBox in the VBox

        addFood.setOnAction(e -> {
            Screen1.display();
        });
        dLog.setOnAction(e -> {
            Screen2.display();
        });
        pLog.setOnAction(e -> {
            Progress.display();
        });

        Scene scene = new Scene(box, 300, 200);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}