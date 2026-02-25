import java.util.LinkedHashMap;
import java.util.Map;

/*
 * ─── SRP REFACTOR: StudentParser ───
 *
 * PROBLEM SOLVED:
 *   Originally, parsing logic (splitting raw input string into key-value pairs)
 *   was embedded inside OnboardingService.registerFromRawInput(). This mixed
 *   parsing with validation, ID generation, persistence, and printing — all
 *   inside one "god method". Any change to the input format (e.g., switching
 *   from ";" to "," delimiter) would force us to modify the entire workflow.
 *
 * WHY WE CREATED THIS CLASS:
 *   We extracted parsing into its own class so it has ONE reason to change:
 *   the input format. This is the Single Responsibility Principle (SRP).
 *   - The parse() method takes a raw string and returns a clean Map<String,String>.
 *   - It knows nothing about validation, IDs, databases, or printing.
 *
 * HOW IT WORKS:
 *   1. Splits the raw input by ";" to get tokens like "name=Riya".
 *   2. Splits each token by "=" (limit 2) to get key-value pairs.
 *   3. Trims whitespace and puts them in a LinkedHashMap (preserves order).
 *
 * BENEFIT:
 *   - Can be unit tested in isolation (pass a string, assert the Map).
 *   - Changing the delimiter or format only affects this one class.
 *   - OnboardingService no longer needs to know HOW parsing works.
 */
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
