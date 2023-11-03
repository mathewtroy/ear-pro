package cz.cvut.kbss.ear.eshop;

import cz.cvut.kbss.ear.eshop.dao.ClientDao;
import cz.cvut.kbss.ear.eshop.model.Client;
import cz.cvut.kbss.ear.eshop.service.ClientService;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("EARPersistence");
        EntityManager em = emf.createEntityManager();
        ClientDao clientDao = new ClientDao();
        clientDao.setEntityManager(em);
        ClientService clientService = new ClientService(clientDao);
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        Client client = new Client();
//        client.setAddress("sds");
//        client.setEmail("as");
//        client.setPassword("dawhd");
//        client.setFirstName("Mike");
//        client.setID(1);
//        client.setLastName("Kononenko");
//        clientService.remove(client);
        transaction.commit();

        em.close();
        emf.close();
    }
}
