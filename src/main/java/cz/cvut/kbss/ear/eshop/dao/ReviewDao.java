package cz.cvut.kbss.ear.eshop.dao;

import cz.cvut.kbss.ear.eshop.model.Review;
import cz.cvut.kbss.ear.eshop.exception.PersistenceException;
import org.springframework.stereotype.Repository;
import javax.persistence.*;
import java.util.*;

@Repository
public class ReviewDao extends BaseDao<Review> {

    public ReviewDao() {
        super(Review.class);
    }

    public void setEntityManager(EntityManager em) {
        this.em = em;
    }

    public List<Review> findByRate(int rate) {
        try {
            TypedQuery<Review> query = em.createQuery("SELECT rw FROM Review rw WHERE rw.Rate = :rate", Review.class);
            query.setParameter("rate", rate);
            return query.getResultList();
        } catch (RuntimeException e) {
            throw new PersistenceException(e);
        }
    }

}
