/*
 * ─── OrderLine (Value Object) ───
 *
 * WHY THIS CLASS EXISTS:
 *   Represents a single line in a customer's order: which item (by ID)
 *   and how many. Immutable with public final fields.
 */
public class OrderLine {
    public final String itemId;
    public final int qty;

    public OrderLine(String itemId, int qty) {
        this.itemId = itemId; this.qty = qty;
    }
}
