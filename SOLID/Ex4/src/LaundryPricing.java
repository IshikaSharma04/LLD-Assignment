public class LaundryPricing implements AddOnPricingStrategy {
    @Override
    public Money getMonthlyFee() {
        return new Money(500.0);
    }

    @Override
    public AddOn getAddOnType() {
        return AddOn.LAUNDRY;
    }
}
