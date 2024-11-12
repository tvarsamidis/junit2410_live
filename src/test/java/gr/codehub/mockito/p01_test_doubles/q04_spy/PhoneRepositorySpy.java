package gr.codehub.mockito.p01_test_doubles.q04_spy;

public class PhoneRepositorySpy implements PhoneRepository {

    int saveCalled = 0;
    Phone lastAddedPhone = null;

    @Override
    public void save(Phone phone) {
        saveCalled++;
        lastAddedPhone = phone;
    }

    public int timesCalled() {
        return saveCalled;
    }

    public boolean calledWith(Phone phone) {
        return lastAddedPhone.equals(phone);
    }

}
