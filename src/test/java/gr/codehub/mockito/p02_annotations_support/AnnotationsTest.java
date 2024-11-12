package gr.codehub.mockito.support;

import gr.codehub.mockito.p02_annotations.support.Phone;
import gr.codehub.mockito.p02_annotations.support.PhoneRepository;
import gr.codehub.mockito.p02_annotations.support.PhoneService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

// Needed in JUnit 4, but not in JUnit 5
// In JUnit 5, this is replaced by @ExtendWith(MockitoExtension.class)
//@RunWith(MockitoJUnitRunner.class)
@ExtendWith(MockitoExtension.class)
public class AnnotationsTest {

//    The Rule is JUnit 4 only, and it is an alternative to MockitoJUnitRunner.
//    In JUnit 5, this too is replaced by @ExtendWith(MockitoExtension.class)
//    @Rule
//    public MockitoRule mockitoRule = MockitoJUnit.rule();  // it must be public, else all fails

    @InjectMocks
    private PhoneService phoneService; // wherever you see phoneService, inject @Mock phoneRepository if required

    @Mock
    private PhoneRepository phoneRepository;

//    The Before annotation this was available in JUnit 4, but does not work in JUnit 5
//    @Before
//    public void before() {
//        MockitoAnnotations.initMocks(this);
//    }


//    The BeforeEach annotation works in JUnit 5, but initMocks is deprecated and is not needed
//    @BeforeEach
//    public void beforeEach() {
//        MockitoAnnotations.initMocks(this);
//    }

    @Test
    public void demoCreateMocksUsingAnnotations() {
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

}
