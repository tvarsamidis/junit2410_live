package gr.codehub.mockito.p02_annotations.support;

import mockito.p02_annotations.support.Phone;
import mockito.p02_annotations.support.PhoneRepository;
import mockito.p02_annotations.support.PhoneService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
// @RunWith(MockitoJUnitRunner.class)
public class AnnotationsTest {

    @InjectMocks
    private PhoneService phoneService;

    @Mock
    private PhoneRepository phoneRepository;

    @Test
    public void demoCreateMocksUsingAnnotations() {
        Phone phone1 = new Phone("1234", "CodePhone Pro", 600, LocalDate.now()); // 540
        Phone phone2 = new Phone("1235", "CodePhone Standard", 500, LocalDate.now()); // 450

        List<Phone> newPhones = new ArrayList<>();
        newPhones.add(phone1);
        newPhones.add(phone2);

        when(phoneRepository.findNewPhones(7)).thenReturn(newPhones);

        List<Phone> newPhonesWithAppliedDiscount = phoneService.getNewPhonesWithAppliedDiscount(10, 7);

        assertEquals(2, newPhonesWithAppliedDiscount.size());
        assertEquals(450, newPhonesWithAppliedDiscount.get(1).getPrice());
    }
}
