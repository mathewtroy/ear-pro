package cz.cvut.kbss.ear.eshop.rest;

import cz.cvut.kbss.ear.eshop.model.Reservation;
import cz.cvut.kbss.ear.eshop.model.Visit;
import cz.cvut.kbss.ear.eshop.service.ReservationService;
import cz.cvut.kbss.ear.eshop.service.VisitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/visit")
public class VisitController {

    private final VisitService visitService;

    @Autowired
    public VisitController(VisitService visitService) {
        this.visitService = visitService;
    }

    @GetMapping
    public ResponseEntity<List<Visit>> getAllReservations() {
        return ResponseEntity.ok(visitService.findAll());
    }
    @PostMapping
    public ResponseEntity<Visit> insertBook(@RequestBody Visit visit) {
        visitService.persist(visit);
        return ResponseEntity.ok(visit);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateReservation(@PathVariable int id, @RequestBody Visit visit) {
        if (visitService.find(id) == null) {
            return ResponseEntity.notFound().build();
        }
        visit.setVisitID(id);
        visitService.update(visit);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReservation(@PathVariable int id) {
        Visit visit = visitService.find(id);
        if (visit == null) {
            return ResponseEntity.notFound().build();
        }
        visitService.remove(visit);
        return ResponseEntity.noContent().build();
    }
}
