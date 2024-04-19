import java.util.ArrayList;
import java.util.List;



public class Model {
    private List<Observer> observers = new ArrayList<>();
    private List<Food> foods = new ArrayList<>();

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update();
        }
    }

    public void addFood(Food food) {
        foods.add(food);
        notifyObservers();
    }
}
