package cz.cvut.kbss.ear.eshop.rest;

import cz.cvut.kbss.ear.eshop.model.Visit;
import cz.cvut.kbss.ear.eshop.service.VisitService;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.*;
import java.util.*;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class VisitControllerTest {

    @Mock
    private VisitService visitService;

    @InjectMocks
    private VisitController visitController;

    @Test
    void getAllReservations() {
        List<Visit> expectedVisits = Collections.singletonList(new Visit());
        when(visitService.findAll()).thenReturn(expectedVisits);

        ResponseEntity<List<Visit>> response = visitController.getAllReservations();

        Assertions.assertEquals(expectedVisits, response.getBody());
    }

    @Test
    void updateReservation() {
        int id = 1;
        Visit visit = new Visit();
        when(visitService.find(id)).thenReturn(visit);

        ResponseEntity<Void> response = visitController.updateReservation(id, visit);

        Assertions.assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(visitService).update(visit);
    }

    @Test
    void deleteReservation() {
        int id = 1;
        Visit visit = new Visit();
        when(visitService.find(id)).thenReturn(visit);

        ResponseEntity<Void> response = visitController.deleteReservation(id);

        Assertions.assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(visitService).remove(visit);
    }
}
