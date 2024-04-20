
import java.util.Observable;
import java.util.Observer;

@SuppressWarnings("deprecation")
public class View implements Observer {
    private Model model;

    public View(Model model) {
        this.model = model;
        model.registerObserver(this);
    }

   

    private void displayData(String data) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'displayData'");
    }



    @Override
    public void update(Observable o, Object arg) {
        // Fetch new data from the model
        String data = model.getData();
        // Display the new data
        displayData(data);
    }

 
    
}
