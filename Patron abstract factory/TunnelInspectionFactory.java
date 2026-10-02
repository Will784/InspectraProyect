public class TunnelInspectionFactory implements InspectionKitFactory {
    public InspectionRobot createRobot() {
        return new CrawlerRobot();
    }

    public InspectionSensor createSensor() {
        return new GasSensor();
    }

    public InspectionMission createMission() {
        return new InspectionMissionBuilder()
                .forInfrastructure("Tunnel")
                .withModule(InspectionModule.CAMERA)
                .withModule(InspectionModule.THERMAL)
                .build();
    }
}
