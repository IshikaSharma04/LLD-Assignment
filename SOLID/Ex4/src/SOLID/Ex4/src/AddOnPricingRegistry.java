import java.util.*;

public class AddOnPricingRegistry {
    private final Map<AddOn, AddOnPricingStrategy> registry = new HashMap<>();

    public AddOnPricingRegistry() {
        register(new MessPricing());
        register(new LaundryPricing());
        register(new GymPricing());
    }

    private void register(AddOnPricingStrategy strategy) {
        registry.put(strategy.getAddOnType(), strategy);
    }

    public AddOnPricingStrategy get(AddOn addOn) {
        return registry.get(addOn);
    }
}
