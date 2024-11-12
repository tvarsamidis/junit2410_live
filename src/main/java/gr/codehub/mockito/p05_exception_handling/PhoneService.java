package gr.codehub.mockito.p05_exception_handling;

import java.sql.SQLException;
import java.util.List;

public class PhoneService {

    private final PhoneRepository phoneRepository;

    public PhoneService(PhoneRepository phoneRepository) {
        this.phoneRepository = phoneRepository;
    }

    public int getTotalPriceOfPhones() {
        List<Phone> phones = null;
        try {
            phones = phoneRepository.findAllPhones();
        } catch (SQLException e) {
            // log exception
            throw new DatabaseReadException("Unable to read from database due to - " + e.getMessage());
        }

        // do with for loop, old fashioned
//        int totalPrice = 0;
//        for (Phone phone : phones) {
//            totalPrice = totalPrice + phone.getPrice();
//        }
//        return totalPrice;

        // do with streams
        return phones.stream()
                .mapToInt(Phone::getPrice)
                .sum();
    }

    public void addPhone(Phone phone) {
        try {
            phoneRepository.save(phone);
        } catch (SQLException e) {
            // log exception
            throw new DatabaseWriteException("Unable to write in database due to - " + e.getMessage());
        }
    }
}
