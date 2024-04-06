package ObserverPattern;

import javafx.geometry.*;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static java.awt.Color.*;

public class Screen1 {

    private static TextField tfEnterFood = new TextField();
    private static TextField tfEnterCalories = new TextField();
    private static TextField tfEnterFat = new TextField();
    private static TextField tfEnterCarb = new TextField();
    private static TextField tfEnterProtein = new TextField();
    private static Button addButtonToRecipe = new Button("Add food to recipe");
    private static Button addRecipeButton = new Button("Add Recipe");
    private static Spinner<Integer> recipeSpinner = new Spinner<>(0, 100, 0);
    private static TextField foodNameTextField = new TextField();
    private static TextField recipeNameTextField = new TextField();
    private static TextArea recipeTextArea = new TextArea();
    private static TextArea taFood = new TextArea();
    private static TextArea taRecipe = new TextArea();
    private static Button btnAddFood = new Button("Add Food");

    public static void display() {
        Stage screen1Stage = new Stage();
        screen1Stage.setTitle("Food Collection");

        taFood.setPrefSize(200, 150);
        taFood.setDisable(true);
        taFood.setStyle("-fx-opacity: 1.0; -fx-text-fill: black;");

        taRecipe.setPrefSize(200, 150);
        taRecipe.setDisable(true);
        taRecipe.setStyle("-fx-opacity: 1.0; -fx-text-fill: black;");

        recipeTextArea.setDisable(true);
        recipeTextArea.setStyle("-fx-opacity: 1.0; -fx-text-fill: black;");



        HBox textAreasLayout = new HBox(10);
        textAreasLayout.setPadding(new Insets(10));
        textAreasLayout.getChildren().addAll(new ScrollPane(taFood), new ScrollPane(taRecipe));

        tfEnterFood.setPromptText("Enter food");
        tfEnterFood.setStyle("-fx-text-fill: gray;");

        tfEnterCalories.setPromptText("Enter calories");
        tfEnterCalories.setStyle("-fx-text-fill: gray;");

        tfEnterFat.setPromptText("Enter fat");
        tfEnterFat.setStyle("-fx-text-fill: gray;");

        tfEnterCarb.setPromptText("Enter carbs");
        tfEnterCarb.setStyle("-fx-text-fill: gray;");

        tfEnterProtein.setPromptText("Enter  protein");
        tfEnterProtein.setStyle("-fx-text-fill: gray;");

        tfEnterFood.setPrefWidth(200);
        foodNameTextField.setPromptText("Enter Food");
        recipeNameTextField.setPromptText("Enter recipe name");

        VBox textFieldLayout = new VBox(10);
        textFieldLayout.getChildren().addAll(tfEnterFood,tfEnterCalories,tfEnterFat,tfEnterCarb,tfEnterProtein, btnAddFood);
        textFieldLayout.setAlignment(Pos.CENTER_LEFT);

        VBox rightComponentsLayout = new VBox(10);
        rightComponentsLayout.setPadding(new Insets(10));
        rightComponentsLayout.getChildren().addAll(
                recipeSpinner,
                foodNameTextField,
                addButtonToRecipe,
                recipeTextArea,
                recipeNameTextField,
                addRecipeButton
        );

        BorderPane screen1Layout = new BorderPane();
        screen1Layout.setLeft(textAreasLayout);
        screen1Layout.setTop(textFieldLayout);
        screen1Layout.setRight(rightComponentsLayout);

        Scene scene = new Scene(screen1Layout, 600, 500);
        screen1Stage.setScene(scene);
        screen1Stage.show();

        File file = new File("foods.csv");
    }
}

