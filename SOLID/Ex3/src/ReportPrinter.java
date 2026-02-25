/*
 * ─── SRP: ReportPrinter — Separated Printing Responsibility ───
 *
 * PROBLEM SOLVED:
 *   Printing the eligibility report was mixed inside the engine's evaluate
 *   method. Extracting it follows SRP — the engine evaluates, the printer
 *   formats and displays.
 *
 * HOW IT WORKS:
 *   Takes a StudentProfile and EligibilityEngineResult and prints a
 *   human-readable report to the console.
 */
public class ReportPrinter {
    public void print(StudentProfile s, EligibilityEngineResult r) {
        System.out.println("Student: " + s.name + " (CGR=" + String.format("%.2f", s.cgr)
                + ", attendance=" + s.attendancePct + ", credits=" + s.earnedCredits
                + ", flag=" + LegacyFlags.nameOf(s.disciplinaryFlag) + ")");
        System.out.println("RESULT: " + r.status);
        for (String reason : r.reasons) System.out.println("- " + reason);
        if (r.reasons.isEmpty() && "ELIGIBLE".equals(r.status)) {
            // keep behavior stable even if empty
        }
    }
}
