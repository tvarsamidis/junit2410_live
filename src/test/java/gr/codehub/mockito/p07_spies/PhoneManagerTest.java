package gr.codehub.mockito.p07_spies;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
public class PhoneManagerTest {

    @InjectMocks
    private PhoneManager phoneManager;

    // When program complete, try the @Mock annotation
    // It will return a discounted price of 0, because it will find no real object to run the discount on
    // @Mock
    @Spy
    private PhoneService phoneService;

    @Test
    public void testMockitoSpy() {
        // Here we only check that things work
        // For the actual test we will use annotations
        PhoneService phoneService1 = Mockito.spy(PhoneService.class);
        PhoneManager phoneManager1 = new PhoneManager(phoneService1);
    }

    @Test
    public void testMockitoSpy1() {
        // We need to mock findPhone because it is communicating to database or not implemented
        // We need to call getAppliedDiscount to calculate the discounted price
        Phone phone = new Phone("1234", "CodePhone Pro", 600, LocalDate.now());
        doReturn(phone).when(phoneService).findPhone("1234");
//		when(phoneService.findPhone("1234")).thenReturn(phone);
        int newPrice = phoneManager.applyDiscountOnPhone("1234", 10);
        assertEquals(540, newPrice);
    }

}
