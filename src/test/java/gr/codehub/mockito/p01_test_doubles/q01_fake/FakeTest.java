package gr.codehub.mockito.p01_test_doubles.q01_fake;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class FakeTest {

    @Test
    public void testFake() {
        PhoneRepository phoneRepository = new FakePhoneRepository();
        PhoneService phoneService = new PhoneService(phoneRepository);

        phoneService.addPhone(new Phone("1234", "CodePhone Pro", 300, LocalDate.now()));
        phoneService.addPhone(new Phone("1235", "CodePhone Standard", 250, LocalDate.now()));

        assertEquals(2, phoneService.findNumberOfPhones());

    }

    @Test
    public void testFakeWithMockito() {
        PhoneRepository phoneRepository = mock(PhoneRepository.class);
        PhoneService phoneService = new PhoneService(phoneRepository);

        Phone phone1 = new Phone("1234", "CodePhone Pro", 300, LocalDate.now());
        Phone phone2 = new Phone("1235", "CodePhone Standard", 250, LocalDate.now());

        List<Phone> phones = new ArrayList<>();
        phones.add(phone1);
        phones.add(phone2);

        when(phoneRepository.findAll()).thenReturn(phones);

        assertEquals(2, phoneService.findNumberOfPhones());
    }

}
