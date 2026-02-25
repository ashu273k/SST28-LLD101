import java.util.*;

/*
 * ─── BookingRequest (Value Object / DTO) ───
 *
 * WHY THIS CLASS EXISTS:
 *   Carries the booking input data: which room type and which add-ons.
 *   Immutable public final fields. The calculator reads this; it doesn't
 *   modify it. Clean data flow from the demo to the calculator.
 */
public class BookingRequest {
    public final int roomType;
    public final List<AddOn> addOns;

    public BookingRequest(int roomType, List<AddOn> addOns) {
        this.roomType = roomType;
        this.addOns = addOns;
    }
}
