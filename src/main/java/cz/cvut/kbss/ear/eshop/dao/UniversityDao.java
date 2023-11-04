package cz.cvut.kbss.ear.eshop.dao;

import cz.cvut.kbss.ear.eshop.model.University;
import org.springframework.stereotype.Repository;
import javax.persistence.*;

@Repository
public class UniversityDao extends BaseDao<University> {

    public UniversityDao() {
        super(University.class);
    }

    public void setEntityManager(EntityManager em) {
        this.em = em;
    }

}
