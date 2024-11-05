package mockito.p01_test_doubles.q01_fake;

public class PhoneService {

    private final PhoneRepository phoneRepository;

    public PhoneService(PhoneRepository phoneRepository) {
        this.phoneRepository = phoneRepository;
    }

    public void addPhone(Phone phone) {
        phoneRepository.save(phone);
    }

    public int findNumberOfPhones() {
        return phoneRepository.findAll().size();
    }
}
