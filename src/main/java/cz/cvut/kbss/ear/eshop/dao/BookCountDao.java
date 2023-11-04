package cz.cvut.kbss.ear.eshop.dao;

import cz.cvut.kbss.ear.eshop.model.BookCount;
import cz.cvut.kbss.ear.eshop.exception.PersistenceException;
import org.springframework.stereotype.Repository;
import javax.persistence.*;
import java.util.*;

@Repository
public class BookCountDao extends BaseDao<BookCount> {

    public BookCountDao() {
        super(BookCount.class);
    }

    public void setEntityManager(EntityManager em) {
        this.em = em;
    }

    public List<BookCount> findByTotalCount(int totalCount) {
        try {
            TypedQuery<BookCount> query = em.createQuery("SELECT bc FROM BookCount bc WHERE bc.TotalCount = :totalCount", BookCount.class);
            query.setParameter("totalCount", totalCount);
            return query.getResultList();
        } catch (RuntimeException e) {
            throw new PersistenceException(e);
        }
    }

}
