package cz.cvut.kbss.ear.eshop.dao;

import cz.cvut.kbss.ear.eshop.model.Reservation;
import cz.cvut.kbss.ear.eshop.exception.PersistenceException;
import org.springframework.stereotype.Repository;
import javax.persistence.*;
import java.util.*;

@Repository
public class ReservationDao extends BaseDao<Reservation> {

    public ReservationDao() {
        super(Reservation.class);
    }

    public void setEntityManager(EntityManager em) {
        this.em = em;
    }

    public List<Reservation> findByStatus(boolean status) {
        try {
            TypedQuery<Reservation> query = em.createQuery("SELECT r FROM Reservation r WHERE r.Status = :status", Reservation.class);
            query.setParameter("status", status);
            return query.getResultList();
        } catch (RuntimeException e) {
            throw new PersistenceException(e);
        }
    }

}
