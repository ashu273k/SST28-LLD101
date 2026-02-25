import java.util.List;

/*
 * ─── SRP: InvoiceData (Data Transfer Object) ───
 *
 * WHY THIS CLASS EXISTS:
 *   This DTO carries all computed invoice values from the billing logic
 *   to the formatter. It's the "bridge" between computation and presentation.
 *
 * PROBLEM IT SOLVES:
 *   Without this, the formatter would need raw order data and would have
 *   to redo calculations, or the billing method would need to build strings.
 *   InvoiceData cleanly separates "compute the numbers" from "format them".
 *
 * DESIGN DECISIONS:
 *   - All fields are public final (immutable after creation).
 *   - Contains: invoiceId, item names/qtys/totals, subtotal, tax, discount, total.
 *   - Created by CafeteriaSystem, consumed by InvoiceFormatter.
 */
public class InvoiceData {
    public final String invoiceId;
    public final List<String> itemNames;
    public final List<Integer> itemQtys;
    public final List<Double> itemLineTotals;
    public final double subtotal;
    public final double taxPercent;
    public final double tax;
    public final double discount;
    public final double total;

    public InvoiceData(
        String invoiceId,
        List<String> itemNames,
        List<Integer> itemQtys,
        List<Double> itemLineTotals,
        double subtotal,
        double taxPercent,
        double tax,
        double discount,
        double total
    ) {
        this.invoiceId = invoiceId;
        this.itemNames = itemNames;
        this.itemQtys = itemQtys;
        this.itemLineTotals = itemLineTotals;
        this.subtotal = subtotal;
        this.taxPercent = taxPercent;
        this.tax = tax;
        this.discount = discount;
        this.total = total;
    }
    
}
