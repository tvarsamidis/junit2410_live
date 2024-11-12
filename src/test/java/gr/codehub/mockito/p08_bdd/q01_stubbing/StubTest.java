package gr.codehub.mockito.p08_bdd.q01_stubbing;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.BDDAssertions.then;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class StubTest {

    @InjectMocks
    private PhoneService phoneService;

    @Mock
    private PhoneRepository phoneRepository;

    @Test
    public void testStubbingInTraditionalMockitoStyle() {
        Phone phone1 = new Phone("1234", "CodePhone Pro", 600, LocalDate.now());
        Phone phone2 = new Phone("1235", "CodePhone Standard", 500, LocalDate.now());

        List<Phone> newPhones = new ArrayList<>();
        newPhones.add(phone1);
        newPhones.add(phone2);

        when(phoneRepository.findNewPhones(7)).thenReturn(newPhones);

        List<Phone> newPhonesWithAppliedDiscount = phoneService.getNewPhonesWithAppliedDiscount(10, 7);

        assertEquals(2, newPhonesWithAppliedDiscount.size());
        assertEquals(540, newPhonesWithAppliedDiscount.get(0).getPrice());
        assertEquals(450, newPhonesWithAppliedDiscount.get(1).getPrice());
    }

    @Test
    @DisplayName("Given new phones, when getNewPhonesWithDiscount() is called, then returns new phones with discount")
    public void givenNewPhones_whenGetNewPhonesWithDiscount_thenReturnsNewPhonesWithDiscount() {
        // Arrange - Given
        Phone phone1 = new Phone("1234", "CodePhone Pro", 600, LocalDate.now());
        Phone phone2 = new Phone("1235", "CodePhone Standard", 500, LocalDate.now());

        List<Phone> newPhones = new ArrayList<>();
        newPhones.add(phone1);
        newPhones.add(phone2);

        given(phoneRepository.findNewPhones(7)).willReturn(newPhones);

        // Act - When
        List<Phone> newPhonesWithAppliedDiscount = phoneService.getNewPhonesWithAppliedDiscount(10, 7);

        // Assert - Then
//		assertEquals(2, newPhonesWithAppliedDiscount.size());
//		assertEquals(540, newPhonesWithAppliedDiscount.get(0).getPrice());
//		assertEquals(450, newPhonesWithAppliedDiscount.get(1).getPrice());

        // AssertJ - BDDAssertions
        then(newPhonesWithAppliedDiscount).isNotNull();
        then(newPhonesWithAppliedDiscount.size()).isEqualTo(2);
        then(newPhonesWithAppliedDiscount.get(0).getPrice()).isEqualTo(540);
    }

}
