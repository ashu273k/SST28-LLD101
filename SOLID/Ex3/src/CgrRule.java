/*
 * ─── OCP: CgrRule — CGR Threshold Rule ───
 *
 * PROBLEM SOLVED:
 *   The CGR check was a hard-coded branch in the engine. Now it's an
 *   independent, pluggable rule class.
 *
 * HOW IT WORKS:
 *   - If cgr < 8.0 → return "CGR below 8.0".
 *   - Otherwise → null (pass).
 */
public class CgrRule implements EligibilityRule {
    @Override
    public String check(StudentProfile s) {
        if (s.cgr < 8.0) {
            return "CGR below 8.0";
        }
        return null;
    }
}
