package gr.codehub.mockito.p05_exception_handling;

import java.sql.SQLException;
import java.util.List;

public interface PhoneRepository {
    List<Phone> findAllPhones() throws SQLException;

    void save(Phone phone) throws SQLException;
}
