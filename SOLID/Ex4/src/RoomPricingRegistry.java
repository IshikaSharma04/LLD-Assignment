import java.util.*;

public class RoomPricingRegistry {
    private final Map<Integer, RoomPricingStrategy> registry = new HashMap<>();

    public RoomPricingRegistry() {
        register(new SingleRoomPricing());
        register(new DoubleRoomPricing());
        register(new TripleRoomPricing());
        register(new DeluxeRoomPricing());
    }

    private void register(RoomPricingStrategy strategy) {
        registry.put(strategy.getRoomType(), strategy);
    }

    public RoomPricingStrategy get(int roomType) {
        return registry.getOrDefault(roomType, new DeluxeRoomPricing());
    }
}
