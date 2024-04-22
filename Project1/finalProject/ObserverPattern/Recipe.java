import java.util.List;

public class Recipe extends Food {
    private String name;
    private List<Food> ingredients;

    public Recipe(String name, List<Food> ingredients) {
        this.name = name;
        this.ingredients = ingredients;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getCalories() {
        int totalCalories = 0;
        for (Food food : ingredients) {
            totalCalories += food.getCalories();
        }
        return totalCalories;
    }

    @Override
    public int getFat() {
        int totalFat = 0;
        for (Food food : ingredients) {
            totalFat += food.getFat();
        }
        return totalFat;
    }

    @Override
    public int getCarbohydrates() {
        int totalCarbs = 0;
        for (Food food : ingredients) {
            totalCarbs += food.getCarbohydrates();
        }
        return totalCarbs;
    }

    @Override
    public int getProtein() {
        int totalProtein = 0;
        for (Food food : ingredients) {
            totalProtein += food.getProtein();
        }
        return totalProtein;
    }
}