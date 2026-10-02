import java.util.ArrayList;
import java.util.List;

public class BasicInspectionMission implements InspectionMission {
    private final String infrastructure;

    public BasicInspectionMission(String infrastructure) {
        this.infrastructure = infrastructure;
    }

    public String getName() {
        return "Inspection Mission (" + infrastructure + ")";
    }

    public List<String> execute() {
        List<String> log = new ArrayList<>();
        log.add("Robot deployed to: " + infrastructure);
        return log;
    }
}

//script realizado por Victor Aguilar//
