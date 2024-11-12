package gr.codehub.mockito.p08_bdd.q02_behavior_verification;

public class PhoneService {

    private final PhoneRepository phoneRepository;

    public PhoneService(PhoneRepository phoneRepository) {
        this.phoneRepository = phoneRepository;
    }

    public void updatePrice(String phoneId, int updatedPrice) {
        Phone phone = phoneRepository.findPhoneById(phoneId);
        phone.setPrice(updatedPrice);
        phoneRepository.save(phone);
    }
}
