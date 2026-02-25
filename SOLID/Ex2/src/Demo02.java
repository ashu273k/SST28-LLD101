import java.util.*;

/*
 * ─── Demo02 — Composition Root / Wiring ───
 *
 * WHY THIS CLASS EXISTS:
 *   This is where all SRP-separated components are ASSEMBLED together.
 *   It acts as the "composition root" — the ONE place where we decide
 *   which concrete implementations to use.
 *
 * HOW THE SRP REFACTOR IS VISIBLE HERE:
 *   - StudentStaffTaxPolicy is injected as the TaxPolicy implementation.
 *   - StudentStaffDiscountPolicy is injected as the DiscountPolicy implementation.
 *   - InvoiceFormatter handles all formatting.
 *   - FileStore (as InvoiceStore) handles persistence.
 *   - CafeteriaSystem just orchestrates.
 *
 * KEY DESIGN POINT:
 *   To switch to a different tax policy (e.g., GSTTaxPolicy), we change
 *   ONLY this file — not CafeteriaSystem or any other class.
 */
public class Demo02 {
    public static void main(String[] args) {
        System.out.println("=== Cafeteria Billing ===");

        // ─── Assemble all SRP-separated components via Dependency Injection ───
        CafeteriaSystem sys = new CafeteriaSystem(
                new StudentStaffTaxPolicy(),      // Tax rules (implements TaxPolicy)
                new StudentStaffDiscountPolicy(),  // Discount rules (implements DiscountPolicy)
                new InvoiceFormatter(),            // Formatting responsibility
                new FileStore()                    // Persistence (implements InvoiceStore)
        );

        sys.addToMenu(new MenuItem("M1", "Veg Thali", 80.00));
        sys.addToMenu(new MenuItem("C1", "Coffee", 30.00));
        sys.addToMenu(new MenuItem("S1", "Sandwich", 60.00));

        List<OrderLine> order = List.of(
                new OrderLine("M1", 2),
                new OrderLine("C1", 1)
        );

        sys.checkout("student", order);
    }
}
