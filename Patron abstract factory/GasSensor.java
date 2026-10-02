public class GasSensor implements InspectionSensor {
    public String getType() {
        return "Gas and Humidity Sensor";
    }

    public String calibrate() {
        return getType() + " calibrated for low light confined spaces";
    }
}
