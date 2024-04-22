import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Progress {

    private static TextField food = new TextField();
    private static TextField exercise = new TextField();
    private static TextField calBurn = new TextField();
    private static TextField weight = new TextField();
    private static Button eat = new Button("Eat");
    private static Button burn = new Button("Burn");
    private static TextArea taExerscise = new TextArea();
    private static TextArea taFood = new TextArea();
    static LocalDate date;

    static List<Exe> exes = new ArrayList<Exe>();

    private static DatePicker datePicker = new DatePicker();
    private static DatePicker datePicker1 = new DatePicker();
    private static TextField tfWeight = new TextField();
    private static TextField tfCalGoal = new TextField();
    private static TextField tfProteinGoal = new TextField();
    private static TextField tfTime = new TextField();

    private static Button log = new Button("DailyLog");

    private static TextArea logArea = new TextArea();

    private static Button burned = new Button("Burn3d");

    public static void display() {
        Stage primaryStage = new Stage();
        primaryStage.setTitle("Screen Chooser");

        food.setPromptText("Food Name");
        food.setStyle("-fx-text-fill: gray;");

        exercise.setPromptText("Exercise");
        exercise.setStyle("-fx-text-fill: gray;");

        calBurn.setPromptText("Calories burned");
        calBurn.setStyle("-fx-text-fill: gray;");

        weight.setPromptText("Your Weight");
        weight.setStyle("-fx-text-fill: gray;");

        tfCalGoal.setPromptText("Calories Goal");
        tfCalGoal.setStyle("-fx-text-fill: gray;");

        tfWeight.setPromptText("Your Weight");
        tfWeight.setStyle("-fx-text-fill: gray;");

        tfProteinGoal.setPromptText("Protein Goal");
        tfProteinGoal.setStyle("-fx-text-fill: gray;");

        tfTime.setPromptText("Time of Exercise");
        tfTime.setStyle("-fx-text-fill: gray;");

        HBox box = new HBox();
        box.getChildren().addAll(food, eat);

        log.setOnAction(e -> logData());

        burn.setOnAction(e -> saveExercise());

        burned.setOnAction(e -> burned());

        HBox box1 = new HBox();
        box1.getChildren().addAll(exercise, calBurn, weight, tfTime, burn, burned);

        HBox box2 = new HBox();
        box2.getChildren().addAll(datePicker, datePicker1);
        HBox box3 = new HBox();
        box3.getChildren().addAll(tfWeight, tfCalGoal, tfProteinGoal, log, logArea);

        // Create a VBox layout
        VBox sidebar = new VBox();
        sidebar.setSpacing(10); // Space between elements

        // Create a ProgressBar
        ProgressBar progressBar = new ProgressBar(0); // Initial progress

        // Add the ProgressBar to the VBox
        sidebar.getChildren().add(progressBar);

        // Update the progress value (Example: set to 50%)
        progressBar.setProgress(0.3456);
        VBox box11 = new VBox();
        box11.getChildren().addAll(box, box1, sidebar, taExerscise, box2, box3, taFood);

        File file = new File("exercise.csv");
        createCSVFile(file);
        displayFood(taFood);

        Scene scene = new Scene(box11, 700, 700);
        primaryStage.setScene(scene);
        primaryStage.show();
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

    private static void saveExercise() {
        if (isFoodInputValid()) {
            File file = new File("exercise.csv");
            String exName = exercise.getText();
            int burn = Integer.parseInt(calBurn.getText());
            Exe exer = FoodFactory.creatExercise(exName, "basic", null, burn / Integer.parseInt(tfTime.getText()));
            exes.add(exer);
            taExerscise.appendText("Exercise Name: " + exer.getName() + " & CalBurn: " + exer.getIntensity() + "\n");
            String line = "e," + exer.getName() + "," + exer.getIntensity();
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
                writer.write(line);
                writer.newLine();
            } catch (IOException e) {
                System.out.println(e);
            }
        } else {
            showAlert("Please enter all data and try again!");
        }
    }

    public static void burned() {
      
            int yourWeight = Integer.parseInt(tfWeight.getText());
            Exe ex = FoodFactory.creatExercise("", "recipe", exes, 0);

            LogEntry log = new LogEntry(datePicker1.getValue(), exes);
            LogEntry logWithWeight = new WeightDecorator(log, yourWeight, ex.getIntensity());
            LogEntry logWithGoals = new NutrientGoalDecorator(logWithWeight, Integer.parseInt(tfCalGoal.getText()),
                    Integer.parseInt(tfProteinGoal.getText()));
            logArea.appendText(logWithGoals.displayLog());
        

    }

    public static boolean isFoodInputValid() {
        String calories = exercise.getText();
        String fat = calBurn.getText();

        if (fat.length() < 1) {
            return false;
        }
        if (calories.length() < 1) {
            return false;
        }
        return true;
    }

    private static boolean nameValid() {
        String foodName = food.getText();
        File file = new File("foods.csv");
        String sb = new String();

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] list = line.split(",");
                if (Objects.equals(list[0], "b")) {
                    sb += list[1] + "\n";

                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        String[] linesArray = sb.split("\n");
        for (int i = 0; i < linesArray.length; i++) {
            if (Objects.equals(foodName, linesArray[i])) {
                return true;
            }
        }
        return false;
    }

    private static void logData() {
        date = datePicker.getValue();
        String foodName = food.getText();
        int weight = Integer.parseInt(tfWeight.getText());
        int cal = Integer.parseInt(tfCalGoal.getText());
        int protein = Integer.parseInt(tfProteinGoal.getText());
        File file = new File("log.csv");
        createCSVFile(file);
        String line = date + ", w, " + weight + ", c, " + cal + ", f, " + foodName;
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
            writer.write(line);
            writer.newLine();
        } catch (IOException e) {
            System.out.println(e);
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

    private static void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning");
        alert.setContentText(message);
        alert.show();
    }
}
