package gr.codehub.mockito.p01_test_doubles.q01_fake;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FakePhoneRepository implements PhoneRepository {

    // In memory database, HashMap or List
    Map<String, Phone> phoneStore = new HashMap<>();

    @Override
    public void save(Phone phone) {
        phoneStore.put(phone.getPhoneId(), phone);
    }

    @Override
    public List<Phone> findAll() {
        return new ArrayList<>(phoneStore.values());
    }
}
