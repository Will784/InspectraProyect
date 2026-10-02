public class BridgeInspectionFactory implements InspectionKitFactory {
    public InspectionRobot createRobot() {
        return new AerialDroneRobot();
    }

    public InspectionSensor createSensor() {
        return new LidarSensor();
    }

    public InspectionMission createMission() {
        return new InspectionMissionBuilder()
                .forInfrastructure("Bridge")
                .withModule(InspectionModule.CAMERA)
                .withModule(InspectionModule.VIBRATION)
                .build();
    }
}
