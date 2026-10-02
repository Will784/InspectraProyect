public class UltrasonicSensor implements InspectionSensor {
    public String getType() {
        return "Ultrasonic Thickness Sensor";
    }

    public String calibrate() {
        return getType() + " calibrated for metal corrosion measurement";
    }
}
