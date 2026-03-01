import java.util.*;

public class DeviceRegistry {
    private final List<Object> devices = new ArrayList<>();

    public void add(Object d) { devices.add(d); }

    /** Look up a device by its concrete class name (e.g. "Projector"). */
    public Object getFirstOfType(String simpleName) {
        for (Object d : devices) {
            if (d.getClass().getSimpleName().equals(simpleName)) return d;
        }
        throw new IllegalStateException("Missing: " + simpleName);
    }

    /** Return every registered device that implements the given capability. */
    @SuppressWarnings("unchecked")
    public <T> List<T> allWith(Class<T> capability) {
        List<T> result = new ArrayList<>();
        for (Object d : devices) {
            if (capability.isInstance(d)) result.add((T) d);
        }
        return result;
    }
}
