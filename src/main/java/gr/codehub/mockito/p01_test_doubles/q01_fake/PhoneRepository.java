package gr.codehub.mockito.p01_test_doubles.q01_fake;

import java.util.List;

public interface PhoneRepository {
    void save(Phone phone);

    List<Phone> findAll();
}
