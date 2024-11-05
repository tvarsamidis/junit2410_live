package mockito.p03_stubbing;

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

    public int calculateTotalCost(List<String> phoneIds) {
        int total = 0;
        for (String phoneId : phoneIds) {
            Phone phone = phoneRepository.findPhoneByPhoneId(phoneId);
            total = total + phone.getPrice();
        }
        return total;
    }

    public void addPhone(Phone phone) {
        phoneRepository.save(phone);
    }

    public void addPhone(PhoneRequest phoneRequest) {
        if (phoneRequest.getPrice() <= 600) {
            return;
        }
        Phone phone = new Phone();
        phone.setModel(phoneRequest.getModel());
        phone.setPrice(phoneRequest.getPrice());
        phone.setReleaseDate(phoneRequest.getReleaseDate());
        phoneRepository.save(phone);
    }
}
