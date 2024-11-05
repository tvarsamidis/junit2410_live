package mockito.p07_spies;


public class PhoneManager {

    private final PhoneService phoneService;

    public PhoneManager(PhoneService phoneService) {
        this.phoneService = phoneService;
    }

    public int applyDiscountOnPhone(String phoneId, int discountRate) {
        Phone phone = phoneService.findPhone(phoneId); // We need to Mock
        int discountedPrice = phoneService.getAppliedDiscount(phone, discountRate);
        return discountedPrice;
    }
}
