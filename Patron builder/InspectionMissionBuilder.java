import java.util.EnumSet;
import java.util.Set;

public class InspectionMissionBuilder implements MissionBuilder {
    private String infrastructure;
    private final Set<InspectionModule> modules = EnumSet.noneOf(InspectionModule.class);

    public MissionBuilder reset() {
        infrastructure = null;
        modules.clear();
        return this;
    }

    public MissionBuilder forInfrastructure(String infrastructure) {
        this.infrastructure = infrastructure;
        return this;
    }

    public MissionBuilder withModule(InspectionModule module) {
        modules.add(module);
        return this;
    }

    public InspectionMission build() {
        if (infrastructure == null || infrastructure.isBlank()) {
            throw new IllegalStateException("The mission needs an infrastructure");
        }
        InspectionMission mission = new BasicInspectionMission(infrastructure);
        for (InspectionModule module : modules) {
            mission = module.attachTo(mission);
        }
        return mission;
    }
}
