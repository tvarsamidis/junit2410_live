package gr.codehub.mockito.p08_bdd.q02_behavior_verification;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PhoneServiceTest {

    @InjectMocks
    private PhoneService phoneService;

    @Mock
    private PhoneRepository phoneRepository;

//	@Spy
//	private PhoneRepository phoneRepository;

    @Test
    public void testUpdatePrice2() {
        Phone phone = new Phone("1234", "CodePhone Pro", 600, LocalDate.now());
        when(phoneRepository.findPhoneById("1234")).thenReturn(phone);
        phoneService.updatePrice("1234", 600);
        verify(phoneRepository).findPhoneById("1234");
        verify(phoneRepository).save(phone);
        verifyNoMoreInteractions(phoneRepository);
    }

    @Test
    @DisplayName("Given a phone, when updatePrice() is called with new price, then phone price is updated")
    public void givenAPhone_whenUpdatePriceIsCalledWithNewPrice_thenPhonePriceIsUpdated() {
        // Given - Arrange
        Phone phone = new Phone("1234", "CodePhone Pro", 600, LocalDate.now());
        given(phoneRepository.findPhoneById("1234")).willReturn(phone);

        // When - Act
        phoneService.updatePrice("1234", 600);

        // Then - Assert/Verify
        then(phoneRepository).should().save(phone);
    }

}
