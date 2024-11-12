package gr.codehub.mockito.p01_test_doubles.q03_stub;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PhoneRepositoryStub implements PhoneRepository {
    @Override
    public List<Phone> findNewPhones(int days) {
        List<Phone> newPhones = new ArrayList<>();
        Phone phone1 = new Phone("1234", "CodePhone Pro", 600, LocalDate.now());
        Phone phone2 = new Phone("1235", "CodePhone Standard", 500, LocalDate.now());

        newPhones.add(phone1);
        newPhones.add(phone2);

        return newPhones;
    }
}
