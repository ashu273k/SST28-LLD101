import java.util.*;

/*
 * ─── SRP: ReceiptPrinter — Separated Printing Responsibility ───
 *
 * WHY THIS CLASS EXISTS:
 *   Printing the booking receipt was originally inside the calculator method.
 *   Extracting it follows SRP: the calculator computes, the printer displays.
 *   Changing the receipt format (e.g., adding a header, going HTML) = edit
 *   only this class.
 */
public class ReceiptPrinter {
    public static void print(BookingRequest req, Money monthly, Money deposit) {
        System.out.println("Room: " + LegacyRoomTypes.nameOf(req.roomType) + " | AddOns: " + req.addOns);
        System.out.println("Monthly: " + monthly);
        System.out.println("Deposit: " + deposit);
        System.out.println("TOTAL DUE NOW: " + monthly.plus(deposit));
    }
}
