package cz.cvut.kbss.ear.eshop.service;

import cz.cvut.kbss.ear.eshop.dao.EducationalInstitutionDao;
import cz.cvut.kbss.ear.eshop.model.EducationalInstitution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EducationalInstitutionService {

    private final EducationalInstitutionDao educationalInstitutionDao;

    @Autowired
    public EducationalInstitutionService(EducationalInstitutionDao educationalInstitutionDao) {
        this.educationalInstitutionDao = educationalInstitutionDao;
    }

    @Transactional(readOnly = true)
    public List<EducationalInstitution> findAll() {
        return educationalInstitutionDao.findAll();
    }

    @Transactional(readOnly = true)
    public EducationalInstitution find(Integer id) {
        return educationalInstitutionDao.find(id);
    }

    @Transactional(readOnly = true)
    public List<EducationalInstitution> findByName(String name) {
        return educationalInstitutionDao.findByName(name);
    }

    @Transactional
    public void persist(EducationalInstitution educationalInstitution) {
        educationalInstitutionDao.persist(educationalInstitution);
    }

    @Transactional
    public void update(EducationalInstitution educationalInstitution) {
        educationalInstitutionDao.update(educationalInstitution);
    }

    @Transactional
    public void remove(EducationalInstitution educationalInstitution) {
        educationalInstitutionDao.remove(educationalInstitution);
    }
}
