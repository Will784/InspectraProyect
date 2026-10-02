import java.util.List;

public class VibrationAnalysisDecorator extends MissionDecorator {
    public VibrationAnalysisDecorator(InspectionMission mission) {
        super(mission);
    }

    public String getName() {
        return mission.getName() + " -> Vibration Analysis";
    }

    public List<String> execute() {
        List<String> log = mission.execute();
        log.add("Vibration: analysis done, abnormal frequency found in section C");
        return log;
    }
}
