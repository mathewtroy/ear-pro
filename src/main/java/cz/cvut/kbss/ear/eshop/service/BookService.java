package cz.cvut.kbss.ear.eshop.service;

import cz.cvut.kbss.ear.eshop.dao.BookDao;
import cz.cvut.kbss.ear.eshop.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BookService {

    private final BookDao bookDao;

    @Autowired
    public BookService(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    @Transactional(readOnly = true)
    public List<Book> findAll() {
        return bookDao.findAll();
    }

    @Transactional(readOnly = true)
    public Book find(Integer id) {
        return bookDao.find(id);
    }

    @Transactional
    public void persist(Book book) {
        bookDao.persist(book);
    }

    @Transactional
    public void update(Book book) {
        bookDao.update(book);
    }

    @Transactional
    public void remove(Book book) {
        bookDao.remove(book);
    }

    @Transactional(readOnly = true)
    public List<Book> findByAuthor(String author) {
        return bookDao.findByAuthor(author);
    }
}