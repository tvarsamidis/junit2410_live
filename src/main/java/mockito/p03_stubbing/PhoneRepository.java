package mockito.p03_stubbing;

import java.util.List;

public interface PhoneRepository {
    List<Phone> findNewPhones(int days);

    Phone findPhoneByPhoneId(String phoneId);

    void save(Phone phone);
}
