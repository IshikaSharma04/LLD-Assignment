public class GymPricing implements AddOnPricingStrategy {
    @Override
    public Money getMonthlyFee() {
        return new Money(300.0);
    }

    @Override
    public AddOn getAddOnType() {
        return AddOn.GYM;
    }
}
