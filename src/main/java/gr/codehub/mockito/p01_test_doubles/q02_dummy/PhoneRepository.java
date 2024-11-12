package gr.codehub.mockito.p01_test_doubles.q02_dummy;

import java.util.List;

public interface PhoneRepository {
    void save(Phone phone);

    List<Phone> findAll();
}
