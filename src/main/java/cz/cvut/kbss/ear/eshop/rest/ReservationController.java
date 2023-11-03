package cz.cvut.kbss.ear.eshop.rest;

import cz.cvut.kbss.ear.eshop.model.Reservation;
import cz.cvut.kbss.ear.eshop.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reservations")
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

    @GetMapping("/{id}")
    public ResponseEntity<Reservation> getReservationById(@PathVariable int id) {
        Reservation reservation = reservationService.find(id);
        if (reservation == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(reservation);
    }

    @PostMapping
    public ResponseEntity<Void> createReservation(@RequestBody Reservation reservation) {
        reservationService.persist(reservation);
        return ResponseEntity.status(HttpStatus.CREATED).build();
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
