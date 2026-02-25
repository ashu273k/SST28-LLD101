/*
 * ─── OCP: DisciplinaryFlagRule — Disciplinary Check ───
 *
 * PROBLEM SOLVED:
 *   The disciplinary flag check was one more branch in the engine.
 *   Extracting it makes the engine's evaluate() method generic.
 *
 * HOW IT WORKS:
 *   - If disciplinaryFlag != NONE → return "disciplinary flag present".
 *   - Otherwise → null (pass).
 */
public class DisciplinaryFlagRule implements EligibilityRule {
    @Override
    public String check(StudentProfile s) {
        if (s.disciplinaryFlag != LegacyFlags.NONE) {
            return "disciplinary flag present";
        }
        return null;
    }
}
