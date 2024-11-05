package mockito.p07_spies;

public class PhoneService {

    public Phone findPhone(String phoneId) {
        // null also works
        // return null;
        throw new RuntimeException("Method not implemented");
    }

    public int getAppliedDiscount(Phone phone, int discountRate) {
        int price = phone.getPrice();
        int newPrice = price - (price * discountRate / 100);
        return newPrice;
    }
}
