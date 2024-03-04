// Screen2.java
package MVC;

import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class Screen2 {
    public static void display() {
        Stage screen2Stage = new Stage();
        screen2Stage.setTitle("Screen 2 GUI");

        // Create components for Screen 2 GUI
        DatePicker datePicker = new DatePicker();
        TextField tfWeight = new TextField();
        TextField tfCalories = new TextField();
        TextField tfFood = new TextField();
        Button btnAddFood = new Button("Add Food");
        TextArea taFood = new TextArea();
        TextArea taLog = new TextArea(); // New TextArea for the right side
        Button btnLogData = new Button("Log Data");

        // Adjust size of the TextAreas
        taFood.setPrefSize(200, 400); // Set preferred size for the left TextArea
        taLog.setPrefSize(200, 400); // Set preferred size for the right TextArea

        // Create layout for Screen 2 GUI
        VBox leftLayout = new VBox(10);
        leftLayout.getChildren().addAll(
                new HBox(10, new Label("Choose Date:"), datePicker),
                new HBox(10, new Label("Text Field 1:"), tfWeight),
                new HBox(10, new Label("Text Field 2:"), tfCalories),
                new HBox(10, new Label("Text Field 3:"), tfFood),
                btnAddFood,
                taFood,
                btnLogData
        );

        // Create layout for Screen 2 GUI
        HBox rightLayout = new HBox(10);
        rightLayout.getChildren().addAll(
                new Label("Right TextArea"), // Label to distinguish
                taLog,
                new VBox(10,
                        new Label("Date Picker:"),
                        new DatePicker(),
                        new Button("Button")
                ),
                new TextArea()
        );

        // Set margin for the text areas
        HBox.setMargin(taLog, new Insets(10, 10, 10, 10)); // Adjust margins for the first text area

        // Set alignment of the HBox
        rightLayout.setAlignment(Pos.TOP_LEFT); // Align to the top left corner

        // Create layout for Screen 2 GUI
        BorderPane layout = new BorderPane();
        layout.setPadding(new Insets(20));
        layout.setLeft(leftLayout);
        layout.setRight(rightLayout); // Set rightLayout to the right of the BorderPane

        // Set scene
        Scene scene = new Scene(layout, 1300, 800); // Set bigger scene size
        screen2Stage.setScene(scene);
        screen2Stage.show();
    }
}
