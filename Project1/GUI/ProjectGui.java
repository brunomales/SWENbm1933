// ProjectGui.java
package MVC;

import javafx.application.Application;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class ProjectGui extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Screen Chooser");

        // Create buttons
        Button screen1Button = new Button("Screen 1");
        Button screen2Button = new Button("Screen 2");

        // Set actions for buttons
        screen1Button.setOnAction(e -> Screen1.display());
        screen2Button.setOnAction(e -> Screen2.display());

        // Create layout for buttons
        VBox buttonLayout = new VBox(10);
        buttonLayout.setAlignment(Pos.CENTER);
        buttonLayout.getChildren().addAll(screen1Button, screen2Button);

        // Create main layout
        BorderPane mainLayout = new BorderPane();
        mainLayout.setCenter(buttonLayout);
        mainLayout.setPadding(new Insets(20));

        // Set scene
        Scene scene = new Scene(mainLayout, 300, 200);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
