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