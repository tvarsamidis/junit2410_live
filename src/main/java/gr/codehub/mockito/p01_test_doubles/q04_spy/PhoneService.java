package gr.codehub.mockito.p01_test_doubles.q04_spy;

public class PhoneService {

    private final PhoneRepository phoneRepository;

    public PhoneService(PhoneRepository phoneRepository) {
        this.phoneRepository = phoneRepository;
    }

    public void addPhone(Phone phone) {
        if (phone.getPrice() > 500) {
            return;
        }
        phoneRepository.save(phone);
    }

}
