package cz.cvut.kbss.ear.eshop.rest;

import cz.cvut.kbss.ear.eshop.model.*;
import cz.cvut.kbss.ear.eshop.service.UniversityService;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.*;
import java.util.*;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UniversityControllerTest {

    @Mock
    private UniversityService universityService;

    @InjectMocks
    private UniversityController universityController;

    @Test
    void getAllUniversities() {
        List<University> expectedUniversities = Collections.singletonList(new University());
        when(universityService.findAll()).thenReturn(expectedUniversities);

        ResponseEntity<List<University>> response = universityController.getAllUniversities();

        Assertions.assertEquals(expectedUniversities, response.getBody());
    }

    @Test
    void getUniversityById() {
        int id = 1;
        University university = new University();
        when(universityService.find(id)).thenReturn(university);

        ResponseEntity<EducationalInstitution> response = universityController.getUniversityById(id);

        Assertions.assertEquals(university, response.getBody());
    }


    @Test
    void updateReservation() {
        int id = 1;
        University university = new University();
        when(universityService.find(id)).thenReturn(university);

        ResponseEntity<Void> response = universityController.updateReservation(id, university);

        Assertions.assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(universityService).update(university);
    }
}
