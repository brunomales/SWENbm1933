import java.util.List;

public class Exercises extends Exe {

    private String name;
    private List<Exe>intensity; 

    public Exercises(List<Exe> intensity) {
        this.intensity = intensity;
    }

    public String getName() {
        return name;
    }

    public int getIntensity() {
        int totalIntensity = 0;
        for (Exe exe : intensity) {
            totalIntensity += exe.getIntensity();
        }
        return totalIntensity;
    }
}
