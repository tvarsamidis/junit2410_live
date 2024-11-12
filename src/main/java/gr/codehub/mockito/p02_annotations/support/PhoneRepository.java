package gr.codehub.mockito.p02_annotations.support;

import java.util.List;

public interface PhoneRepository {
    List<Phone> findNewPhones(int days);
}
