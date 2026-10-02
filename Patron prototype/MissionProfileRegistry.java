import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class MissionProfileRegistry {
    private final Map<String, MissionProfile> profiles = new LinkedHashMap<>();

    public void register(String key, MissionProfile profile) {
        profiles.put(key, profile.copy());
    }

    public MissionProfile create(String key) {
        MissionProfile profile = profiles.get(key);
        if (profile == null) {
            throw new IllegalArgumentException("Unknown mission profile: " + key);
        }
        return profile.copy();
    }

    public Set<String> getKeys() {
        return profiles.keySet();
    }
}
