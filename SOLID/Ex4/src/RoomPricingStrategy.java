public interface RoomPricingStrategy {
    Money getMonthlyFee();
    int getRoomType();
}
