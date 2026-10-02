public class CreationalPatternsDemo {
    public static void main(String[] args) {
        showBuilder();
        showPrototype();
        showAbstractFactory();
    }

    private static void showBuilder() {
        System.out.println("=== Builder ===");
        MissionBuilder builder = new InspectionMissionBuilder();
        MissionDirector director = new MissionDirector();

        run(director.buildQuickCheck(builder, "Bridge"));
        run(director.buildStructuralSurvey(builder, "Tunnel"));
        run(director.buildFullAudit(builder, "Industrial Structure"));
        run(builder.reset()
                .forInfrastructure("Bridge")
                .withModule(InspectionModule.COMPLIANCE_REPORT)
                .withModule(InspectionModule.THERMAL)
                .build());
    }

    private static void showPrototype() {
        System.out.println("=== Prototype ===");
        MissionProfileRegistry registry = new MissionProfileRegistry();
        registry.register("bridge-routine", new MissionProfile("Bridge")
                .addZone("Deck")
                .addModule(InspectionModule.CAMERA)
                .addModule(InspectionModule.VIBRATION));
        registry.register("tunnel-safety", new MissionProfile("Tunnel")
                .addZone("North Portal")
                .addModule(InspectionModule.CAMERA)
                .addModule(InspectionModule.THERMAL)
                .addModule(InspectionModule.COMPLIANCE_REPORT));

        MissionProfile template = registry.create("bridge-routine");
        MissionProfile custom = registry.create("bridge-routine")
                .addZone("Pillar 3")
                .addModule(InspectionModule.AI_ANOMALY);

        System.out.println("Registered profiles: " + registry.getKeys());
        System.out.println("Template: " + template.describe());
        System.out.println("Clone:    " + custom.describe());
        System.out.println("Template after editing the clone: " + registry.create("bridge-routine").describe());
        System.out.println();

        MissionBuilder builder = new InspectionMissionBuilder();
        run(custom.toMission(builder));
        run(registry.create("tunnel-safety").toMission(builder));
    }

    private static void showAbstractFactory() {
        System.out.println("=== Abstract Factory ===");
        InspectionKitFactory[] factories = {
                new BridgeInspectionFactory(),
                new TunnelInspectionFactory(),
                new IndustrialInspectionFactory()
        };
        for (InspectionKitFactory factory : factories) {
            InspectionRobot robot = factory.createRobot();
            InspectionSensor sensor = factory.createSensor();
            System.out.println("Robot:  " + robot.deploy());
            System.out.println("Sensor: " + sensor.calibrate());
            run(factory.createMission());
        }
    }

    private static void run(InspectionMission mission) {
        System.out.println(mission.getName());
        for (String line : mission.execute()) {
            System.out.println("  " + line);
        }
        System.out.println();
    }
}
