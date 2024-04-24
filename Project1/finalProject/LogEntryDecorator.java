public abstract class LogEntryDecorator extends LogEntry {
    protected LogEntry logEntry;

    public LogEntryDecorator(LogEntry logEntry) {
        super( logEntry.getExercises());
        this.logEntry = logEntry;
    }

    @Override
    public abstract String displayLog();
}
