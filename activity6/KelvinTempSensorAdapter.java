public class KelvinTempSensorAdapter implements ITempSensor{
    private KelvinTempSensor kts = new KelvinTempSensor(); 
    private final int KTOC = -27315 ;
    public double getCelsius() {
        return (kts.reading() + KTOC) / 100.0 ;
    }
}
