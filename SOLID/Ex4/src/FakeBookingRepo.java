/*
 * ─── FakeBookingRepo — Persistence Stub ───
 *
 * WHY THIS CLASS EXISTS:
 *   Simulates saving a booking. In production, this would persist to a
 *   database. Could be abstracted behind an interface (like InvoiceStore
 *   in Ex2) for full Dependency Inversion.
 */
public class FakeBookingRepo {
    public void save(String id, BookingRequest req, Money monthly, Money deposit) {
        System.out.println("Saved booking: " + id);
    }
}
