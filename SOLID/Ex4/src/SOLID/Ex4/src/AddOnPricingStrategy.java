public interface AddOnPricingStrategy {
    Money getMonthlyFee();
    AddOn getAddOnType();
}
