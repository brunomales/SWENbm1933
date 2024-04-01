package ObserverPattern;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class Screen2 {
        private static DatePicker datePicker = new DatePicker();
        private static DatePicker datePicker1 = new DatePicker();
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
                                new HBox(10, new Label("ADD DATA TO LOG")),
                                new HBox(10, datePicker),
                                new HBox(10, tfWeight),
                                new HBox(10, tfCalories),
                                new HBox(10, tfFood),
                                btnLogData);

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

        public static void SaveToCSV() {
                String w = tfWeight.getText();
                String c = tfWeight.getText();
                String f = tfWeight.getText();
                if (valid()) {
                        File file = new File("log.csv");
                        String date = datePicker.getValue().toString();
                        String[] dates = date.split("-");
                        // String line;
                        // try (BufferedReader br = new BufferedReader(new FileReader(file))){
                        //         while ((line = br.readLine()) != null) {
                        //                 String[] list = line.split(",");
                        //                 if(list[3].equals(dates[3])){}
                        //         }
                        // } catch (Exception e) {

                        // };
                        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
                                String dataw = dates[0] + "," + dates[1] + "," + dates[2] + "," + "w" + ","
                                                + w;
                                String datac = dates[0] + "," + dates[1] + "," + dates[2] + "," + "c" + ","
                                                + c;
                                String dataf = dates[0] + "," + dates[1] + "," + dates[2] + "," + "f" + ","
                                                + f;
                                writer.write(dataw);
                                writer.newLine();
                                writer.write(datac);
                                writer.newLine();
                                writer.write(dataf);
                                writer.newLine();
                        } catch (IOException e) {
                                e.printStackTrace();
                        }
                }
        }

        public static boolean valid() {
                String w = tfWeight.getText();
                String c = tfWeight.getText();
                String f = tfWeight.getText();
                String d = datePicker.getValue().toString();

                if (d.length() < 1) {
                        return false;
                }
                if (w.length() < 1) {

                        return false;
                }
                if (c.length() < 1) {
                        return false;
                }
                if (f.length() < 1) {
                        return false;
                }
                return true;
        }

        private static void display(TextArea textArea) {
                File file = new File("log.csv");
                try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                        StringBuilder sb = new StringBuilder();
                        String line;
                        System.out.println("Hellow2");
                        while ((line = br.readLine()) != null) {
                                String[] list = line.split(",");
                                String date = datePicker1.getValue().toString();
                                String[] dates = date.split("-");
                                System.out.println(list[0] + list[1] + list[2]);
                                if (list[0].equals(dates[0]) && list[1].equals(dates[1]) && list[2].equals(dates[2])) {
                                                        if (Objects.equals(list[3], "w")) {
                                                                sb.append(list[4]).append("\n");
                                                        }
                                                        if (Objects.equals(list[3], "f")) {
                                                                sb.append(list[4]).append("\n");
                                                        }
                                                        if (Objects.equals(list[3], "c")) {
                                                                sb.append(list[4]).append("\n");
                                                        }
                                }
                        }
                        textArea.setText(sb.toString());
                } catch (IOException e) {
                        e.printStackTrace();
                }
        }
}
