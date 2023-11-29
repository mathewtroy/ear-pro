package cz.cvut.kbss.ear.eshop.rest;

import cz.cvut.kbss.ear.eshop.model.Book;
import cz.cvut.kbss.ear.eshop.model.BookCount;
import cz.cvut.kbss.ear.eshop.service.BookCountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bookcount")
public class BookCountController {

    private final BookCountService bookCountService;

    @Autowired
    public BookCountController(BookCountService bookCountService) {
        this.bookCountService = bookCountService;
    }
    @GetMapping
    public ResponseEntity<List<BookCount>> getAllBooks() {
        return ResponseEntity.ok(bookCountService.findAll());
    }

    @PostMapping
    public ResponseEntity<BookCount> insertBookCount(@RequestBody BookCount bookCount) {
        bookCountService.persist(bookCount);
        return ResponseEntity.ok(bookCount);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateReservation(@PathVariable int id, @RequestBody BookCount bookCount) {
        if (bookCountService.find(id) == null) {
            return ResponseEntity.notFound().build();
        }
        bookCount.setBookCountID(id);
        bookCountService.update(bookCount);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReservation(@PathVariable int id) {
        BookCount bookCount = bookCountService.find(id);
        if (bookCount == null) {
            return ResponseEntity.notFound().build();
        }
        bookCountService.remove(bookCount);
        return ResponseEntity.noContent().build();
    }

}
