/*
 * ─── OCP REFACTOR: EligibilityRule (Interface / Abstraction) ───
 *
 * PROBLEM SOLVED:
 *   Originally, EligibilityEngine.evaluate() had a LONG if/else chain:
 *     if (cgr < 8.0) ...
 *     if (attendance < 75) ...
 *     if (credits < 20) ...
 *     if (flag != NONE) ...
 *   Adding a NEW rule (e.g., "minimum GPA in major") required EDITING the
 *   evaluate() method — this violates the Open-Closed Principle (OCP):
 *   "Classes should be OPEN for extension, CLOSED for modification."
 *
 * WHY WE CREATED THIS INTERFACE:
 *   By defining an EligibilityRule interface, each rule becomes its own class.
 *   The engine just ITERATES over a list of rules — it doesn't know the specifics.
 *   - To ADD a new rule: create a new class implementing EligibilityRule.
 *   - The engine and all existing rules remain UNTOUCHED.
 *
 * HOW IT WORKS:
 *   - check(StudentProfile s) inspects the student and returns:
 *     • null if the student PASSES this rule.
 *     • A reason string (e.g., "attendance below 75") if they FAIL.
 *   - The engine collects all non-null reasons to determine eligibility.
 *
 * BENEFIT:
 *   - No giant conditional chains — each rule is a small, focused class.
 *   - Adding rules = creating files, not editing existing logic.
 *   - Each rule is independently testable.
 *   - Rules can be reordered, removed, or combined via configuration.
 */
public interface EligibilityRule {
    /** Return a reason string if the student fails this rule, or null if they pass. */
    String check(StudentProfile s);
}
