package gr.codehub.mockito.p01_test_doubles.q02_dummy;

public class PhoneService {

    private final PhoneRepository phoneRepository;
    private final EmailService emailService;

    public PhoneService(PhoneRepository phoneRepository, EmailService emailService) {
        this.phoneRepository = phoneRepository;
        this.emailService = emailService;
    }

    public void addPhone(Phone phone) {
        phoneRepository.save(phone);
    }

    public int findNumberOfPhones() {
        return phoneRepository.findAll().size();
    }

    // Other methods which use EmailService
}
