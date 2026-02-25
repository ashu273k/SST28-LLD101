import java.util.*;

/*
 * ─── Demo05 — Composition Root / Wiring ───
 *
 * HOW OCP IS VISIBLE HERE:
 *   - The BookingRequest specifies a room type and add-ons.
 *   - The calculator treats them all as uniform FeeComponent objects.
 *   - To add a new room type: RoomFee.register(NEW_TYPE, price) before calling process().
 *   - To add a new add-on: add enum constant to AddOn, use it in the request.
 *   - The calculator code itself never changes.
 */
public class Demo05 {
    public static void main(String[] args) {
        System.out.println("=== Hostel Fee Calculator ===");
        BookingRequest req = new BookingRequest(LegacyRoomTypes.DOUBLE, List.of(AddOn.LAUNDRY, AddOn.MESS));
        HostelFeeCalculator calc = new HostelFeeCalculator(new FakeBookingRepo());
        calc.process(req);
    }
}
