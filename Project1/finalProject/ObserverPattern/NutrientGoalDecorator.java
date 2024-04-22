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
        return logEntry.displayLog() + " Calorie Goal: " + calorieGoal + " kcal, Protein Goal: " + proteinGoal + " g\n"
                + "Left calories to take: " + calorieGoal  + "\n" + "Left calories to take: "
                + proteinGoal  + "\n";
    }
}
