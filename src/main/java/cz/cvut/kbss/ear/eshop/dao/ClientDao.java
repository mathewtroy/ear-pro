package cz.cvut.kbss.ear.eshop.dao;

import cz.cvut.kbss.ear.eshop.model.Client;
import cz.cvut.kbss.ear.eshop.exception.PersistenceException;
import org.springframework.stereotype.Repository;
import javax.persistence.*;
import java.util.*;

@Repository
public class ClientDao extends BaseDao<Client> {

    public ClientDao() {
        super(Client.class);
    }

    public void setEntityManager(EntityManager em) {
        this.em = em;
    }

    public List<Client> findByFirstNameAndLastName(String firstName, String lastName) {
        try {
            TypedQuery<Client> query = em.createNamedQuery("Client.findByName", Client.class);
            query.setParameter("firstName", firstName);
            query.setParameter("lastName", lastName);
            return query.getResultList();
        } catch (RuntimeException e) {
            throw new PersistenceException(e);
        }
    }
    public Client findByUserName(String userName) {
        try {
            TypedQuery<Client> query = em.createQuery("SELECT c FROM Client c WHERE c.UserName = :userName", Client.class);
            query.setParameter("userName", userName);
            List<Client> clients = query.getResultList();
            if (clients.isEmpty()) {
                return null;
            }
            return clients.get(0);
        } catch (RuntimeException e) {
            throw new PersistenceException(e);
        }
    }

}
