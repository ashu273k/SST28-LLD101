import java.util.*;

/*
 * ─── Demo03 — Composition Root / Wiring ───
 *
 * WHY THIS CLASS EXISTS:
 *   Assembles the rules engine with a CONFIGURABLE list of rules.
 *   This is where OCP becomes visible:
 *
 * HOW TO ADD A NEW RULE (OCP in action):
 *   1. Create a new class (e.g., MinorGPARule) implementing EligibilityRule.
 *   2. Add it to the list below: new MinorGPARule()
 *   3. Done! No changes to EligibilityEngine, no changes to existing rules.
 *
 * KEY DESIGN POINT:
 *   The rule list is configured HERE, not inside the engine. The engine
 *   is generic — it just iterates over whatever rules it receives.
 *   Changing which rules run = changing this wiring code only.
 */
public class Demo03 {
    public static void main(String[] args) {
        System.out.println("=== Placement Eligibility ===");
        StudentProfile s = new StudentProfile("23BCS1001", "Ayaan", 8.10, 72, 18, LegacyFlags.NONE);

        // ─── Configure the rule list (OCP: add new rules here, engine stays untouched) ───
        List<EligibilityRule> rules = List.of(
            new DisciplinaryFlagRule(),  // Rule 1: no disciplinary flags
            new CgrRule(),               // Rule 2: CGR >= 8.0
            new AttendanceRule(),        // Rule 3: attendance >= 75%
            new CreditsRule()            // Rule 4: credits >= 20
        );

        EligibilityEngine engine = new EligibilityEngine(new FakeEligibilityStore(), rules);
        engine.runAndPrint(s);
    }
}
