package ObserverPattern;

import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.Observable;
import java.util.Observer;

public class View implements Observer {
    private Stage primaryStage;
    private Button screen1Button;
    private Button screen2Button;
    private BorderPane mainLayout;

    public View(Stage primaryStage, Model model) {
        this.primaryStage = primaryStage;
        initUI();
    }
    private void initUI() {
        screen1Button = new Button("Food Collection");
        screen2Button = new Button("Daily Log");
        screen1Button.setMinWidth(200);
        screen2Button.setMinWidth(200);

        VBox buttonLayout = new VBox(10);
        buttonLayout.setAlignment(Pos.CENTER);
        buttonLayout.getChildren().addAll(screen1Button, screen2Button);

        mainLayout = new BorderPane();
        mainLayout.setCenter(buttonLayout);
        mainLayout.setPadding(new Insets(20));
    }
    public Button getScreen1Button() {
        return screen1Button;
    }
    public Button getScreen2Button() {
        return screen2Button;
    }
    public BorderPane getMainLayout() {
        return mainLayout;
    }

    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof Model) {
            int currentScreen = ((Model) o).getCurrentScreen();
        }
    }
}
