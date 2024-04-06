package ObserverPattern;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ProjectGui extends Application {
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Screen Chooser");

        Model model = new Model();

        View view = new View(primaryStage, model);
        model.addObserver(view);

        view.getScreen1Button().setOnAction(e -> {
            model.setCurrentScreen(1);
            Screen1.display();
        });
        view.getScreen2Button().setOnAction(e -> {
            model.setCurrentScreen(2);
            Screen2.display();
        });

        Scene scene = new Scene(view.getMainLayout(), 300, 200);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

