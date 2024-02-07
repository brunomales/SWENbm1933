class FahrenheitSensorAdapter{
    KelvinTempSensor kts = new KelvinTempSensor();
    private final int KTOC = -27315 ;
    public double getFahrenheit(){
        return ((kts.reading() + KTOC) / 100.0) * (9/5) + 32;
    }
}
