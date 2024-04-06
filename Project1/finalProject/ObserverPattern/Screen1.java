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
import javafx.scene.control.Label;

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

        style();

        HBox textAreasLayout = new HBox(10);
        textAreasLayout.setPadding(new Insets(10));
        textAreasLayout.getChildren().addAll(new ScrollPane(taFood), new ScrollPane(taRecipe));

        VBox textFieldLayout = new VBox(10);
        textFieldLayout.getChildren().addAll(
                new HBox(10,  tfEnterFood),
                new HBox(10,  tfEnterCalories),
                new HBox(10,  tfEnterFat),
                new HBox(10,  tfEnterCarb),
                new HBox(10,  tfEnterProtein),
                btnAddFood
        );

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

        /*btnAddFood.setStyle("-fx-background-color: transparent; -fx-border-color: #102f49; -fx-border-width: 2px; -fx-border-radius: 5px; -fx-padding: 5px 10px; -fx-text-fill: #102f49;");
        btnAddFood.setOnMouseEntered(e -> btnAddFood.setStyle("-fx-background-color: #102f49; -fx-text-fill: #fafafa; -fx-border-color: #102f49; -fx-border-width: 2px; -fx-border-radius: 5px;"));
        btnAddFood.setOnMouseExited(e -> btnAddFood.setStyle("-fx-background-color: transparent; -fx-border-color: #102f49; -fx-border-width: 2px; -fx-border-radius: 5px; -fx-padding: 5px 10px; -fx-text-fill: #102f49;"));*/


        HBox mainLayout = new HBox(10);
        mainLayout.getChildren().addAll(textFieldLayout, textAreasLayout, rightComponentsLayout);

        Scene scene = new Scene(mainLayout, 800, 400);
        screen1Stage.setScene(scene);
        screen1Stage.show();
        File file = new File("foods.csv");
        createCSVFile(file);
        readCSVFile(file);
        displayFood(taFood);
        displayRecipe(taRecipe);
    }



    private static void style(){
        taFood.setPrefSize(200, 150);
        taFood.setDisable(true);
        taFood.setStyle("-fx-opacity: 1.0; -fx-text-fill: black;");

        taRecipe.setPrefSize(200, 150);
        taRecipe.setDisable(true);
        taRecipe.setStyle("-fx-opacity: 1.0; -fx-text-fill: black;");

        recipeTextArea.setDisable(true);
        recipeTextArea.setStyle("-fx-opacity: 1.0; -fx-text-fill: black;");
        tfEnterFood.setPromptText("Enter food");
        tfEnterFood.setStyle("-fx-text-fill: gray;");
        tfEnterFood.setMaxWidth(150);

        tfEnterCalories.setPromptText("Enter calories");
        tfEnterCalories.setStyle("-fx-text-fill: gray;");
        tfEnterCalories.setMaxWidth(150);

        tfEnterFat.setPromptText("Enter fat");
        tfEnterFat.setStyle("-fx-text-fill: gray;");
        tfEnterFat.setMaxWidth(150);

        tfEnterCarb.setPromptText("Enter carbs");
        tfEnterCarb.setStyle("-fx-text-fill: gray;");
        tfEnterCarb.setMaxWidth(150);

        tfEnterProtein.setPromptText("Enter  protein");
        tfEnterProtein.setStyle("-fx-text-fill: gray;");
        tfEnterProtein.setMaxWidth(150);

        tfEnterFood.setPrefWidth(200);
        btnAddFood.setOnAction(e -> saveDataToCSV());
        addButtonToRecipe.setOnAction(e -> addFoodToRecipe());
        addRecipeButton.setOnAction(e -> addRecipe());

        foodNameTextField.setPromptText("Enter Food");
        recipeNameTextField.setPromptText("Enter recipe name");
        foodNameTextField.setMaxWidth(150);
        recipeTextArea.setMaxWidth(150);
    }
    private static void createCSVFile(File file) {
        try {
            boolean created = file.createNewFile();
            if (created) {
                System.out.println("CSV file created successfully.");
            } else {
                System.out.println("CSV file already exists.");
            }
        } catch (IOException e) {
            System.err.println("Error occurred while creating the CSV file: " + e.getMessage());
        }
    }
    private static List<String[]> readCSVFile(File file) {
        List<String[]> data = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] row = line.split(",");
                data.add(row);
            }
            System.out.println("CSV file read successfully.");
        } catch (IOException e) {
            System.err.println("Error occurred while reading the CSV file: " + e.getMessage());
        }
        return data;
    }
    private static void saveDataToCSV() {
        if (isFoodInputValid()){
        File file = new File("foods.csv");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
            String data = "b," + tfEnterFood.getText() + "," + tfEnterCalories.getText() + "," + tfEnterFat.getText() + "," + tfEnterCarb.getText() + "," + tfEnterProtein.getText();
            writer.write(data);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        displayFood(taFood);
        tfEnterProtein.setText("");
        tfEnterCarb.setText("");
        tfEnterCalories.setText("");
        tfEnterFood.setText("");
        tfEnterFat.setText("");
        }else {
            showAlert("Please enter all data and try again!");
        }
    }
    private static void displayFood(TextArea textArea) {
        File file = new File("foods.csv");
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                String[] list = line.split(",");
                if (Objects.equals(list[0], "b")){
                    sb.append(list[1]).append("\n");
                }
            }
            textArea.setText(sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static void displayRecipe(TextArea textArea) {
        File file = new File("foods.csv");
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                String[] list = line.split(",");
                if (Objects.equals(list[0], "r")){
                    sb.append(list[1]).append("\n");
                }
            }
            textArea.setText(sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static void addFoodToRecipe(){
        if (isFoodToRecInputValid()){
            String food = "";
            String foodName = foodNameTextField.getText();
            int recipeSpiner = recipeSpinner.getValue();
            food = foodName + "," + recipeSpiner + "\n";
            recipeTextArea.appendText(food);
            foodNameTextField.setText("");
            recipeSpinner.getValueFactory().setValue(0);
        }
        else {
            showAlert("Please add food from list!");
        }
    }
    private static List<String> convertTextAreaToList(String taData) {
        String[] linesArray = taData.split("\n");
        return new ArrayList<>(Arrays.asList(linesArray));
    }
    private static void addRecipe(){
        if (isRecipeInputValid()){
            String taData = recipeTextArea.getText();
        ArrayList list = (ArrayList) convertTextAreaToList(taData);
        String recipe = "r," + recipeNameTextField.getText() + ",";

        for (int i = 0; i <list.size(); i++){
            recipe = recipe + list.get(i) + ",";
        }
        saveRecipeToCSV(recipe);
        displayRecipe(taRecipe);
        recipeNameTextField.setText("");
        recipeTextArea.setText("");
        }else {
            showAlert("Please enter all data and try again!");
        }
    }
    private static void saveRecipeToCSV(String recipe) {
            File file = new File("foods.csv");
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
                String data = recipe;
                writer.write(data);
                writer.newLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            displayRecipe(taRecipe);
    }

    public static boolean isFoodInputValid(){
        String foodName = tfEnterFood.getText();
        String calories = tfEnterCalories.getText();
        String fat = tfEnterFat.getText();
        String carb = tfEnterCarb.getText();
        String protein = tfEnterProtein.getText();

        if (foodName.length() < 1){
            return false;
        }
        if (fat.length() < 1){
            return false;
        }
        if (carb.length() < 1){
            return false;
        }
        if (calories.length() < 1){
            return false;
        }
        if (protein.length() < 1){
            return false;
        }
        return true;
    }
    public static boolean isFoodToRecInputValid(){
        String foodName = foodNameTextField.getText();
        int count = recipeSpinner.getValue();

        if (count < 1){
            return false;
        }
        String taText = taFood.getText();
        String[] foods = taText.split("\n");
        for (int i = 0; i < foods.length; i++){
            System.out.println(foods[i]);
            if (Objects.equals(foodName, foods[i])){
                return true;
            }
        }
        return false;
    }
    public static boolean isRecipeInputValid(){
        String recipeName = recipeNameTextField.getText();
        String textArea = recipeTextArea.getText();

        if (recipeName.length() < 1){
            return false;
        }
        if (textArea.length() < 1){
            return false;
        }
        return true;
    }
    private static void showAlert(String message){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setContentText(message);
            alert.show();
    }
}

