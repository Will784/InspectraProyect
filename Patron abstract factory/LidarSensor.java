public class LidarSensor implements InspectionSensor {
    public String getType() {
        return "LiDAR Scanner";
    }

    public String calibrate() {
        return getType() + " calibrated for long range deformation mapping";
    }
}
