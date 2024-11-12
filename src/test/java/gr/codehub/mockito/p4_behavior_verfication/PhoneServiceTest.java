package gr.codehub.mockito.verfication;

import gr.codehub.mockito.p04_behavior.verification.Phone;
import gr.codehub.mockito.p04_behavior.verification.PhoneRepository;
import gr.codehub.mockito.p04_behavior.verification.PhoneRequest;
import gr.codehub.mockito.p04_behavior.verification.PhoneService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PhoneServiceTest {

    @InjectMocks
    private PhoneService phoneService;

//	@Mock
//	private PhoneRepository phoneRepository;

    @Spy
    private PhoneRepository phoneRepository;

    @Test
    public void testAddPhone() {
        Phone phone = new Phone(null, "CodePhone Pro", 700, LocalDate.now());
        phoneService.addPhone(phone);
        verify(phoneRepository).save(phone);
    }

    @Test
    public void testSavePhoneWithPhoneRequestWithGreaterPrice() {
        // price of PHoneRequest <= 600 returns with no save
        PhoneRequest phoneRequest = new PhoneRequest("CodePhone Pro", 800, LocalDate.now());
        Phone phone = new Phone(null, "CodePhone Pro", 800, LocalDate.now());
        phoneService.addPhone(phoneRequest);
        // 800, 500, 1 fails because one is saved but not the same
        // 800, 800, 1 succeeds, once is saved and is the same
        // 500, 500, 0 succeeds, none is saved
        // 800, 500, 0 succeeds, one is saved but is 800, not 500
        // 500, 800, 0 succeeds, none is saved, so 800 is not saved
        // 800, 800, 1 succeeds even is phone.Id="-999" and PhoneRequest.Phone.id="REQUEST_ID"
        verify(phoneRepository, times(1)).save(phone);
    }

    @Test
    public void testSavePhoneWithPhoneRequestWithGreaterPrice1() {
        PhoneRequest phoneRequest = new PhoneRequest("CodePhone Pro", 700, LocalDate.now());
        Phone phone = new Phone(null, "CodePhone Pro", 700, LocalDate.now());
        phoneService.addPhone(phoneRequest);
        phoneService.addPhone(phoneRequest);
        verify(phoneRepository, times(2)).save(phone);
    }

    @Test
    public void testSavePhoneWithPhoneRequestWithGreaterPrice2() {
        PhoneRequest phoneRequest = new PhoneRequest("CodePhone Pro", 600, LocalDate.now());
        Phone phone = new Phone(null, "CodePhone Pro", 600, LocalDate.now());
        phoneService.addPhone(phoneRequest);
        verify(phoneRepository, never()).save(phone); // verify(phoneRepository, never()).save(Mockito);
    }

    @Test
    public void testUpdatePrice() {
        phoneService.updatePrice(null, 700);
        verifyNoInteractions(phoneRepository);
    }

    @Test
    public void testUpdatePrice2() {
        Phone phone = new Phone("1234", "CodePhone Pro", 600, LocalDate.now());
        when(phoneRepository.findPhoneById("1234")).thenReturn(phone);
        phoneService.updatePrice("1234", 500); // calls findPhoneById and save
        verify(phoneRepository).findPhoneById("1234");
        // if the update price is the same as the original price, the following will fail
        // because the PhonceService.updatePrice code will not run the phoneRepository.saveMethod
        verify(phoneRepository).save(phone);
        verifyNoMoreInteractions(phoneRepository);
        // if we remove the "verify(phoneRepository).save(phone)" and the prices are different,
        // then the verifyNoMoreInteractions will fail
        // the exception shows the methods that have failed to verify, using this statement:
        // "For your reference, here is the list of all invocations ([?] - means unverified)."
    }

    @Test
    public void testUpdatePrice3() {
        Phone phone = new Phone("1234", "CodePhone Pro", 700, LocalDate.now());
        when(phoneRepository.findPhoneById("1234")).thenReturn(phone);
        phoneService.updatePrice("1234", 600);
        phoneService.updatePrice("1234", 600);

        InOrder inOrder = Mockito.inOrder(phoneRepository);
        inOrder.verify(phoneRepository).findPhoneById("1234");
        inOrder.verify(phoneRepository).save(phone);
        inOrder.verify(phoneRepository).findPhoneById("1234");
        verifyNoMoreInteractions(phoneRepository);
    }

    @Test
    public void testSavePhoneWithPhoneRequestWithGreaterPrice3() {
        PhoneRequest phoneRequest = new PhoneRequest("CodePhone Pro", 700, LocalDate.now());
        Phone phone = new Phone(null, "CodePhone Pro", 700, LocalDate.now());
        phoneService.addPhone(phoneRequest);
        phoneService.addPhone(phoneRequest);
        phoneService.addPhone(phoneRequest);
		verify(phoneRepository, times(3)).save(phone);
//		verify(phoneRepository, atLeast(4)).save(phone);
//		verify(phoneRepository, atMost(2)).save(phone);
//		verify(phoneRepository, atMostOnce()).save(phone);
//        verify(phoneRepository, atLeastOnce()).save(phone);
    }

}
