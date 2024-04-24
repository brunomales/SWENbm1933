public class NutrientGoalDecorator extends LogEntryDecorator {
    private int calorieGoal;
    private int proteinGoal;

    private int burned;

    public NutrientGoalDecorator(LogEntry logEntry, int calorieGoal, int proteinGoal, int burned) {
        super(logEntry);
        this.calorieGoal = calorieGoal;
        this.proteinGoal = proteinGoal;
        this.burned = burned;

    }

    @Override
    public String displayLog() {
        return logEntry.displayLog() + " Calorie Goal: " + calorieGoal + " kcal\n " + "Calories to burn: " + burned + "\n";
    }
}
