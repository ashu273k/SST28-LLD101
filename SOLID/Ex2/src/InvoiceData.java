import java.util.List;

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
