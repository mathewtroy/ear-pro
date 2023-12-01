package cz.cvut.kbss.ear.eshop.rest;

import cz.cvut.kbss.ear.eshop.dao.ClientDao;
import cz.cvut.kbss.ear.eshop.model.Book;
import cz.cvut.kbss.ear.eshop.model.Client;
import cz.cvut.kbss.ear.eshop.model.Reservation;
import cz.cvut.kbss.ear.eshop.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/reservation")
public class ReservationController {
    private final ReservationService reservationService;
    private final ClientDao clientDao;

    @Autowired
    public ReservationController(ReservationService reservationService, ClientDao clientDao) {
        this.reservationService = reservationService;
        this.clientDao = clientDao;
    }

    @GetMapping
    public ResponseEntity<List<Reservation>> getAllReservations() {
        return ResponseEntity.ok(reservationService.findAll());
    }
    @PostMapping
    public ResponseEntity<Reservation> createReservation(@RequestBody Reservation reservation) {
        reservationService.persist(reservation);
        return ResponseEntity.ok(reservation);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Reservation> getReservationById(@PathVariable int id) {
        Reservation reservation = reservationService.find(id);
        if (reservation == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(reservation);
    }
    @GetMapping("/my/{id}")
    public ResponseEntity<List<Reservation>> getReservationByUserId(@PathVariable int id) {
        List<Reservation> allReservations = reservationService.findAll();
        List<Reservation> myReservations = new ArrayList<>();

        if (allReservations == null) {
            return ResponseEntity.notFound().build();
        }

        for (Reservation reservation : allReservations) {
            if (reservation.getClient().getID() == id) {
                myReservations.add(reservation);
            }
        }
        return ResponseEntity.ok(myReservations);
    }

    @GetMapping("/my")
    public ResponseEntity<List<Reservation>> getMyReservations() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = authentication.getName();
        Client client = clientDao.findByUserName(currentUserName);
        if (client == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        List<Reservation> myReservations = reservationService.findByClientId(client.getID());
        return ResponseEntity.ok(myReservations);
    }

    @GetMapping("/expiresoon")
    public ResponseEntity<List<Reservation>> expiresoon() {
        List<Reservation> reservations = reservationService.findAll();
        List<Reservation> myReservations = new ArrayList<>();

        if (reservations == null) {
            return ResponseEntity.notFound().build();
        }
        LocalDateTime specificDate = LocalDate.of(2023, Month.DECEMBER, 1).atStartOfDay();

        LocalDateTime oneWeekFromNow = specificDate.plus(1, ChronoUnit.WEEKS);

        for (Reservation reservation : reservations) {
            LocalDateTime expirationDate = reservation.getDateOfReservation();
            if (expirationDate != null &&
                    (expirationDate.isAfter(specificDate) || expirationDate.isEqual(specificDate)) &&
                    expirationDate.isBefore(oneWeekFromNow)) {
                myReservations.add(reservation);
            }
        }
        return ResponseEntity.ok(myReservations);
    }
    @GetMapping("/count/book/{bookId}")
    public ResponseEntity<Long> getCountOfReservationsByBookId(@PathVariable int bookId) {
        long count = reservationService.countReservationsByBookId(bookId);
        return ResponseEntity.ok(count);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateReservation(@PathVariable int id, @RequestBody Reservation reservation) {
        if (reservationService.find(id) == null) {
            return ResponseEntity.notFound().build();
        }
        reservation.setReservationID(id);
        reservationService.update(reservation);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReservation(@PathVariable int id) {
        Reservation reservation = reservationService.find(id);
        if (reservation == null) {
            return ResponseEntity.notFound().build();
        }
        reservationService.remove(reservation);
        return ResponseEntity.noContent().build();
    }
}
