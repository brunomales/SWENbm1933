public class WeightDecorator extends LogEntryDecorator {
    private int weight;
    private int burned;

    public WeightDecorator(LogEntry logEntry, int weight, int burned) {
        super(logEntry);
        this.weight = weight;
        this.burned = burned;
    }

    @Override
    public String displayLog() {
        return logEntry.displayLog() + "Weight: " + weight + " kg \n" + "Calories burned: " + burned + "\n";
    }
}
