package cz.cvut.kbss.ear.eshop.dao;

import cz.cvut.kbss.ear.eshop.model.Server;
import cz.cvut.kbss.ear.eshop.exception.PersistenceException;
import org.springframework.stereotype.Repository;
import javax.persistence.*;
import java.util.*;

@Repository
public class ServerDao extends BaseDao<Server> {

    public ServerDao() {
        super(Server.class);
    }

    public void setEntityManager(EntityManager em) {
        this.em = em;
    }

    public List<Server> findByLocation(String location) {
        try {
            TypedQuery<Server> query = em.createQuery("SELECT s FROM Server s WHERE s.Location = :location", Server.class);
            query.setParameter("location", location);
            return query.getResultList();
        } catch (RuntimeException e) {
            throw new PersistenceException(e);
        }
    }

}
