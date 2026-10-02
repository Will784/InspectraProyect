import java.util.List;

public class AIAnomalyDecorator extends MissionDecorator {
    public AIAnomalyDecorator(InspectionMission mission) {
        super(mission);
    }

    public String getName() {
        return mission.getName() + " -> AI Anomaly Detection";
    }

    public List<String> execute() {
        List<String> log = mission.execute();
        log.add("AI: 3 possible anomalies identified, 1 marked as high priority");
        return log;
    }
}

//script realizado por Victor Aguilar//
