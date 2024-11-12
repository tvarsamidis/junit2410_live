package gr.codehub.mockito.p01_test_doubles.q05_mock;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PhoneRepositoryMock implements PhoneRepository {

    int saveCalled = 0;
    Phone lastAddedPhone = null;

    @Override
    public void save(Phone phone) {
        saveCalled++;
        lastAddedPhone = phone;
    }

    public void verify(Phone phone, int times) {
        assertEquals(times, saveCalled);
        assertEquals(phone, lastAddedPhone);
    }

}
