package gr.codehub.mockito.p09_argument_matchers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyBoolean;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.anyList;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.contains;
import static org.mockito.Mockito.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PhoneServiceTest {

    @InjectMocks
    private PhoneService phoneService;

    @Mock
    private PhoneRepository phoneRepository;

    @Test
    public void testUpdatePrice() {
        Phone phone1 = new Phone("1234", "CodePhone Pro", 700, LocalDate.now());
        Phone phone2 = new Phone("1234", "CodePhone Pro", 600, LocalDate.now());
        when(phoneRepository.findPhoneById(any(String.class))).thenReturn(phone1);
        phoneService.updatePrice("1234", 600);
        verify(phoneRepository).save(phone2);
    }

    // Argument Matchers should be provided for all arguments
    // Argument Matchers can't be used outside p03_stubbing/verification
    @Test
    public void testValidUseOfArgumentMatchers() {
        Phone phone = new Phone("1234", "CodePhone Pro", 700, LocalDate.now());
        when(phoneRepository.findPhoneByModelAndReleaseDate("CodePhone Pro", LocalDate.now())).thenReturn(phone);
        Phone actualPhone = phoneService.getPhoneByModelAndReleaseDate("CodePhone Pro", LocalDate.now());
        assertEquals("CodePhone Pro", actualPhone.getModel());
    }

    // Argument Matchers should be provided for all arguments
    // Argument Matchers can't be used outside p03_stubbing/verification
    @Test
    public void testInvalidUseOfArgumentMatchers() {
        Phone phone = new Phone("1234", "CodePhone Pro", 700, LocalDate.now());
        when(phoneRepository.findPhoneByModelAndReleaseDate(eq("CodePhone Pro"), any())).thenReturn(phone);
        Phone actualPhone = phoneService.getPhoneByModelAndReleaseDate("CodePhone Pro", any());
        assertEquals("CodePhone Pro", actualPhone.getModel());
    }

    // Argument Matchers should be provided for all arguments
    // Argument Matchers cant be used outside p03_stubbing/verification
    // replace LocalDate.now() with an argument matcher like any() if you don't care about the exact date in the test
    // or eq(LocalDate.now()) (if you want to match the exact date).
    @Test
    public void testHalfCorrectedUseOfArgumentMatchers() {
        Phone phone = new Phone("1234", "CodePhone Pro", 700, LocalDate.now());
        when(phoneRepository.findPhoneByModelAndReleaseDate(eq("CodePhone Pro"), any())).thenReturn(phone);
        // eq() cannot be used outside stubbing or verification - LocalDate.now() below cannot be replaced with any()
        Phone actualPhone1 = phoneService.getPhoneByModelAndReleaseDate(eq("CodePhone Pro"), LocalDate.now());
        Phone actualPhone2 = phoneService.getPhoneByModelAndReleaseDate(eq("CodePhone Pro"), any());
        Phone actualPhone3 = phoneService.getPhoneByModelAndReleaseDate(eq("CodePhone Pro"), eq(LocalDate.now()));
        assertEquals("CodePhone Pro", actualPhone1.getModel());
    }


    @Test
    public void testSpecificTypeOfArgumentMatchers() {
        Phone phone = new Phone("1234", "CodePhone Pro", 700, LocalDate.now());
        when(phoneRepository.findPhoneByModelAndPriceAndIsFastCharge(anyString(), anyInt(), anyBoolean())).thenReturn(phone);
        Phone actualPhone = phoneService.getPhoneByModelAndPriceAndIsFastCharge("CodePhone Pro", 700, true);
        assertEquals("CodePhone Pro", actualPhone.getModel());
    }

    @Test
    public void testCollectionTypeArgumentMatchers() {
        List<Phone> phones = new ArrayList<>();
        Phone phone = new Phone("1234", "CodePhone Pro", 700, LocalDate.now());
        phones.add(phone);
        phoneService.addPhones(phones);
        verify(phoneRepository).saveAll(anyList()); // anySet, anyMap, anyCollection
    }

    @Test
    public void testStringTypeArgumentMatchers() {
        Phone phone = new Phone("1234", "CodePhone Pro", 700, LocalDate.now());
        when(phoneRepository.findPhoneByModelAndPriceAndIsFastCharge(contains("CodePhone"), anyInt(), anyBoolean())).thenReturn(phone);
        Phone actualPhone = phoneService.getPhoneByModelAndPriceAndIsFastCharge("CodePhone Standard", 700, true);
        assertEquals("CodePhone Pro", actualPhone.getModel());
    }

}
