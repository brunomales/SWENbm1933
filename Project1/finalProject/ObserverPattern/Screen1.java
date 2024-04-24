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

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Screen1 {

    private static TextField tfEnterFood = new TextField();
    private static TextField tfEnterCalories = new TextField();
    private static TextField tfEnterFat = new TextField();
    private static TextField tfEnterCarb = new TextField();
    private static TextField tfEnterProtein = new TextField();
    private static TextField tfEnterCalories2 = new TextField();
    private static TextField tfEnterFat3 = new TextField();
    private static TextField tfEnterCarb4 = new TextField();
    private static TextField tfEnterProtein5 = new TextField();
    private static Button addButtonToRecipe = new Button("Add food to recipe");
    private static Button addRecipeButton = new Button("Add Recipe");
    private static Spinner<Integer> recipeSpinner = new Spinner<>(0, 100, 0);
    private static TextField foodNameTextField = new TextField();
    private static TextField recipeNameTextField = new TextField();
    private static TextArea recipeTextArea = new TextArea();
    private static TextArea taFood = new TextArea();
    private static TextArea taRecipe = new TextArea();
    private static TextArea taRecipe1 = new TextArea();
    private static Button btnAddFood = new Button("Add Food");

    private static List<Food> ingredients = new ArrayList<>();

    public static void display() {
        Stage screen1Stage = new Stage();
        screen1Stage.setTitle("Food Collection");

        taFood.setPrefSize(200, 150);
        //taFood.setDisable(true);
        taFood.setStyle("-fx-opacity: 1.0; -fx-text-fill: black;");

        taRecipe.setPrefSize(200, 150);
        //taRecipe.setDisable(true);
        taRecipe.setStyle("-fx-opacity: 1.0; -fx-text-fill: black;");

        taRecipe1.setDisable(true);
        taRecipe1.setStyle("-fx-opacity: 1.0; -fx-text-fill: black;");

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

        tfEnterCalories2.setPromptText("Enter calories");
        tfEnterCalories2.setStyle("-fx-text-fill: gray;");

        tfEnterFat3.setPromptText("Enter fat");
        tfEnterFat3.setStyle("-fx-text-fill: gray;");

        tfEnterCarb4.setPromptText("Enter carbs");
        tfEnterCarb4.setStyle("-fx-text-fill: gray;");

        tfEnterProtein5.setPromptText("Enter  protein");
        tfEnterProtein5.setStyle("-fx-text-fill: gray;");
        tfEnterFood.setPrefWidth(200);
        btnAddFood.setOnAction(e -> saveDataToCSV());
        addButtonToRecipe.setOnAction(e -> addFoodToRecipe());
        addRecipeButton.setOnAction(e -> addRecipe());

        foodNameTextField.setPromptText("Enter Food1");
        recipeNameTextField.setPromptText("Enter recipe name");

        VBox textFieldLayout = new VBox(10);
        textFieldLayout.getChildren().addAll(tfEnterFood, tfEnterCalories, tfEnterFat, tfEnterCarb, tfEnterProtein,
                btnAddFood);
        textFieldLayout.setAlignment(Pos.CENTER_LEFT);

        VBox rightComponentsLayout = new VBox(10);
        rightComponentsLayout.setPadding(new Insets(10));
        rightComponentsLayout.getChildren().addAll(
                recipeSpinner,
                foodNameTextField, tfEnterCalories2, tfEnterFat3, tfEnterCarb4, tfEnterProtein5,
                addButtonToRecipe,
                recipeTextArea,
                recipeNameTextField,
                taRecipe1,
                addRecipeButton);

        BorderPane screen1Layout = new BorderPane();
        screen1Layout.setLeft(textAreasLayout);
        screen1Layout.setTop(textFieldLayout);
        screen1Layout.setRight(rightComponentsLayout);

        Scene scene = new Scene(screen1Layout, 600, 800);
        screen1Stage.setScene(scene);
        screen1Stage.show();

        File file = new File("foods.csv");
        createCSVFile(file);
        readCSVFile(file);
        displayFood(taFood);
        displayRecipe(taRecipe);
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
        if (isFoodInputValid()) {
            try {
                String name = tfEnterFood.getText().trim();
                int calories = Integer.parseInt(tfEnterCalories.getText().trim());
                int fat = Integer.parseInt(tfEnterFat.getText().trim());
                int carbs = Integer.parseInt(tfEnterCarb.getText().trim());
                int protein = Integer.parseInt(tfEnterProtein.getText().trim());
                Food food = FoodFactory.createFood("basic", name, null, calories, fat, carbs, protein);
                Model model = new Model();
                View view = new View();
                model.addPropertyChangeListener(view);
                model.addFood(food);
            } catch (NumberFormatException e) {
                System.err.println("Invalid number format: " + e.getMessage());
            }

            // Clear the fields
            tfEnterProtein.setText("");
            tfEnterCarb.setText("");
            tfEnterCalories.setText("");
            tfEnterFood.setText("");
            tfEnterFat.setText("");
        } else {
            showAlert("Please enter all data and try again!");
        }
    }
    public void appendText(String text) {
        taFood.appendText(text + "\n");
    }

    private static void displayFood(TextArea textArea) {
        File file = new File("foods.csv");
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                String[] list = line.split(",");
                if (Objects.equals(list[0], "b")) {
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
                if (Objects.equals(list[0], "r")) {
                    sb.append(list[1]).append("\n");
                }
            }
            textArea.setText(sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void addFoodToRecipe() {
        if (isFoodToRecInputValid()) {
            String foodName = foodNameTextField.getText();
            int calories = Integer.parseInt(tfEnterCalories2.getText()) * recipeSpinner.getValue();
            int fat = Integer.parseInt(tfEnterFat3.getText()) * recipeSpinner.getValue();
            int carbs = Integer.parseInt(tfEnterCarb4.getText()) * recipeSpinner.getValue();
            int protein = Integer.parseInt(tfEnterProtein5.getText()) * recipeSpinner.getValue();
            Food food = FoodFactory.createFood("basic", foodName, null, calories, fat, carbs, protein);
            ingredients.add(food);
            recipeTextArea.appendText(food.getName() + "," + recipeSpinner.getValue() + "\n");
            foodNameTextField.setText("");
            recipeSpinner.getValueFactory().setValue(0);
        } else {
            showAlert("Please add food from list!");
        }
    }

    private static List<String> convertTextAreaToList(String taData) {
        String[] linesArray = taData.split("\n");
        return new ArrayList<>(Arrays.asList(linesArray));
    }


    @SuppressWarnings("rawtypes")
    private static void addRecipe() {
        if (isRecipeInputValid()) {
            String recipeName = recipeNameTextField.getText();
            Food recipe1 = FoodFactory.createFood("recipe", recipeName, ingredients, 0, 0, 0, 0);
            taRecipe1.appendText(
                    "Recipe: " + recipe1.getName() + " with " + ingredients.size() + " ingredients created and Total:"
                            + "\n" + "Calories: " + recipe1.getCalories() + "\n" + "Fat: " + recipe1.getFat() + "\n"
                            + "Protein: " + recipe1.getProtein());
            String taData = recipeTextArea.getText();
            ArrayList list = (ArrayList) convertTextAreaToList(taData);
            String recipe = "r," + recipe1.getName() + ",";
            for (int i = 0; i < list.size(); i++) {
                recipe = recipe + list.get(i) + ",";
            }
            saveRecipeToCSV(recipe + "," + recipe1.getCalories() + "," + recipe1.getCarbohydrates() + ","
                    + recipe1.getFat() + "," + recipe1.getProtein());
            ingredients.clear();
            recipeNameTextField.setText("");
            recipeTextArea.setText("");
        } else {
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
    }

    public static boolean isFoodInputValid() {
        String foodName = tfEnterFood.getText();
        String calories = tfEnterCalories.getText();
        String fat = tfEnterFat.getText();
        String carb = tfEnterCarb.getText();
        String protein = tfEnterProtein.getText();

        if (foodName.length() < 1) {
            return false;
        }
        if (fat.length() < 1) {
            return false;
        }
        if (carb.length() < 1) {
            return false;
        }
        if (calories.length() < 1) {
            return false;
        }
        if (protein.length() < 1) {
            return false;
        }
        return true;
    }

    public static boolean isFoodToRecInputValid() {
        String foodName = foodNameTextField.getText();
        int count = recipeSpinner.getValue();

        if (count < 1) {
            return false;
        }
        String taText = taFood.getText();
        String[] foods = taText.split("\n");
        for (int i = 0; i < foods.length; i++) {
            System.out.println(foods[i]);
            if (Objects.equals(foodName, foods[i])) {
                return true;
            }
        }
        return false;
    }

    public static boolean isRecipeInputValid() {
        String recipeName = recipeNameTextField.getText();
        String textArea = recipeTextArea.getText();

        if (recipeName.length() < 1) {
            return false;
        }
        if (textArea.length() < 1) {
            return false;
        }
        return true;
    }

    private static void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning");
        alert.setContentText(message);
        alert.show();
    }
}
