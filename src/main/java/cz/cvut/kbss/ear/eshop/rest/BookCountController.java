package cz.cvut.kbss.ear.eshop.rest;

import cz.cvut.kbss.ear.eshop.dao.ClientDao;
import cz.cvut.kbss.ear.eshop.model.Book;
import cz.cvut.kbss.ear.eshop.model.BookCount;
import cz.cvut.kbss.ear.eshop.model.Client;
import cz.cvut.kbss.ear.eshop.model.Role;
import cz.cvut.kbss.ear.eshop.service.BookCountService;
import cz.cvut.kbss.ear.eshop.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bookcount")
public class BookCountController {
    private final ClientDao clientDao;
    private final BookCountService bookCountService;

    @Autowired
    public BookCountController(BookCountService bookCountService, ClientDao clientDao) {
        this.bookCountService = bookCountService;
        this.clientDao = clientDao;
    }
    @GetMapping
    public ResponseEntity<List<BookCount>> getAllBooks() {
        return ResponseEntity.ok(bookCountService.findAll());
    }

    @PostMapping
    public ResponseEntity<BookCount> insertBookCount(@RequestBody BookCount bookCount) {
        if (checkAccess() == false){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        bookCountService.persist(bookCount);
        return ResponseEntity.ok(bookCount);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateReservation(@PathVariable int id, @RequestBody BookCount bookCount) {
        if (checkAccess() == false){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        if (bookCountService.find(id) == null) {
            return ResponseEntity.notFound().build();
        }
        bookCount.setBookCountID(id);
        bookCountService.update(bookCount);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReservation(@PathVariable int id) {
        if (checkAccess() == false){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        BookCount bookCount = bookCountService.find(id);
        if (bookCount == null) {
            return ResponseEntity.notFound().build();
        }
        bookCountService.remove(bookCount);
        return ResponseEntity.noContent().build();
    }
    public boolean checkAccess(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = authentication.getName();
        Client client = clientDao.findByUserName(currentUserName);
        Role adminRole = Role.ADMIN;
        if (client.getRole() != adminRole) {
            return false;
        }
        return true;
    }

}
