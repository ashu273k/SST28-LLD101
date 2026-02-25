/*
 * ─── OCP: AddOn — Add-On Pricing as a FeeComponent (Enum Strategy) ───
 *
 * PROBLEM SOLVED:
 *   Add-on pricing (MESS, LAUNDRY, GYM) was handled with if/else branches
 *   in the calculator. Now each add-on IS a FeeComponent.
 *
 * HOW IT WORKS:
 *   - Each enum constant (MESS, LAUNDRY, GYM) holds its price as a Money.
 *   - Implements FeeComponent.monthlyFee() → returns its price.
 *   - The calculator treats add-ons exactly like rooms: just calls monthlyFee().
 *
 * WHY THIS DESIGN:
 *   - Adding a new add-on (e.g., PARKING(200.0)) = add ONE enum constant.
 *     The calculator loop doesn't change.
 *   - All add-ons are uniform FeeComponent objects — no special-casing.
 *   - Enum ensures type safety and prevents invalid add-on values.
 */
public enum AddOn implements FeeComponent {
    MESS(1000.0), LAUNDRY(500.0), GYM(300.0);

    private final Money price;

    AddOn(double price) { this.price = new Money(price); }

    @Override
    public Money monthlyFee() { return price; }
}
