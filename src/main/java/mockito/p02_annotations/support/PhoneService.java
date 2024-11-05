package mockito.p02_annotations.support;

import java.util.List;

public class PhoneService {

    private final PhoneRepository phoneRepository;

    public PhoneService(PhoneRepository phoneRepository) {
        this.phoneRepository = phoneRepository;
    }

    public List<Phone> getNewPhonesWithAppliedDiscount(int discountRate, int days) {
        List<Phone> newPhones = phoneRepository.findNewPhones(days);
        // 10% discount for 600 -> 540

        for (Phone phone : newPhones) {
            int price = phone.getPrice();
            int newPrice = price - (discountRate * price / 100);
            phone.setPrice(newPrice);
        }

        return newPhones;
    }
}
