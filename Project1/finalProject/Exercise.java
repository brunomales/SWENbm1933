public class Exercise extends Exe{
    private String name;
    private int intensity; 

    public Exercise(String name,int intensity) {
        this.name = name;
        this.intensity = intensity;
    }

    public String getName() {
        return name;
    }

    public int getIntensity() {
        return intensity;
    }
}