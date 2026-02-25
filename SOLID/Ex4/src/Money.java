/*
 * ─── Money (Value Object) ───
 *
 * PROBLEM SOLVED:
 *   Money arithmetic (addition, rounding) was scattered and done with raw
 *   doubles throughout the codebase, leading to inconsistent formatting
 *   and potential rounding errors.
 *
 * WHY WE CREATED THIS CLASS:
 *   Encapsulates monetary values with consistent 2-decimal rounding.
 *   - Immutable: every operation returns a NEW Money object.
 *   - plus() for addition, toString() for consistent formatting.
 *   - Centralizes rounding logic in one place.
 *
 * BENEFIT:
 *   - All money values in the system are consistently rounded.
 *   - No more scattered String.format("%.2f", ...) calls.
 *   - Type safety: you can't accidentally mix Money with plain doubles.
 */
public class Money {
    public final double amount;
    public Money(double amount) { this.amount = round2(amount); }

    public Money plus(Money other) { return new Money(this.amount + other.amount); }

    private static double round2(double x) { return Math.round(x * 100.0) / 100.0; }

    @Override public String toString() { return String.format("%.2f", amount); }
}
