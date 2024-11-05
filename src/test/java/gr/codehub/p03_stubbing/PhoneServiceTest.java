package gr.codehub.p03_stubbing;

import mockito.p03_stubbing.PhoneRepository;
import mockito.p03_stubbing.PhoneService;
import mockito.p03_stubbing.Phone;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PhoneServiceTest {

    @InjectMocks
    private PhoneService phoneService;

    @Mock
    private PhoneRepository phoneRepository;

    @Test
    public void testCalculateTotalCostOfPhones1Fast() {
        List<String> phoneIds = List.of("1234", "1235");

        Phone phone1 = new Phone("1234", "CodePhone Pro", 600, LocalDate.now());
        Phone phone2 = new Phone("1235", "CodePhone Standard", 500, LocalDate.now());

        // fast way
        when(phoneRepository.findPhoneByPhoneId("1234")).thenReturn(phone1);
        when(phoneRepository.findPhoneByPhoneId("1235")).thenReturn(phone2);

        int actualCost = phoneService.calculateTotalCost(phoneIds);
        assertEquals(1100, actualCost);
    }

    @Test
    public void testCalculateTotalCostOfPhones2() {
        List<String> phoneIds = List.of("1234", "1234", "1234", "1234", "1234");

        Phone phone1 = new Phone("1234", "CodePhone Pro", 600, LocalDate.now());
        Phone phone2 = new Phone("1235", "CodePhone Standard", 500, LocalDate.now());

        // ** FOR THE CASE WHERE both phoneIds are "1234" -> List<String> phoneIds = List.of("1234", "1234");
        // if both doReturns are uncommented, then the second one is unused and the test fails with
        // org.mockito.exceptions.misusing.UnnecessaryStubbingException:
        // Unnecessary stubbings detected.
        // Clean & maintainable test code requires zero unnecessary code.
        //doReturn(phone1).when(phoneRepository).findPhoneByPhoneId("1234");
        //doReturn(phone2).when(phoneRepository).findPhoneByPhoneId("1235");

        // careful, these fail with UnfinishedStuddingException!
//        doReturn(phone1).when(phoneRepository.findPhoneByPhoneId("1234"));
//        doReturn(phone2).when(phoneRepository.findPhoneByPhoneId("1235"));

        when(phoneRepository.findPhoneByPhoneId("1234"))
                .thenReturn(phone1)
                .thenReturn(phone2)
                .thenReturn(phone1); // phone 1 for all subsequent calls

        int actualCost = phoneService.calculateTotalCost(phoneIds);
        assertEquals(2900, actualCost);
    }

    @Test
    public void testSavePhone() {
        Phone phone1 = new Phone(null, "CodePhone Pro", 600, LocalDate.now());
        doNothing().when(phoneRepository).save(phone1); // ==
        phoneService.addPhone(phone1);
    }

}
