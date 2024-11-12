package gr.codehub.mockito.p01_test_doubles.q05_mock;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;

public class MockTest {

    @Test
    public void demoMock() {
        PhoneRepositoryMock phoneRepositoryMock = new PhoneRepositoryMock();
        PhoneService phoneService = new PhoneService(phoneRepositoryMock);

        Phone phone1 = new Phone("1234", "CodePhone Pro", 600, LocalDate.now());
        Phone phone2 = new Phone("1235", "CodePhone Standard", 500, LocalDate.now());

        phoneService.addPhone(phone1); // return
        phoneService.addPhone(phone2); // save will be called

        phoneRepositoryMock.verify(phone2, 1);

    }

    @Test
    public void demoMockWithMockito() {
        PhoneRepository phoneRepositoryMock = Mockito.mock(PhoneRepository.class);
        PhoneService phoneService = new PhoneService(phoneRepositoryMock);

        Phone phone1 = new Phone("1234", "CodePhone Pro", 600, LocalDate.now());
        Phone phone2 = new Phone("1235", "CodePhone Standard", 500, LocalDate.now());

        phoneService.addPhone(phone1); // return
        phoneService.addPhone(phone2); // save will be called

        Mockito.verify(phoneRepositoryMock, Mockito.times(1)).save(phone2);
        Mockito.verify(phoneRepositoryMock, Mockito.times(0)).save(phone1);

    }

}
