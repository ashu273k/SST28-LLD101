import java.util.*;

/*
 * ─── OCP REFACTOR: HostelFeeCalculator (Orchestrator) ───
 *
 * PROBLEM SOLVED:
 *   Originally this class had a switch-case on room types AND if/else
 *   branches for each add-on. It also mixed calculation with printing
 *   and persistence. Every new room type or add-on required editing
 *   this class (OCP + SRP violation).
 *
 * WHY WE REFACTORED IT:
 *   After refactoring, the calculator is GENERIC:
 *   - Builds a list of FeeComponent objects (room + add-ons).
 *   - Iterates and sums their monthlyFee() — a uniform loop.
 *   - No switch, no if/else on types. Doesn't know WHICH components exist.
 *
 * HOW IT WORKS:
 *   1. calculateMonthly() creates a list of FeeComponent:
 *      - A RoomFee for the room type (looks up price from registry).
 *      - All AddOn enum values from the request (each is a FeeComponent).
 *   2. Iterates the list, calling monthlyFee() on each, summing totals.
 *   3. process() adds a fixed deposit, delegates printing to ReceiptPrinter,
 *      and saves via FakeBookingRepo.
 *
 * KEY OCP BENEFIT:
 *   - New room type? → RoomFee.register(newType, price). No edits here.
 *   - New add-on? → Add enum constant to AddOn. No edits here.
 *   - New fee type (e.g., LateFee)? → Implement FeeComponent, add to list.
 *     The loop handles it automatically.
 *
 * ALSO FOLLOWS SRP:
 *   - Calculation is in calculateMonthly().
 *   - Printing is delegated to ReceiptPrinter.
 *   - Persistence is delegated to FakeBookingRepo.
 */
public class HostelFeeCalculator {
    private final FakeBookingRepo repo;

    public HostelFeeCalculator(FakeBookingRepo repo) { this.repo = repo; }

    public void process(BookingRequest req) {
        Money monthly = calculateMonthly(req);
        Money deposit = new Money(5000.00);

        ReceiptPrinter.print(req, monthly, deposit);

        String bookingId = "H-" + (7000 + new Random(1).nextInt(1000)); // deterministic-ish
        repo.save(bookingId, req, monthly, deposit);
    }

    private Money calculateMonthly(BookingRequest req) {
        List<FeeComponent> components = new ArrayList<>();
        components.add(new RoomFee(req.roomType));
        components.addAll(req.addOns);

        Money total = new Money(0);
        for (FeeComponent comp : components) {
            total = total.plus(comp.monthlyFee());
        }
        return total;
    }
}
