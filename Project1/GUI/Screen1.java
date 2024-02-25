// Screen1.java
package project;

import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class Screen1 {
    public static void display() {
        Stage screen1Stage = new Stage();
        screen1Stage.setTitle("Screen 1 GUI");

        // Create text areas
        TextArea taFood = new TextArea();
        TextArea taRecipe = new TextArea();
        taFood.setPrefSize(200, 150);
        taRecipe.setPrefSize(200, 150);

        // Create layout for text areas
        HBox textAreasLayout = new HBox(10);
        textAreasLayout.setPadding(new Insets(10));
        textAreasLayout.getChildren().addAll(new ScrollPane(taFood), new ScrollPane(taRecipe));

        // Create text field and button
        TextField tfEnterFood = new TextField();
        tfEnterFood.setPromptText("Enter food");
        tfEnterFood.setStyle("-fx-text-fill: gray;");

        tfEnterFood.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                // Text field is focused, clear the prompt text and set text color to black
                if (tfEnterFood.getText().equals("Enter text")) {
                    tfEnterFood.setText("");
                }
                tfEnterFood.setStyle("-fx-text-fill: black;");
            } else {
                // Text field lost focus, set prompt text and text color to gray if empty
                if (tfEnterFood.getText().isEmpty()) {
                    tfEnterFood.setText("Enter text");
                    tfEnterFood.setStyle("-fx-text-fill: gray;");
                }
            }
        });

        tfEnterFood.setPrefWidth(200);
        Button btnAddFood = new Button("Add Food");

        // Create layout for text field and button
        VBox textFieldLayout = new VBox(10);
        textFieldLayout.getChildren().addAll(tfEnterFood, btnAddFood);
        textFieldLayout.setAlignment(Pos.CENTER_LEFT);

        Button addButtonToRecipe = new Button("Add food to recipe");
        Button addRecipeButton = new Button("Add Recipe");
        Spinner<Integer> recipeSpinner = new Spinner<>(0, 100, 0);
        TextField foodNameTextField = new TextField("Food name");
        TextField recipeNameTextField = new TextField("Recipe name");
        TextArea recipeTextArea = new TextArea();

        // Create layout for right components
        VBox rightComponentsLayout = new VBox(10);
        rightComponentsLayout.setPadding(new Insets(10));
        rightComponentsLayout.getChildren().addAll(
                addButtonToRecipe,
                addRecipeButton,
                recipeSpinner,
                foodNameTextField,
                recipeNameTextField,
                recipeTextArea
        );

        // Create layout for screen 1
        BorderPane screen1Layout = new BorderPane();
        screen1Layout.setLeft(textAreasLayout);
        screen1Layout.setTop(textFieldLayout);
        screen1Layout.setRight(rightComponentsLayout);

        // Set scene
        Scene scene = new Scene(screen1Layout, 600, 300);
        screen1Stage.setScene(scene);
        screen1Stage.show();
    }
}
