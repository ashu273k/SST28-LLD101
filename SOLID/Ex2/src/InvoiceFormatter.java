/*
 * ─── SRP REFACTOR: InvoiceFormatter ───
 *
 * PROBLEM SOLVED:
 *   Invoice formatting (building the string with "Invoice# ...", line items,
 *   subtotal, tax, discount, total) was previously embedded inside
 *   CafeteriaSystem.checkout(). This mixed money calculations with string
 *   formatting — any change to the invoice look required editing the
 *   billing logic.
 *
 * WHY WE CREATED THIS CLASS:
 *   Separated formatting into its own class with ONE responsibility:
 *   turning an InvoiceData object into a printable string. Follows SRP.
 *
 * HOW IT WORKS:
 *   - Takes an InvoiceData DTO containing all computed values.
 *   - Builds a formatted string with line items, subtotal, tax, discount, total.
 *   - Returns the string — CafeteriaSystem just prints and stores it.
 *
 * BENEFIT:
 *   - Changing the invoice format (e.g., to HTML or PDF) = change this class.
 *   - CafeteriaSystem doesn't know or care about formatting details.
 *   - Formatting can be tested independently (pass InvoiceData, assert string).
 */
public class InvoiceFormatter {

    public String format(InvoiceData data) {
        StringBuilder out = new StringBuilder();
        out.append("Invoice# ").append(data.invoiceId).append("\n");

        for (int i = 0; i < data.itemNames.size(); i++) {
            out.append(String.format("- %s x%d = %.2f\n",
                    data.itemNames.get(i), data.itemQtys.get(i), data.itemLineTotals.get(i)));
        }

        out.append(String.format("Subtotal: %.2f\n", data.subtotal));
        out.append(String.format("Tax(%.0f%%): %.2f\n", data.taxPercent, data.tax));
        out.append(String.format("Discount: -%.2f\n", data.discount));
        out.append(String.format("TOTAL: %.2f\n", data.total));

        return out.toString();
    }
}