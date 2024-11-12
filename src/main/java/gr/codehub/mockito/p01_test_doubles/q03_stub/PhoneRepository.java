package gr.codehub.mockito.p01_test_doubles.q03_stub;

import java.util.List;

public interface PhoneRepository {
    List<Phone> findNewPhones(int days);
}
