import java.util.ArrayList;
import java.util.List;
import java.util.Observer;



@SuppressWarnings("deprecation")
public class Model implements Subject{
  
    private List<Food> foods = new ArrayList<>();

    private List<Observer> observers = new ArrayList<>();

    @Override
    public void registerObserver(Observer o) {
        observers.add(o);
    }

    public void removeObserver(Observer o) {
        observers.remove(o);
    }

    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(null, observer);
        }
    }

    public void addFood(Food food) {
        foods.add(food);
        notifyObservers();
    }

    public String getData() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getData'");
    }

    
}
