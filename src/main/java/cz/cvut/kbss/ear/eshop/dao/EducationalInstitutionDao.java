package cz.cvut.kbss.ear.eshop.dao;

import cz.cvut.kbss.ear.eshop.model.EducationalInstitution;
import cz.cvut.kbss.ear.eshop.exception.PersistenceException;
import org.springframework.stereotype.Repository;
import javax.persistence.*;
import java.util.*;

@Repository
public class EducationalInstitutionDao extends BaseDao<EducationalInstitution> {

    public EducationalInstitutionDao() {
        super(EducationalInstitution.class);
    }

    public void setEntityManager(EntityManager em) {
        this.em = em;
    }

    public List<EducationalInstitution> findByName(String name) {
        try {
            TypedQuery<EducationalInstitution> query = em.createNamedQuery("EducationalInstitution.findByName", EducationalInstitution.class);
            query.setParameter("name", name);
            return query.getResultList();
        } catch (RuntimeException e) {
            throw new PersistenceException(e);
        }
    }

}
