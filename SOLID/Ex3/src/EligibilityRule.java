public interface EligibilityRule {
    /** Return a reason string if the student fails this rule, or null if they pass. */
    String check(StudentProfile s);
}
