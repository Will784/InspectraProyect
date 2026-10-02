public class IndustrialInspectionFactory implements InspectionKitFactory {
    public InspectionRobot createRobot() {
        return new ClimbingRobot();
    }

    public InspectionSensor createSensor() {
        return new UltrasonicSensor();
    }

    public InspectionMission createMission() {
        return new InspectionMissionBuilder()
                .forInfrastructure("Industrial Structure")
                .withModule(InspectionModule.THERMAL)
                .withModule(InspectionModule.VIBRATION)
                .withModule(InspectionModule.AI_ANOMALY)
                .build();
    }
}
