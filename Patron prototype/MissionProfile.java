import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;

public class MissionProfile implements MissionPrototype {
    private String infrastructure;
    private final List<String> zones;
    private final Set<InspectionModule> modules;

    public MissionProfile(String infrastructure) {
        this.infrastructure = infrastructure;
        this.zones = new ArrayList<>();
        this.modules = EnumSet.noneOf(InspectionModule.class);
    }

    private MissionProfile(MissionProfile source) {
        this.infrastructure = source.infrastructure;
        this.zones = new ArrayList<>(source.zones);
        this.modules = EnumSet.copyOf(source.modules);
    }

    public MissionProfile copy() {
        return new MissionProfile(this);
    }

    public MissionProfile setInfrastructure(String infrastructure) {
        this.infrastructure = infrastructure;
        return this;
    }

    public MissionProfile addZone(String zone) {
        zones.add(zone);
        return this;
    }

    public MissionProfile addModule(InspectionModule module) {
        modules.add(module);
        return this;
    }

    public MissionProfile removeModule(InspectionModule module) {
        modules.remove(module);
        return this;
    }

    public InspectionMission toMission(MissionBuilder builder) {
        builder.reset().forInfrastructure(getTarget());
        for (InspectionModule module : modules) {
            builder.withModule(module);
        }
        return builder.build();
    }

    public String describe() {
        return getTarget() + " with modules " + modules;
    }

    private String getTarget() {
        if (zones.isEmpty()) {
            return infrastructure;
        }
        return infrastructure + ", zones " + String.join("/", zones);
    }
}
