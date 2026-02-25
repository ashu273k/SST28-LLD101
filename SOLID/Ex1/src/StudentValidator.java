import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/*
 * ─── SRP REFACTOR: StudentValidator ───
 *
 * PROBLEM SOLVED:
 *   Previously, validation rules (name required, email must contain "@",
 *   phone must be digits, program must be CSE/AI/SWE) were hard-coded
 *   inside OnboardingService alongside parsing, persistence, and printing.
 *   This meant:
 *     - Adding a new validation rule required editing the entire workflow method.
 *     - Validation could not be tested without running the full onboarding flow.
 *     - Printing error messages was tangled with checking for errors.
 *
 * WHY WE CREATED THIS CLASS:
 *   Extracted validation into its own class so it has ONE reason to change:
 *   the validation rules themselves. This follows SRP.
 *   - Takes a Map<String,String> of parsed fields as input.
 *   - Returns a List<String> of error messages (empty list = valid).
 *   - Knows nothing about parsing, IDs, databases, or printing.
 *
 * HOW IT WORKS:
 *   1. Reads fields ("name", "email", "phone", "program") from the map.
 *   2. Checks each against its rule and collects error strings.
 *   3. Returns the list — the CALLER decides what to do with errors.
 *
 * BENEFIT:
 *   - Adding a new field check = add code here only, not in the service.
 *   - Testable independently: pass a Map, assert expected errors.
 *   - Validation logic is centralized, not scattered across the codebase.
 */
public class StudentValidator {
    

    public List<String> validate(Map<String, String> kv) {


        String name = kv.getOrDefault("name", "");
        String email = kv.getOrDefault("email", "");
        String phone = kv.getOrDefault("phone", "");
        String program = kv.getOrDefault("program", "");
        // validation inline, printing inline
        List<String> errors = new ArrayList<>();
        if (name.isBlank())
            errors.add("name is required");

        if (email.isBlank() || !email.contains("@"))
            errors.add("email is invalid");

        if (phone.isBlank() || !phone.chars().allMatch(Character::isDigit))
            errors.add("phone is invalid");

        if (!(program.equals("CSE") || program.equals("AI") || program.equals("SWE")))
            errors.add("program is invalid");

        return errors;
    }
}
