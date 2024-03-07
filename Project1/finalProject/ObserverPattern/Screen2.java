package ObserverPattern;

import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class Screen2 {

    private static DatePicker datePicker = new DatePicker();
    private static TextField tfWeight = new TextField();
    private static TextField tfCalories = new TextField();
    private static TextField tfFood = new TextField();
    private static Button btnAddFood = new Button("Add Food");
    private static TextArea taFood = new TextArea();
    private static TextArea taLog = new TextArea();
    private static TextArea taDateLog = new TextArea();
    private static Button btnLogData = new Button("Log Data");
    private static Button btnDisplayDate = new Button("Display Date");

    public static void display() {
        Stage screen2Stage = new Stage();
        screen2Stage.setTitle("Daily Log");

        tfWeight.setPromptText("Enter Weight:");
        tfCalories.setPromptText("Enter Calories:");
        tfFood.setPromptText("Enter Food:");
        taFood.setPrefSize(200, 400);
        taLog.setPrefSize(200, 400);

        taFood.setDisable(true);
        taFood.setStyle("-fx-opacity: 1.0; -fx-text-fill: black;");

        taLog.setDisable(true);
        taLog.setStyle("-fx-opacity: 1.0; -fx-text-fill: black;");

        taDateLog.setDisable(true);
        taDateLog.setStyle("-fx-opacity: 1.0; -fx-text-fill: black;");

        VBox leftLayout = new VBox(10);
        leftLayout.getChildren().addAll(
                new HBox(10,  new Label("ADD DATA TO LOG")),
                new HBox(10,  datePicker),
                new HBox(10,  tfWeight),
                new HBox(10,  tfCalories),
                new HBox(10,  tfFood),
                btnAddFood,
                taFood,
                btnLogData
        );

        HBox rightLayout = new HBox(10);
        rightLayout.getChildren().addAll(
                taLog,
                new VBox(10,
                        new Label("PICK DATE TO DISPLAY"),
                        new DatePicker(),
                        btnDisplayDate
                ),
                taDateLog
        );

        HBox.setMargin(taLog, new Insets(10, 10, 10, 10));
        rightLayout.setAlignment(Pos.TOP_LEFT);

        BorderPane layout = new BorderPane();
        layout.setPadding(new Insets(20));
        layout.setLeft(leftLayout);
        layout.setRight(rightLayout);

        Scene scene = new Scene(layout, 1000, 500);
        screen2Stage.setScene(scene);
        screen2Stage.show();
    }
}

