public interface MissionBuilder {
    MissionBuilder reset();
    MissionBuilder forInfrastructure(String infrastructure);
    MissionBuilder withModule(InspectionModule module);
    InspectionMission build();
}
