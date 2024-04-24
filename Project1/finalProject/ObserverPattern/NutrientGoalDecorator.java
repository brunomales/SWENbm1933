public class NutrientGoalDecorator extends LogEntryDecorator {
    private int calorieGoal;
    private int proteinGoal;

    public NutrientGoalDecorator(LogEntry logEntry, int calorieGoal, int proteinGoal) {
        super(logEntry);
        this.calorieGoal = calorieGoal;
        this.proteinGoal = proteinGoal;

    }

    @Override
    public String displayLog() {
        return logEntry.displayLog() + " Calorie Goal: " + calorieGoal + " kcal \n";
    }
}
