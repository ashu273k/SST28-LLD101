import java.util.*;

/*
 * ─── OCP: RoomFee — Room Pricing as a FeeComponent ───
 *
 * PROBLEM SOLVED:
 *   Room pricing was a switch-case inside the calculator. Each new room type
 *   meant editing that switch. Now room prices live in a registry (Map),
 *   and RoomFee implements FeeComponent.
 *
 * HOW IT WORKS:
 *   - A static Map<Integer, Money> holds prices for each room type.
 *   - Pre-loaded with SINGLE, DOUBLE, TRIPLE, DELUXE prices.
 *   - monthlyFee() looks up the price by roomType code.
 *   - register() allows adding NEW room types at runtime without editing code.
 *
 * WHY THIS DESIGN:
 *   - The calculator never has a switch on room types — it just calls
 *     monthlyFee() on whatever FeeComponent it receives.
 *   - To add a new room type: call RoomFee.register(5, new Money(18000)).
 *     No changes to the calculator or this class's core logic.
 *   - This is OCP: open for extension (via register), closed for modification.
 */
public class RoomFee implements FeeComponent {
    private static final Map<Integer, Money> PRICES = new HashMap<>();

    static {
        PRICES.put(LegacyRoomTypes.SINGLE, new Money(14000.0));
        PRICES.put(LegacyRoomTypes.DOUBLE, new Money(15000.0));
        PRICES.put(LegacyRoomTypes.TRIPLE, new Money(12000.0));
        PRICES.put(LegacyRoomTypes.DELUXE, new Money(16000.0));
    }

    private final int roomType;

    public RoomFee(int roomType) {
        this.roomType = roomType;
    }

    /** Register a new (or override an existing) room-type price. */
    public static void register(int roomType, Money price) {
        PRICES.put(roomType, price);
    }

    @Override
    public Money monthlyFee() {
        Money price = PRICES.get(roomType);
        if (price == null) {
            throw new IllegalArgumentException("Unknown room type: " + roomType);
        }
        return price;
    }
}
