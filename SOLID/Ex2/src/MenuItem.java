/*
 * ─── MenuItem (Value Object) ───
 *
 * WHY THIS CLASS EXISTS:
 *   Represents a single item on the cafeteria menu. Simple data holder
 *   with id, name, and price. Public final fields keep it immutable.
 *
 * DESIGN NOTE:
 *   Kept deliberately simple — no logic, just data. This is a constraint
 *   from the exercise: "Keep MenuItem and OrderLine public fields unchanged."
 */
public class MenuItem {
    public final String id;
    public final String name;
    public final double price;

    public MenuItem(String id, String name, double price) {
        this.id = id; this.name = name; this.price = price;
    }
}
