import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class View implements PropertyChangeListener {
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getNewValue() instanceof Food) {
            Food food = (Food) evt.getNewValue();
            display(food);
        }
    }

    private void display(Food food) {
        File file = new File("foods.csv");
        Screen1 screen = new Screen1();
        Progress progress = new Progress();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
            String data = String.format("b,%s,%d,%d,%d,%d",
                food.getName(), food.getCalories(), food.getFat(),
                food.getCarbohydrates(), food.getProtein());
            writer.write(data);
            writer.newLine();
            screen.appendText(food.getName());
            progress.appendText(food.getName());
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }
}
