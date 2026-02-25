import java.util.*;

/*
 * ─── SRP REFACTOR: CafeteriaSystem (Orchestrator) ───
 *
 * PROBLEM SOLVED:
 *   Originally, this class was a "god class" that did EVERYTHING:
 *   menu management, pricing, tax calculation, discount calculation,
 *   invoice formatting, and persistence — ALL inside the checkout() method.
 *   It had 6+ reasons to change, violating SRP.
 *
 * WHY WE REFACTORED IT:
 *   After applying SRP, this class now ONLY orchestrates the billing workflow.
 *   It delegates every specialized task to a dedicated collaborator:
 *     - TaxPolicy       → calculates tax percentage (via interface)
 *     - DiscountPolicy   → calculates discount amount (via interface)
 *     - InvoiceFormatter  → formats invoice as a printable string
 *     - InvoiceStore      → saves the invoice (via interface)
 *
 * HOW IT WORKS:
 *   1. Constructor receives all 4 dependencies via Dependency Injection.
 *   2. checkout() computes line totals and subtotal (simple arithmetic).
 *   3. DELEGATES tax to taxPolicy.taxPercent().
 *   4. DELEGATES discount to discountPolicy.discountAmount().
 *   5. Builds an InvoiceData DTO with all computed values.
 *   6. DELEGATES formatting to formatter.format().
 *   7. DELEGATES persistence to store.save().
 *
 * KEY DESIGN DECISIONS:
 *   - Uses INTERFACES (TaxPolicy, DiscountPolicy, InvoiceStore) so
 *     implementations can be swapped without editing this class.
 *   - InvoiceData acts as a bridge between computation and formatting.
 *   - Adding a new tax/discount policy = create a new class, wire it in
 *     Demo02. Zero changes here.
 *
 * BENEFIT:
 *   - This class reads like a simple workflow: compute → format → save.
 *   - Each concern is testable and replaceable independently.
 *   - Follows SRP: changes ONLY if the checkout workflow itself changes.
 */
public class CafeteriaSystem {
    private final Map<String, MenuItem> menu = new LinkedHashMap<>();
    private final TaxPolicy taxPolicy;
    private final DiscountPolicy discountPolicy;
    private final InvoiceFormatter formatter;
    private final InvoiceStore store;
    private int invoiceSeq = 1000;

    // ─── Dependency Injection via Constructor ───
    // All rule/formatting/storage collaborators are injected, not created here.
    // CafeteriaSystem never does "new StudentStaffTaxPolicy()" — the caller decides.
    public CafeteriaSystem(TaxPolicy taxPolicy, DiscountPolicy discountPolicy,
                           InvoiceFormatter formatter, InvoiceStore store) {
        this.taxPolicy = taxPolicy;
        this.discountPolicy = discountPolicy;
        this.formatter = formatter;
        this.store = store;
    }

    public void addToMenu(MenuItem i) { menu.put(i.id, i); }

    // ─── The Orchestration Method ───
    // This method coordinates the billing steps but DELEGATES specialized work.
    public void checkout(String customerType, List<OrderLine> lines) {
        String invId = "INV-" + (++invoiceSeq);

        List<String> names = new ArrayList<>();
        List<Integer> qtys = new ArrayList<>();
        List<Double> lineTotals = new ArrayList<>();
        double subtotal = 0.0;

        for (OrderLine l : lines) {
            MenuItem item = menu.get(l.itemId);
            double lineTotal = item.price * l.qty;
            subtotal += lineTotal;
            names.add(item.name);
            qtys.add(l.qty);
            lineTotals.add(lineTotal);
        }

        // DELEGATE tax calculation to the TaxPolicy interface
        double taxPct = taxPolicy.taxPercent(customerType);
        double tax = subtotal * (taxPct / 100.0);
        // DELEGATE discount calculation to the DiscountPolicy interface
        double discount = discountPolicy.discountAmount(customerType, subtotal, lines.size());
        double total = subtotal + tax - discount;

        // Build an InvoiceData DTO to bridge computation and formatting
        InvoiceData data = new InvoiceData(invId, names, qtys, lineTotals,
                subtotal, taxPct, tax, discount, total);

        // DELEGATE formatting to InvoiceFormatter
        String printable = formatter.format(data);
        System.out.print(printable);

        // DELEGATE persistence to InvoiceStore (interface)
        store.save(invId, printable);
        System.out.println("Saved invoice: " + invId + " (lines=" + store.countLines(invId) + ")");
    }
}
