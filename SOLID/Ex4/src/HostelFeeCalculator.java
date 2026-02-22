import java.util.*;

public class HostelFeeCalculator {
    private final FakeBookingRepo repo;
    private final RoomPricingRegistry roomPricing;
    private final AddOnPricingRegistry addOnPricing;

    public HostelFeeCalculator(FakeBookingRepo repo) {
        this.repo = repo;
        this.roomPricing = new RoomPricingRegistry();
        this.addOnPricing = new AddOnPricingRegistry();
    }

    public void process(BookingRequest req) {
        Money monthly = calculateMonthly(req);
        Money deposit = new Money(5000.00);

        ReceiptPrinter.print(req, monthly, deposit);

        String bookingId = "H-" + (7000 + new Random(1).nextInt(1000));
        repo.save(bookingId, req, monthly, deposit);
    }

    private Money calculateMonthly(BookingRequest req) {
        RoomPricingStrategy roomStrategy = roomPricing.get(req.roomType);
        Money total = roomStrategy.getMonthlyFee();

        for (AddOn addOn : req.addOns) {
            AddOnPricingStrategy addOnStrategy = addOnPricing.get(addOn);
            if (addOnStrategy != null) {
                total = total.plus(addOnStrategy.getMonthlyFee());
            }
        }
        return total;
    }
}
