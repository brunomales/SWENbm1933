import java.util.List;

public class FoodFactory {
    public static Food createFood(String name, int calories, int fat, int carbs, int protein) {
            return new BasicFood(name, calories,  fat,  carbs,  protein);
    }
}
