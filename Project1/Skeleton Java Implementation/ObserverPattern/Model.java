package ObserverPattern;

import java.util.Observable;

public class Model extends Observable {
    private int currentScreen;

    public int getCurrentScreen() {
        return currentScreen;
    }

    public void setCurrentScreen(int currentScreen) {
        this.currentScreen = currentScreen;
        setChanged();
        notifyObservers();
    }
}
