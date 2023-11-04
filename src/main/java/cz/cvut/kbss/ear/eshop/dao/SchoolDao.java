package cz.cvut.kbss.ear.eshop.dao;

import cz.cvut.kbss.ear.eshop.model.School;
import org.springframework.stereotype.Repository;
import javax.persistence.*;

@Repository
public class SchoolDao extends BaseDao<School> {

    public SchoolDao() {
        super(School.class);
    }

    public void setEntityManager(EntityManager em) {
        this.em = em;
    }

}
