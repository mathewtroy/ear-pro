package cz.cvut.kbss.ear.eshop.rest;

import cz.cvut.kbss.ear.eshop.dao.ClientDao;
import cz.cvut.kbss.ear.eshop.model.Client;
import cz.cvut.kbss.ear.eshop.model.Reservation;
import cz.cvut.kbss.ear.eshop.service.ReservationService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ReservationControllerTest {

    @Mock
    private ReservationService reservationService;

    @Mock
    private ClientDao clientDao;

    @Mock
    private Authentication authentication;

    @InjectMocks
    private ReservationController reservationController;

    @Test
    void getAllReservations() {
        List<Reservation> expectedReservations = Collections.singletonList(new Reservation());
        when(reservationService.findAll()).thenReturn(expectedReservations);

        ResponseEntity<List<Reservation>> response = reservationController.getAllReservations();

        Assertions.assertEquals(expectedReservations, response.getBody());
    }

    @Test
    void getReservationById() {
        int id = 1;
        Reservation expectedReservation = new Reservation();
        when(reservationService.find(id)).thenReturn(expectedReservation);

        ResponseEntity<Reservation> response = reservationController.getReservationById(id);

        Assertions.assertEquals(expectedReservation, response.getBody());
    }

    @Test
    void expiresoon() {
        List<Reservation> reservations = Collections.singletonList(new Reservation());
        List<Reservation> expectedReservations = new ArrayList<>();

        LocalDateTime specificDate = LocalDate.of(2023, Month.DECEMBER, 1).atStartOfDay();
        LocalDateTime oneWeekFromNow = specificDate.plus(1, ChronoUnit.WEEKS);

        when(reservationService.findAll()).thenReturn(reservations);

        ResponseEntity<List<Reservation>> response = reservationController.expiresoon();

        Assertions.assertEquals(expectedReservations, response.getBody());
    }

    @Test
    void getCountOfReservationsByBookId() {
        int bookId = 1;
        long expectedCount = 5;
        when(reservationService.countReservationsByBookId(bookId)).thenReturn(expectedCount);

        ResponseEntity<Long> response = reservationController.getCountOfReservationsByBookId(bookId);

        Assertions.assertEquals(expectedCount, response.getBody());
    }

    @Test
    void updateReservation() {
        int id = 1;
        Reservation reservation = new Reservation();
        when(reservationService.find(id)).thenReturn(reservation);

        ResponseEntity<Void> response = reservationController.updateReservation(id, reservation);

        Assertions.assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(reservationService).update(reservation);
    }

    @Test
    void deleteReservation() {
        int id = 1;
        Reservation reservation = new Reservation();
        when(reservationService.find(id)).thenReturn(reservation);

        ResponseEntity<Void> response = reservationController.deleteReservation(id);

        Assertions.assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(reservationService).remove(reservation);
    }
}
