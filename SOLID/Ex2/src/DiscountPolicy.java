/*
 * ─── SRP REFACTOR: DiscountPolicy (Interface / Abstraction) ───
 *
 * PROBLEM SOLVED:
 *   Discount logic was hard-coded inside CafeteriaSystem.checkout().
 *   The discount rules ("student gets ₹10 off if subtotal >= 180",
 *   "staff gets ₹15 off if 3+ items") were mixed with tax logic,
 *   formatting, and persistence — all in one method.
 *
 * WHY WE CREATED THIS INTERFACE:
 *   Same reasoning as TaxPolicy:
 *   - SRP: Discount calculation is its own responsibility.
 *   - OCP: New discount types (e.g., FestivalDiscountPolicy,
 *     BulkDiscountPolicy) can be added by implementing this interface —
 *     no changes needed in CafeteriaSystem.
 *   - Dependency Inversion: CafeteriaSystem depends on this abstraction,
 *     not on specific discount rules.
 *   - Testability: Can inject a stub (e.g., always returns 0) for testing.
 *
 * HOW IT WORKS:
 *   - discountAmount(customerType, subtotal, distinctLines) returns the
 *     discount value in rupees.
 *   - The caller (CafeteriaSystem) just subtracts this from the total.
 */
public interface DiscountPolicy {
    double discountAmount(String customerType, double subtotal, int distinctLines);
}
