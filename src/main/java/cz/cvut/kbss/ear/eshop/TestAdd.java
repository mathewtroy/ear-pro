//package cz.cvut.kbss.ear.eshop;
//import cz.cvut.kbss.ear.eshop.dao.ClientDao;
//import cz.cvut.kbss.ear.eshop.dao.UniversityDao;
//import cz.cvut.kbss.ear.eshop.model.Client;
//import cz.cvut.kbss.ear.eshop.model.EducationalInstitution;
//import cz.cvut.kbss.ear.eshop.model.Role;
//import cz.cvut.kbss.ear.eshop.model.University;
//import cz.cvut.kbss.ear.eshop.service.ClientService;
//import cz.cvut.kbss.ear.eshop.service.UniversityService;
//
//import javax.persistence.EntityManager;
//import javax.persistence.EntityManagerFactory;
//import javax.persistence.EntityTransaction;
//import javax.persistence.Persistence;
//import java.time.LocalDateTime;
//
//public class TestAdd {
//
//    public static void main(String[] args) {
//        EntityManagerFactory emf = Persistence.createEntityManagerFactory("EARPersistence");
//        EntityManager em = emf.createEntityManager();
//        ClientDao clientDao = new ClientDao();
//        clientDao.setEntityManager(em);
//
//        EntityTransaction transaction = em.getTransaction();
//        transaction.begin();
//        Client cl = new Client();
//        cl.setFirstName("John");
//        cl.setLastName("Doe");
//        cl.setAddress("123 Main St");
//        cl.setDateOfBirth(LocalDateTime.now());
//        cl.setEmail("john.doe@example.com");
//        cl.setPhoneNumber(1234567890L);
//        cl.setUserName("johndoe");
//        cl.setPassword("password123");
//        cl.setRole(Role.USER);
//        cl.setInstitutionID(1);
//        clientDao.persist(cl);
//
//        transaction.commit();
//        em.close();
//        emf.close();
//    }
//}
