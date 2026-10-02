public class MissionDirector {
    public InspectionMission buildQuickCheck(MissionBuilder builder, String infrastructure) {
        return builder.reset()
                .forInfrastructure(infrastructure)
                .withModule(InspectionModule.CAMERA)
                .build();
    }

    public InspectionMission buildStructuralSurvey(MissionBuilder builder, String infrastructure) {
        return builder.reset()
                .forInfrastructure(infrastructure)
                .withModule(InspectionModule.CAMERA)
                .withModule(InspectionModule.THERMAL)
                .withModule(InspectionModule.VIBRATION)
                .build();
    }

    public InspectionMission buildFullAudit(MissionBuilder builder, String infrastructure) {
        builder.reset().forInfrastructure(infrastructure);
        for (InspectionModule module : InspectionModule.values()) {
            builder.withModule(module);
        }
        return builder.build();
    }
}
