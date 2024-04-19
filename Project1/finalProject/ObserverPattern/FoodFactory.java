


public class FoodFactory {
    public static Food createFood(String type, String name, int calories) {
        if ("Basic".equals(type)) {
            return new BasicFood(name, calories);
        }
        // Additional food types like recipes can be added here
        return null;
    }
}
