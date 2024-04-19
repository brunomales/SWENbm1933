public class BasicFood extends Food {
    private String name;
    private int calories;

    public BasicFood(String name, int calories) {
        this.name = name;
        this.calories = calories;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getCalories() {
        return calories;
    }
}
