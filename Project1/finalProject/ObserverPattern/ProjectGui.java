

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ProjectGui extends Application {
    private static Button addFood = new Button ("AddFood");
    private static Button dLog = new Button ("Daily Log");
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Screen Chooser");

        Model model = new Model();

        View view = new View(model);
        model.registerObserver(view);
        VBox box = new VBox();
        box.getChildren().addAll(addFood, dLog);

        addFood.setOnAction(e -> {
            Screen1.display();
        });
        dLog.setOnAction(e -> {
            Screen2.display();
        });

        Scene scene = new Scene(box, 300, 200);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

