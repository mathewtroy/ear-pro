package cz.cvut.kbss.ear.eshop.dao;

import cz.cvut.kbss.ear.eshop.model.Visit;
import cz.cvut.kbss.ear.eshop.exception.PersistenceException;
import org.springframework.stereotype.Repository;
import javax.persistence.*;
import java.util.*;

@Repository
public class VisitDao extends BaseDao<Visit> {

    public VisitDao() {
        super(Visit.class);
    }

    public void setEntityManager(EntityManager em) {
        this.em = em;
    }

    public List<Visit> findByPurpose(String purpose) {
        try {
            TypedQuery<Visit> query = em.createQuery("SELECT v FROM Visit v WHERE v.Purpose = :purpose", Visit.class);
            query.setParameter("purpose", purpose);
            return query.getResultList();
        } catch (RuntimeException e) {
            throw new PersistenceException(e);
        }
    }

}
