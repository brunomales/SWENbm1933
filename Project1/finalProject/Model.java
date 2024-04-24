import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class Model {
    private Food food;
    private PropertyChangeSupport support;

    public Model() {
        support = new PropertyChangeSupport(this);
    }

    public void addPropertyChangeListener(PropertyChangeListener pcl) {
        support.addPropertyChangeListener(pcl);
    }

    public void removePropertyChangeListener(PropertyChangeListener pcl) {
        support.removePropertyChangeListener(pcl);
    }

    public void addFood(Food food) {
        Food oldFood = this.food;
        this.food = food;
        support.firePropertyChange("food", oldFood, food);
    }
}
