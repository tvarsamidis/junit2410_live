package mockito.p08_bdd.q01_stubbing;

import java.util.List;

public interface PhoneRepository {
    List<Phone> findNewPhones(int days);
}
