import java.util.LinkedHashMap;
import java.util.Map;

// It has the single responsibility of parsing the data
public class StudentParser {
    
    public Map<String, String> parse(String rawInput) {
        
        Map<String,String> kv = new LinkedHashMap<>();
        String[] parts = rawInput.split(";");
        for (String p : parts) {
            String[] t = p.split("=", 2);
            if (t.length == 2) kv.put(t[0].trim(), t[1].trim());
        }

        return kv;

    }
}
