package cz.cvut.kbss.ear.eshop.service;

import cz.cvut.kbss.ear.eshop.dao.VisitDao;
import cz.cvut.kbss.ear.eshop.model.Visit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class VisitService {

    private final VisitDao visitDao;

    @Autowired
    public VisitService(VisitDao visitDao) {
        this.visitDao = visitDao;
    }

    @Transactional(readOnly = true)
    public List<Visit> findAll() {
        return visitDao.findAll();
    }

    @Transactional(readOnly = true)
    public Visit find(Integer id) {
        return visitDao.find(id);
    }

    @Transactional(readOnly = true)
    public List<Visit> findByPurpose(String purpose) {
        return visitDao.findByPurpose(purpose);
    }

    @Transactional
    public void persist(Visit visit) {
        visitDao.persist(visit);
    }

    @Transactional
    public void update(Visit visit) {
        visitDao.update(visit);
    }

    @Transactional
    public void remove(Visit visit) {
        visitDao.remove(visit);
    }
}
