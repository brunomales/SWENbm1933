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
    private static Button eat = new Button("Eat");
    private static Button burn = new Button("Save Exercise");
    private static TextArea taExerscise = new TextArea();
    private static TextArea taFood = new TextArea();
    static LocalDate date;
    static LocalDate date1;
    private static ProgressBar progressBar = new ProgressBar(0);
    private static ProgressBar progressBar1 = new ProgressBar(0);

    static List<Exe> exes = new ArrayList<Exe>();

    private static DatePicker datePicker = new DatePicker();
    private static DatePicker datePicker1 = new DatePicker();
    private static TextField tfWeight = new TextField();
    private static TextField tfCalGoal = new TextField();
    private static TextField tfProteinGoal = new TextField();
    private static TextField tfTime = new TextField();

    private static Button log = new Button("DailyLog");

    private static TextArea logArea = new TextArea();

    private static Button burned = new Button("Burned");
    private static Button burned1 = new Button("RealLogData");

    public static void display() {
        Stage primaryStage = new Stage();
        primaryStage.setTitle("Screen Chooser");

        food.setPromptText("Food Name");
        food.setStyle("-fx-text-fill: gray;");

        exercise.setPromptText("Exercise");
        exercise.setStyle("-fx-text-fill: gray;");

        calBurn.setPromptText("Calories burned");
        calBurn.setStyle("-fx-text-fill: gray;");

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

        eat.setOnAction(e -> eat());

        log.setOnAction(e -> logData());

        burn.setOnAction(e -> saveExercise());

        burned.setOnAction(e -> burned());
        burned1.setOnAction(e -> logData1());

        HBox box1 = new HBox();
        box1.getChildren().addAll(exercise, calBurn, tfTime, burn, burned);


        HBox box2 = new HBox();
        box2.getChildren().addAll(datePicker, datePicker1);
        HBox box3 = new HBox();
        box3.getChildren().addAll(tfWeight, tfCalGoal, tfProteinGoal, log, burned1, logArea);

        logArea.setMaxWidth(180);
        // Create a VBox layout
        VBox sidebar = new VBox();
        sidebar.setSpacing(10);

        // Add the ProgressBar to the VBox
        sidebar.getChildren().addAll(progressBar, progressBar1);

        VBox box11 = new VBox();
        box11.getChildren().addAll(box, box1, sidebar, taExerscise, box2, box3, taFood);

        File file = new File("exercise.csv");
        createCSVFile(file);
        displayFood(taFood);
        displayExe();

        Scene scene = new Scene(box11, 800, 700);
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
            Exe exer = FoodFactory.creatExercise(exName, "basic", null, (burn / 60) * Integer.parseInt(tfTime.getText()));
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
        resetAll();
    }

    public static void burned() {
        int yourWeight = Integer.parseInt(tfWeight.getText());
        Exe ex = FoodFactory.creatExercise("", "recipe", exes, 0);
        int calGoal = Integer.parseInt(tfCalGoal.getText());
        LogEntry log = new LogEntry(exes);
        LogEntry logWithWeight = new WeightDecorator(log, yourWeight, ex.getIntensity());
        LogEntry logWithGoals = new NutrientGoalDecorator(logWithWeight, calGoal,
                Integer.parseInt(tfProteinGoal.getText()), (calGoal - ex.getIntensity()));
        logArea.appendText(logWithGoals.displayLog());
        resetAll();
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

    private static double percent;
    private static double percentW;

    @SuppressWarnings("unlikely-arg-type")
    private static void eat() {
        String foodName = food.getText();
        File file = new File("foods.csv");
        int calGoal = Integer.parseInt(tfCalGoal.getText());
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] list = line.split(",");
                if (Objects.equals(list[0], "b")) {
                    if (Objects.equals(foodName, list[1])) {
                        percent += Double.parseDouble(list[2]);
                        progressBar.setProgress(percent / calGoal);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Please enter all data and try again!");
        }
        resetAll();
    }

    private static void logData() {
        date = datePicker.getValue();
        String foodName = food.getText();
        int weight = Integer.parseInt(tfWeight.getText());
        int cal = Integer.parseInt(tfCalGoal.getText());
        File file = new File("log.csv");
        createCSVFile(file);
        String line = date + ", w, " + weight + ", c, " + cal + ", f, " + foodName;
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
            writer.write(line);
            writer.newLine();
        } catch (IOException e) {
            System.out.println(e);
        }
        String str = "For date: " + date + ", Cal limit: " + weight;
        logArea.appendText(str);
        resetAll();
    }

    private static void logData1(){
        File file = new File("log.csv");
        date1 = datePicker1.getValue();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            String ste;
            while ((line = br.readLine()) != null) {
                String[] list = line.split(",");
                ste = ""+date1;
                if (Objects.equals(list[0], ste)) {
                    String str = "Date: " + list[0] + ", Weight: " + list[2] + ", Cal limit: " + list[4] + ", Food: " + list[6];
                    logArea.appendText(str);
                    resetAll();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
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
            resetAll();
        }
    }

    private static void displayExe() {
        File file = new File("exercise.csv");
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                String[] list = line.split(",");
                if (Objects.equals(list[0], "e")) {
                    sb.append("Exercise: " + list[1]  + ", CalBurned: " + list[2]  +"\n");
                }
            }
            taExerscise.setText(sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
            resetAll();
        }
    }

    private static void resetAll() {
        // Clear text fields
        food.setText("");
        exercise.setText("");
        calBurn.setText("");
        tfWeight.setText("");
        tfCalGoal.setText("");
        tfProteinGoal.setText("");
        tfTime.setText("");

        // Reset date pickers
        datePicker.setValue(null); // Set to null or you can set to LocalDate.now() for today's date
        datePicker1.setValue(null);
    }

    private static void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning");
        alert.setContentText(message);
        alert.show();
    }
}