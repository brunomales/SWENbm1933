
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class Screen2 {
        // Declaration of UI components 
        private static DatePicker datePicker = new DatePicker();
        private static DatePicker datePicker1 = new DatePicker();
        private static TextField tfWeight = new TextField();
        private static TextField tfCalories = new TextField();
        private static TextField tfFood = new TextField();
        private static TextField tfCount = new TextField();
        private static TextArea taFood = new TextArea();
        private static TextArea taLog = new TextArea();
        private static TextArea taDateLog = new TextArea();
        private static Button btnLogData = new Button("Log Data");
        private static Button btnDisplayDate = new Button("Display Date");
        // Displays the screen, setting up the UI
        public static void display() {
                resetLogData();
                Stage screen2Stage = new Stage();
                screen2Stage.setTitle("Daily Log");

                // Setting prompt text for text fields
                tfWeight.setPromptText("Enter Weight:");
                tfCalories.setPromptText("Enter Calories:");
                tfFood.setPromptText("Enter Food:");
                tfCount.setPromptText("Enter Count:");
                taFood.setPrefSize(200, 400);
                taLog.setPrefSize(200, 400);

                taFood.setDisable(true);
                taFood.setStyle("-fx-opacity: 1.0; -fx-text-fill: black;");

                taLog.setDisable(true);
                taLog.setStyle("-fx-opacity: 1.0; -fx-text-fill: black;");

                // Layout setup for adding data log
                taDateLog.setDisable(true);
                taDateLog.setStyle("-fx-opacity: 1.0; -fx-text-fill: black;");
                VBox leftLayout = new VBox(10);
                leftLayout.getChildren().addAll(
                                new HBox(10, new Label("ADD DATA TO LOG")),
                                new HBox(10, datePicker),
                                new HBox(10, tfWeight),
                                new HBox(10, tfCalories),
                                new HBox(10, tfFood),
                                new HBox(10, tfCount),
                                btnLogData);

                                // Layout setup for displaying data by date
                btnLogData.setOnAction(e -> SaveToCSV());
                HBox rightLayout = new HBox(10);
                rightLayout.getChildren().addAll(
                                new VBox(10,
                                                new Label("PICK DATE TO DISPLAY"),
                                                datePicker1,
                                                btnDisplayDate),
                                taDateLog);
                btnDisplayDate.setOnAction(e -> display(taDateLog));
                HBox.setMargin(taLog, new Insets(10, 10, 10, 10));
                rightLayout.setAlignment(Pos.TOP_LEFT);

                // Main layout setup and scene creation
                BorderPane layout = new BorderPane();
                layout.setPadding(new Insets(20));
                layout.setLeft(leftLayout);
                layout.setRight(rightLayout);

                Scene scene = new Scene(layout, 1000, 500);
                screen2Stage.setScene(scene);
                File file = new File("log.csv");
                createCSVFile(file);
                screen2Stage.show();
        }

        // Attempts to create a CSV file and logs its creation status
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

        // Saves user input to the CSV file, handling different cases for logging data
        public static void SaveToCSV() {
                // Implementation includes reading from and writing to the CSV file to update or add new log entries
                String date = datePicker.getValue().toString();
                File file = new File("log.csv");
                String date2 = "";
                int num = 0;
                String w = "";
                String defaultWeight = "150.0";
                String defaultCalorieLimit = "2000.0";
                String c = "";
                try (BufferedReader br = new BufferedReader(new FileReader(file))) {

                        String line;
                        while ((line = br.readLine()) != null) {
                                String[] parts = line.split(",");
                                date2 = parts[0];
                                if (date.equals(date2) && num == 0) {
                                        w = tfWeight.getText().isEmpty() ? defaultWeight : tfWeight.getText();
                                        c = tfCalories.getText().isEmpty() ? defaultCalorieLimit : tfCalories.getText();
                                        num++;
                                } else {
                                        try {
                                                w = findMostRecentWeight(date, file);
                                                c = findMostRecentCalorieLimit(date, file);
                                        } catch (IOException e) {
                                                e.printStackTrace();
                                        }
                                }
                        }
                } catch (Exception e) {
                        // TODO: handle exception
                }

                String f = tfFood.getText();
                String co = tfCount.getText();

                try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {

                        writer.write(String.join(",", date, "w", w));
                        writer.newLine();

                        writer.write(String.join(",", date, "c", c));
                        writer.newLine();

                        if (!f.isEmpty() && !co.isEmpty()) {

                                writer.write(String.join(",", date, "f", f, co));
                                writer.newLine();
                        } else if (!f.isEmpty()) {
                                writer.write(String.join(",", date, "f", f));
                                writer.newLine();
                        }
                } catch (IOException e) {
                        e.printStackTrace();
                }
                resetLogData();
        }

        // Finds the most recent weight entry before or on the selected date
        private static String findMostRecentWeight(String selectedDate, File file) throws IOException {
                String mostRecentWeight = "150.0";
                String lastDateWithWeight = "";
                try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                        String line;
                        while ((line = br.readLine()) != null) {
                                String[] parts = line.split(",");

                                if (parts[1].equals("w")) {

                                        if (parts[0].compareTo(selectedDate) <= 0) {

                                                if (parts[0].compareTo(lastDateWithWeight) >= 0) {
                                                        mostRecentWeight = parts[2];
                                                        lastDateWithWeight = parts[0];
                                                }
                                        }
                                }
                        }
                }
                return mostRecentWeight;
        }

        // Finds the most recent calorie limit entry before or on the selected date
        private static String findMostRecentCalorieLimit(String selectedDate, File file) throws IOException {
                String mostRecentCalorieLimit = "2000.0";
                String lastDateWithCalorieLimit = "";
                try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                        String line;
                        while ((line = br.readLine()) != null) {
                                String[] parts = line.split(",");

                                if (parts[1].equals("c")) {

                                        if (parts[0].compareTo(selectedDate) <= 0) {

                                                if (parts[0].compareTo(lastDateWithCalorieLimit) >= 0) {
                                                        mostRecentCalorieLimit = parts[2];
                                                        lastDateWithCalorieLimit = parts[0];
                                                        System.out.println("Food:" + mostRecentCalorieLimit);
                                                        System.out.println("Food2:" + lastDateWithCalorieLimit);
                                                }
                                        }
                                }
                        }
                }
                return mostRecentCalorieLimit;
        }

        // Resets input fields and sets both date pickers to the current date
        private static void resetLogData() {
                tfWeight.clear();
                tfCalories.clear();
                tfFood.clear();
                tfCount.clear();

                datePicker.setValue(LocalDate.now());
                datePicker1.setValue(LocalDate.now());

                taFood.clear();
                taLog.clear();
        }

        // Calculates and returns the percentage of calories consumed for a given food item relative to the calorie limit
        private static double calcCaloriesPercentage(String name, double count, String calorieLimit) {
                File file = new File("foods.csv");
                double calPerc = 0.0;
                double foodCal = 0.0;

                double calLim = Double.parseDouble(calorieLimit);
                try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                        String line;
                        while ((line = br.readLine()) != null) {
                                String[] list = line.split(",");

                                if (list[0].equals("b") || list[0].equals("r")) {
                                        if (list[1].equals(name)) {
                                                foodCal += (Double.parseDouble(list[2]) * count);
                                        }
                                }
                        }

                } catch (Exception e) {
                        System.out.println(e.getMessage());
                }

                calPerc = (foodCal / calLim) * 100;

                System.out.println("Calorie Percentage: " + calPerc);
                return calPerc;
        }

        // Displays the log for the selected date in the specified text area
        private static void display(TextArea textArea) {
                File file = new File("log.csv");
                String date = datePicker1.getValue().toString();
                try {
                        String weightForDate = findMostRecentWeight(date, file);
                        StringBuilder sb = new StringBuilder("Weight: " + weightForDate + "\n");

                        String calorieLimitForDate = findMostRecentCalorieLimit(date, file);
                        sb.append("Calorie Limit: " + calorieLimitForDate + "\n");

                        List<String> foodEntries = new ArrayList<>();
                        List<String> foodCount = new ArrayList<>();
                        double calPerc = 0;

                        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                                String line;
                                while ((line = br.readLine()) != null) {
                                        String[] list = line.split(",");
                                        if (list[0].equals(date)) {
                                                if (list[1].equals("f")) {
                                                        foodEntries.add(list[2]);
                                                        foodCount.add(list[3]);
                                                        calPerc = calcCaloriesPercentage(list[2],
                                                                        Double.parseDouble(list[3]),
                                                                        calorieLimitForDate);
                                                }
                                        }
                                }
                        } catch (IOException e) {
                                e.printStackTrace();
                        }

                        for (String food : foodEntries) {
                                int num = 0;
                                sb.append("Food: ").append(food).append("\n");
                                sb.append("Count: ").append(foodCount.get(num)).append("\n");
                                num++;
                        }
                        sb.append("Calori Percentage for this date: " + calPerc + " %");
                        textArea.setText(sb.toString());
                } catch (IOException e) {
                        e.printStackTrace();
                }
        }

}
