public import java.util.List;

public class ComplianceReportDecorator extends MissionDecorator {
    public ComplianceReportDecorator(InspectionMission mission) {
        super(mission);
    }

    public String getName() {
        return mission.getName() + " -> Compliance Report";
    }

    public List<String> execute() {
        List<String> log = mission.execute();
        log.add("Report: compliance report generated with " + log.size() + " documented steps");
        return log;
    }
} {
    
}

// Script realizado por: William Cando
