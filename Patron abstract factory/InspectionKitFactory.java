public interface InspectionKitFactory {
    InspectionRobot createRobot();
    InspectionSensor createSensor();
    InspectionMission createMission();
}
