import java.util.Observer;

@SuppressWarnings("deprecation")
public interface Subject {
    void registerObserver(Observer o);
    void removeObserver(Observer o);
    void notifyObservers();
}
