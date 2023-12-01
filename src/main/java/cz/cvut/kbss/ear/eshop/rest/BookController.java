package cz.cvut.kbss.ear.eshop.rest;

import cz.cvut.kbss.ear.eshop.dao.ClientDao;
import cz.cvut.kbss.ear.eshop.model.Book;
import cz.cvut.kbss.ear.eshop.model.Client;
import cz.cvut.kbss.ear.eshop.model.Reservation;
import cz.cvut.kbss.ear.eshop.model.Role;
import cz.cvut.kbss.ear.eshop.service.BookCountService;
import cz.cvut.kbss.ear.eshop.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {

    private final BookService bookService;
    private final ClientDao clientDao;

    @Autowired
    public BookController(BookService bookService, ClientDao clientDao) {
        this.bookService = bookService;
        this.clientDao = clientDao;
    }


    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks() {
        return ResponseEntity.ok(bookService.findAll());
    }

    @PostMapping
    public ResponseEntity<Book> insertBook(@RequestBody Book book) {
        if (checkAccess() == false){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        bookService.persist(book);
        return ResponseEntity.ok(book);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateBook(@PathVariable int id, @RequestBody Book book) {
        if (checkAccess() == false){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        if (bookService.find(id) == null) {
            return ResponseEntity.notFound().build();
        }
        book.setBookID(id);
        bookService.update(book);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable int id) {
        if (checkAccess() == false){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        Book book = bookService.find(id);
        if (book == null) {
            return ResponseEntity.notFound().build();
        }
        bookService.remove(book);
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
