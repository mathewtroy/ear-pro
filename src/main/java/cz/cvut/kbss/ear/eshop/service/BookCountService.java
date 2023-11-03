package cz.cvut.kbss.ear.eshop.service;

import cz.cvut.kbss.ear.eshop.dao.BookCountDao;
import cz.cvut.kbss.ear.eshop.model.BookCount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BookCountService {

    private final BookCountDao bookCountDao;

    @Autowired
    public BookCountService(BookCountDao bookCountDao) {
        this.bookCountDao = bookCountDao;
    }

    @Transactional(readOnly = true)
    public List<BookCount> findAll() {
        return bookCountDao.findAll();
    }

    @Transactional(readOnly = true)
    public BookCount find(Integer id) {
        return bookCountDao.find(id);
    }

    @Transactional(readOnly = true)
    public List<BookCount> findByTotalCount(int totalCount) {
        return bookCountDao.findByTotalCount(totalCount);
    }

    @Transactional
    public void persist(BookCount bookCount) {
        bookCountDao.persist(bookCount);
    }

    @Transactional
    public void update(BookCount bookCount) {
        bookCountDao.update(bookCount);
    }

    @Transactional
    public void remove(BookCount bookCount) {
        bookCountDao.remove(bookCount);
    }
}
