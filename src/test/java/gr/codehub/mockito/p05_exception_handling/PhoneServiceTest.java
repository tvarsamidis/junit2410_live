package gr.codehub.mockito.p05_exception_handling;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.SQLException;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PhoneServiceTest {

    @InjectMocks
    private PhoneService phoneService;

    @Mock
    private PhoneRepository phoneRepository;

    @Test
    public void testTotalPriceOfPhones() throws SQLException {
        when(phoneRepository.findAllPhones()).thenThrow(SQLException.class);
        assertThrows(DatabaseReadException.class, () -> phoneService.getTotalPriceOfPhones());
    }

    @Test
    public void testTotalPriceOfPhones3() throws SQLException {
//		when(phoneRepository.findAllPhones()).thenThrow(SQLException.class);
        given(phoneRepository.findAllPhones()).willThrow(SQLException.class);
        assertThrows(DatabaseReadException.class, () -> phoneService.getTotalPriceOfPhones());
    }

    @Test
    public void testTotalPriceOfPhones1() throws SQLException {
        when(phoneRepository.findAllPhones()).thenThrow(new SQLException("Database not available"));
        assertThrows(DatabaseReadException.class, () -> phoneService.getTotalPriceOfPhones());
    }

    @Test
    public void testAddPhone() throws SQLException {
        Phone phone = new Phone(null, "CodePhone Pro", 700, LocalDate.now());
        doThrow(SQLException.class).when(phoneRepository).save(phone);
        assertThrows(DatabaseWriteException.class, () -> phoneService.addPhone(phone));
    }

    @Test
    public void testTotalPriceOfPhones2() throws SQLException {
        when(phoneRepository.findAllPhones()).thenThrow(new SQLException("Database not available"));
        assertThrows(DatabaseReadException.class, () -> phoneService.getTotalPriceOfPhones());
    }

}
