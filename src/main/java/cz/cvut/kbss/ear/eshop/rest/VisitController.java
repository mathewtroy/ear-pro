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

    @GetMapping("/getall")
    public ResponseEntity<List<Visit>> getAllReservations() {
        return ResponseEntity.ok(visitService.findAll());
    }
    @PostMapping("/insert")
    public ResponseEntity<Visit> insertBook(@RequestBody Visit visit) {
        visitService.persist(visit);
        return ResponseEntity.ok(visit);
    }
}
