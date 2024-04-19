
public class View implements Observer {
    private Model model;

    public View(Model model) {
        this.model = model;
        model.addObserver(this);
    }

    @Override
    public void update() {
        // Update the view based on the model's state
    }
}
