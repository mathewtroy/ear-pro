package cz.cvut.kbss.ear.eshop.rest;

import cz.cvut.kbss.ear.eshop.model.Book;
import cz.cvut.kbss.ear.eshop.model.Reservation;
import cz.cvut.kbss.ear.eshop.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/reservation")
public class ReservationController {

    private final ReservationService reservationService;

    @Autowired
    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
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
    public ResponseEntity<List<Reservation>> getMyReservations(@PathVariable int id) {
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
