package gr.codehub.mockito.p09_argument_matchers;

import java.time.LocalDate;
import java.util.List;

public interface PhoneRepository {
    void save(Phone phone);

    Phone findPhoneById(String phoneId);

    Phone findPhoneByModelAndReleaseDate(String model, LocalDate localDate);

    Phone findPhoneByModelAndPriceAndIsFastCharge(String model, int price, boolean isFastCharge);

    void saveAll(List<Phone> phones);
}
