import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class LogEntry {
    private LocalDate date;
    
    private List<Exe> exercises;
    String line; 

    public LogEntry(LocalDate date,  List<Exe> exercises) {
        this.date = date;
        this.exercises = exercises;
    }


    public LocalDate getDate() {
        return date;
    }

    public List<Exe> getExercises() {
        return exercises;
    }
    public String displayLog() {
        for(int i = 0; i < exercises.size(); i++) {
        line +=  "Date: " + date + ", Exercises: " + exercises.get(i).getName() + "\n";
        }
        return line;
    }
}
