/*
 * ─── SRP REFACTOR: TaxPolicy (Interface / Abstraction) ───
 *
 * PROBLEM SOLVED:
 *   Tax calculation logic was hard-coded inside CafeteriaSystem.checkout().
 *   The system had if/else statements like:
 *     if ("student") return 5%;
 *     if ("staff") return 2%;
 *   This meant adding a new customer type (e.g., "visitor" at 12%) required
 *   editing the checkout method — violating SRP and OCP.
 *
 * WHY WE CREATED THIS INTERFACE:
 *   By defining TaxPolicy as an interface, we DECOUPLE the tax rules from
 *   the billing system. CafeteriaSystem only knows that "something" can
 *   give it a tax percentage — it doesn't know the specific rules.
 *   - SRP: Tax rules live in their own class, separate from billing.
 *   - OCP: New tax policies can be added by creating new implementations
 *     without touching CafeteriaSystem.
 *   - Dependency Inversion: CafeteriaSystem depends on the abstraction
 *     (TaxPolicy), not on concrete tax logic.
 *
 * HOW IT WORKS:
 *   - taxPercent(customerType) returns the tax percentage for a given type.
 *   - Concrete implementations (e.g., StudentStaffTaxPolicy) contain the
 *     actual if/else logic for specific customer types.
 */
public interface TaxPolicy {
    double taxPercent(String customerType);
}
