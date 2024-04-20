

public class BasicFood extends Food {
    private String name;
    private int calories;
    private int fat;
    private int carbs;
    private int protein;

    public BasicFood(String name, int calories, int fat, int carbs, int protein) {
        this.name = name;
        this.calories = calories;
        this.fat = fat;
        this.carbs = carbs;
        this.protein = protein;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getCalories() {
        return calories;
    }

    @Override
    public int getFat() {
        return fat;
    }

    @Override
    public int getCarbohydrates() {
        return carbs;
    }

    @Override
    public int getProtein() {
        return protein;
    }
}
