public class MessPricing implements AddOnPricingStrategy {
    @Override
    public Money getMonthlyFee() {
        return new Money(1000.0);
    }

    @Override
    public AddOn getAddOnType() {
        return AddOn.MESS;
    }
}
