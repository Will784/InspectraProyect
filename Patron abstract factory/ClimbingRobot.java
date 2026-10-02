public class ClimbingRobot implements InspectionRobot {
    public String getModel() {
        return "Magnetic Climber MC-45";
    }

    public String deploy() {
        return getModel() + " climbing the steel structure";
    }
}
