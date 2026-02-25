/*
 * ─── OCP: AttendanceRule — One of the Extracted Rules ───
 *
 * PROBLEM SOLVED:
 *   The attendance check ("if attendancePct < 75, reject") was previously
 *   one branch inside the engine's long if/else. Extracting it into its
 *   own class means the engine doesn't encode attendance thresholds.
 *
 * HOW IT WORKS:
 *   Implements EligibilityRule.check():
 *   - If attendance < 75 → return reason "attendance below 75".
 *   - Otherwise → return null (student passes this rule).
 *
 * BENEFIT:
 *   - Changing the threshold (e.g., 75 → 80) only affects THIS class.
 *   - Removing this rule = remove it from the list in Demo03. No engine edits.
 */
public class AttendanceRule implements EligibilityRule {
    @Override
    public String check(StudentProfile s) {
        if (s.attendancePct < 75) {
            return "attendance below 75";
        }
        return null;
    }
}
