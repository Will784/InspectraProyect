import java.util.List;

public abstract class MissionDecorator implements InspectionMission {
    protected final InspectionMission mission;

    public MissionDecorator(InspectionMission mission) {
        this.mission = mission;
    }

    public String getName() {
        return mission.getName();
    }

    public List<String> execute() {
        return mission.execute();
    }
}
