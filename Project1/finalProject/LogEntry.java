import java.time.LocalDate;
import java.util.List;

public class LogEntry {
    
    private List<Exe> exercises;
    String line; 

    public LogEntry(List<Exe> exercises) {
        
        this.exercises = exercises;
    }

    public List<Exe> getExercises() {
        return exercises;
    }
    public String displayLog() {
        for(int i = 0; i < exercises.size(); i++) {
        line += "Exercises: " + exercises.get(i).getName() + "\n";
        }
        return line;
    }
}
