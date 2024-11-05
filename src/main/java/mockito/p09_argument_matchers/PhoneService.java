package mockito.p09_argument_matchers;

import java.time.LocalDate;
import java.util.List;

public class PhoneService {

    private final PhoneRepository phoneRepository;

    public PhoneService(PhoneRepository phoneRepository) {
        this.phoneRepository = phoneRepository;
    }

    public void addPhone(Phone phone) {
        if (phone.getPrice() <= 600) {
            return;
        }
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

    public void updatePrice(String phoneId, int updatedPrice) {
        Phone phone = phoneRepository.findPhoneById(phoneId);
        phone.setPrice(updatedPrice);
        phoneRepository.save(phone);
    }

    public Phone getPhoneByModelAndReleaseDate(String model, LocalDate localDate) {
        return phoneRepository.findPhoneByModelAndReleaseDate(model, localDate);
    }

    public Phone getPhoneByModelAndPriceAndIsFastCharge(String model, int price, boolean isFastCharge) {
        return phoneRepository.findPhoneByModelAndPriceAndIsFastCharge(model, price, isFastCharge);
    }

    public void addPhones(List<Phone> phones) {
        phoneRepository.saveAll(phones);
    }
}
