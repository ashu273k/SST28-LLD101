import java.util.List;

// It has the single responsibility of printing the data
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
