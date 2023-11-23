package cz.cvut.kbss.ear.eshop.service;

import cz.cvut.kbss.ear.eshop.dao.UniversityDao;
import cz.cvut.kbss.ear.eshop.model.EducationalInstitution;
import cz.cvut.kbss.ear.eshop.model.University;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UniversityService {

    private final UniversityDao universityDao;

    @Autowired
    public UniversityService(UniversityDao universityDao) {
        this.universityDao = universityDao;
    }

    @Transactional(readOnly = true)
    public List<University> findAll() {
        return universityDao.findAll();
    }

    @Transactional(readOnly = true)
    public EducationalInstitution find(Integer id) {
        return universityDao.find(id);
    }


    @Transactional
    public void persist(University university) {
        universityDao.persist(university);
    }

    @Transactional
    public void update(University university) {
        universityDao.update(university);
    }

    @Transactional
    public void remove(University university) {
        universityDao.remove(university);
    }
}
