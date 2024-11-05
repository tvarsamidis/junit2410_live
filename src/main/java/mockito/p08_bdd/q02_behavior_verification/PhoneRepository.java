package mockito.p08_bdd.q02_behavior_verification;

public interface PhoneRepository {
    void save(Phone phone);

    Phone findPhoneById(String phoneId);
}
