import java.util.*;

/**
 * Maps a legacy room-type code to its monthly base price.
 * New room types are added by calling {@link #register} —
 * the calculator never needs to change.
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
