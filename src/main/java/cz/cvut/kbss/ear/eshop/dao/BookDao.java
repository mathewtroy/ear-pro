package cz.cvut.kbss.ear.eshop.dao;

import cz.cvut.kbss.ear.eshop.model.Book;
import cz.cvut.kbss.ear.eshop.exception.PersistenceException;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class BookDao extends BaseDao<Book> {

    public BookDao() {
        super(Book.class);
    }

    public void setEntityManager(EntityManager em) {
        this.em = em;
    }

    public List<Book> findByAuthor(String author) {
        try {
            TypedQuery<Book> query = em.createQuery("SELECT b FROM Book b WHERE b.Author = :author", Book.class);
            query.setParameter("author", author);
            return query.getResultList();
        } catch (RuntimeException e) {
            throw new PersistenceException(e);
        }
    }

}

