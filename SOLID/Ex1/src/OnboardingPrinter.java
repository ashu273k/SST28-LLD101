import java.util.List;

/*
 * ─── SRP REFACTOR: OnboardingPrinter ───
 *
 * PROBLEM SOLVED:
 *   Originally, all System.out.println() calls were scattered inside
 *   OnboardingService — header printing, input echo, confirmation,
 *   error display, and DB dump were all mixed with business logic.
 *   Changing the output format (e.g., to JSON or a GUI) required
 *   editing the core workflow.
 *
 * WHY WE CREATED THIS CLASS:
 *   Extracted ALL printing/formatting logic into its own class with
 *   ONE responsibility: displaying output to the console. Follows SRP.
 *   - Each method handles one type of output (header, input, errors, etc.).
 *   - The service calls printer methods instead of doing System.out directly.
 *
 * HOW IT WORKS:
 *   Provides focused methods: printHeader(), printInput(), printCreated(),
 *   printSavedCount(), printConfirmation(), printDbDump(), printErrors().
 *   Each does exactly one print task.
 *
 * BENEFIT:
 *   - Changing output format (console → file, HTML, JSON) = replace or
 *     extend this one class. Business logic stays untouched.
 *   - OnboardingService focuses on orchestration, not formatting.
 *   - Can be swapped with a mock in tests to verify what gets printed.
 */
public class OnboardingPrinter {
    
    public void printHeader() {
         System.out.println("=== Student Onboarding ===");
    }

    public void printInput(String rawInput) {
        System.out.println("INPUT: " + rawInput);
    }

    public void printCreated(String id) {
        System.out.println("OK: created student " + id);
    }

    public void printSavedCount(int count) {
        System.out.println("Saved. Total students: " + count);
    }

     public void printConfirmation(StudentRecord record) {
        System.out.println("CONFIRMATION:");
        System.out.println(record);
    }

    public void printDbDump(List<StudentRecord> records) {
        System.out.println();
        System.out.println("-- DB DUMP --");
        System.out.println("| ID             | NAME | PROGRAM |");
        for (StudentRecord r : records) {
            System.out.printf("| %-15s| %-5s| %-8s|%n", r.getId(), r.getName(), r.getProgram());
        }
    }

    public void printErrors(List<String> errors) {
        System.out.println("VALIDATION FAILED:");
        for (String e : errors) {
            System.out.println("  - " + e);
        }
    }
}
