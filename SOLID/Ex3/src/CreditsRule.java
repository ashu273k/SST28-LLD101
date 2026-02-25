/*
 * ─── OCP: CreditsRule — Minimum Credits Rule ───
 *
 * PROBLEM SOLVED:
 *   The credits check was embedded in the engine's if/else chain.
 *   Now it's a standalone, testable rule.
 *
 * HOW IT WORKS:
 *   - If earnedCredits < 20 → return "credits below 20".
 *   - Otherwise → null (pass).
 */
public class CreditsRule implements EligibilityRule {
    @Override
    public String check(StudentProfile s) {
        if (s.earnedCredits < 20) {
            return "credits below 20";
        }
        return null;
    }
}
