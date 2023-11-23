package add;
import cz.cvut.kbss.ear.eshop.dao.UniversityDao;
import cz.cvut.kbss.ear.eshop.service.UniversityService;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class University {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("EARPersistence");
        EntityManager em = emf.createEntityManager();
        UniversityDao universityDao = new UniversityDao();
        universityDao.setEntityManager(em);
        UniversityService universityService = new UniversityService(universityDao);
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        cz.cvut.kbss.ear.eshop.model.University un = new cz.cvut.kbss.ear.eshop.model.University();
        un.setQuote("blabla");
        un.setDeanName("Mike Ross");
        un.setAddress("st. Sokolska 14213");
        un.setEstablishedYear(2023);
        un.setName("The largest and biggest");
        universityDao.persist(un);
        transaction.commit();
        universityDao.findAll();
        em.close();
        emf.close();
    }
}
