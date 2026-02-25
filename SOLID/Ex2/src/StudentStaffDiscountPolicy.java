/*
 * ─── SRP: StudentStaffDiscountPolicy — Concrete Discount Implementation ───
 *
 * WHY THIS CLASS EXISTS:
 *   This is the concrete implementation of DiscountPolicy for the
 *   student/staff use case. It encapsulates the discount rules:
 *   - Student: ₹10 off if subtotal >= ₹180, else ₹0.
 *   - Staff: ₹15 off if 3+ distinct items, else ₹5.
 *   - Others: no discount.
 *
 * PROBLEM IT SOLVES:
 *   These rules were previously inside CafeteriaSystem.checkout(). Extracting
 *   them here means the billing system doesn't encode ANY discount logic.
 *
 * HOW IT WORKS:
 *   Implements DiscountPolicy.discountAmount() with the specific rules above.
 *   CafeteriaSystem calls discountPolicy.discountAmount(...) without knowing
 *   which implementation it's using.
 *
 * BENEFIT:
 *   - To add a new discount scheme (e.g., "FestivalDiscountPolicy" with 20% off),
 *     just create a new class implementing DiscountPolicy. CafeteriaSystem and
 *     this class remain untouched.
 */
public class StudentStaffDiscountPolicy implements DiscountPolicy{
    
    @Override
    public double discountAmount(String customerType, double subtotal, int distinctLines) {
        // hard-coded policy (smell)
        if ("student".equalsIgnoreCase(customerType)) {
            if (subtotal >= 180.0) return 10.0;
            return 0.0;
        }
        if ("staff".equalsIgnoreCase(customerType)) {
            if (distinctLines >= 3) return 15.0;
            return 5.0;
        }
        return 0.0;
    }
}
