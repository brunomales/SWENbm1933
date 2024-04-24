import java.util.List;

public class FoodFactory {
    public static Food createFood(String type, String name, List<Food> ingredients, int calories, int fat, int carbs, int protein) {
        if ("basic".equalsIgnoreCase(type)) {
            return new BasicFood(name, calories, fat, carbs, protein);
        } else if ("recipe".equalsIgnoreCase(type)) {
            return new Recipe(name, ingredients);
        } else {
            throw new IllegalArgumentException("Unknown food type: " + type);
        }
    }
    public static Exe creatExercise(String name, String type, List<Exe> ingredients, int intensity){
        if ("basic".equalsIgnoreCase(type)) {
        return new Exercise(name, intensity);
    } else if ("recipe".equalsIgnoreCase(type)) {
        return new Exercises(ingredients);
    } else {
        throw new IllegalArgumentException("Unknown food type: " + type);
    }
    }
}