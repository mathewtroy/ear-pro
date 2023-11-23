package cz.cvut.kbss.ear.eshop.service;

import cz.cvut.kbss.ear.eshop.dao.BookDao;
import cz.cvut.kbss.ear.eshop.dao.CategoryDao;
import cz.cvut.kbss.ear.eshop.model.Book;
import cz.cvut.kbss.ear.eshop.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {
    @PersistenceContext
    protected EntityManager em;
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
    @Transactional
    public void persist(Book book) {
        System.out.println(book);
        CategoryService categoryService = new CategoryService(new CategoryDao());
        Category category = new Category();
        category.setName(book.getCategory().toString());
        System.out.println(category);
        if (book.getCategory() != null && book.getCategory().getCategoryID() > 0) {
            Category managedCategory = em.find(Category.class, book.getCategory().getCategoryID());
            if (managedCategory != null) {
                book.setCategory(managedCategory);
            } else {
                throw new IllegalStateException("Category with ID " + book.getCategory().getCategoryID() + " does not exist.");
            }
        } else {
            throw new IllegalStateException("Book must have a category.");
        }
        bookDao.persist(book);
    }

}