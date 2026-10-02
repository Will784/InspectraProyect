import java.util.List;

public class CameraInspectionDecorator extends MissionDecorator {
    public CameraInspectionDecorator(InspectionMission mission) {
        super(mission);
    }

    public String getName() {
        return mission.getName() + " -> Camera Inspection";
    }

    public List<String> execute() {
        List<String> log = mission.execute();
        log.add("Camera: visual inspection done, 124 images captured, 2 surface cracks spotted");
        return log;
    }
}

//script realizado por Victor Aguilar//
