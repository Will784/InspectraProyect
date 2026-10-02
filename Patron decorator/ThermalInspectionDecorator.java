import java.util.List;

public class ThermalInspectionDecorator extends MissionDecorator {
    public ThermalInspectionDecorator(InspectionMission mission) {
        super(mission);
    }

    public String getName() {
        return mission.getName() + " -> Thermal Analysis";
    }

    public List<String> execute() {
        List<String> log = mission.execute();
        log.add("Thermal: scan done, 1 hot spot detected at 78 C near section B");
        return log;
    }
}
