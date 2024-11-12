package gr.codehub.mockito.p06_argument_captor;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class PhoneServiceTest {

    @InjectMocks
    private PhoneService phoneService;

    @Mock
    private PhoneRepository phoneRepository;

    @Captor
    private ArgumentCaptor<Phone> phoneArgumentCaptor;


    @Test
    public void testSavePhone1() {
        PhoneRequest phoneRequest = new PhoneRequest("CodePhone Pro", 600, LocalDate.now());
        ArgumentCaptor<Phone> phoneArgumentCaptor1 = ArgumentCaptor.forClass(Phone.class);
        phoneService.addPhone(phoneRequest);
        verify(phoneRepository).save(phoneArgumentCaptor1.capture());
        Phone phone = phoneArgumentCaptor1.getValue();
        assertEquals("CodePhone Pro", phone.getModel());
    }

    // if we had to pass other arguments too to save, then it would be:
    // ArgumentCaptor<Phone> phoneCaptor = ArgumentCaptor.forClass(Phone.class);
    // verify(phoneRepository).save(phoneCaptor.capture(), eq("anotherArgument"), anyInt());

    @Test
    public void testSavePhone2() {
        PhoneRequest phoneRequest = new PhoneRequest("CodePhone Pro", 600, LocalDate.now());
        phoneService.addPhone(phoneRequest);
        verify(phoneRepository).save(phoneArgumentCaptor.capture());
        Phone phone = phoneArgumentCaptor.getValue();
        assertEquals("CodePhone Pro", phone.getModel());
    }
}
