/**
 * Abstraction for any pricing component (room, add-on, late fee, etc.)
 * that contributes to the monthly hostel fee.
 * New fee types can be added by implementing this interface — no
 * existing code needs to change (OCP).
 */
public interface FeeComponent {
    Money monthlyFee();
}
