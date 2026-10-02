public class CrawlerRobot implements InspectionRobot {
    public String getModel() {
        return "Tracked Crawler TC-80";
    }

    public String deploy() {
        return getModel() + " driving through the tunnel lining";
    }
}
