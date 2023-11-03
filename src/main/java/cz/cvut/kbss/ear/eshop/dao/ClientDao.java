package cz.cvut.kbss.ear.eshop.dao;

import cz.cvut.kbss.ear.eshop.model.Client;
import cz.cvut.kbss.ear.eshop.exception.PersistenceException;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

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

}
