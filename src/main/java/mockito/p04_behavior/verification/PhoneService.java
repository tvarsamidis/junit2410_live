package mockito.p04_behavior.verification;

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
        if (phoneId == null) {
            return;
        }
        Phone phone = phoneRepository.findPhoneById(phoneId);
        if (phone.getPrice() == updatedPrice) {
            return;
        }
        phone.setPrice(updatedPrice);
        phoneRepository.save(phone);
    }
}
