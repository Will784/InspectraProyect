public class AerialDroneRobot implements InspectionRobot {
    public String getModel() {
        return "Aerial Drone AD-200";
    }

    public String deploy() {
        return getModel() + " flying along the deck and the cables";
    }
}
