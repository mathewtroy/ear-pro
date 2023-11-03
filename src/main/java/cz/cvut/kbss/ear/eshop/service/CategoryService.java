package cz.cvut.kbss.ear.eshop.service;

import cz.cvut.kbss.ear.eshop.dao.CategoryDao;
import cz.cvut.kbss.ear.eshop.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CategoryService {

    private final CategoryDao categoryDao;

    @Autowired
    public CategoryService(CategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }

    @Transactional(readOnly = true)
    public List<Category> findAll() {
        return categoryDao.findAll();
    }

    @Transactional(readOnly = true)
    public Category find(Integer id) {
        return categoryDao.find(id);
    }

    @Transactional(readOnly = true)
    public List<Category> findByName(String name) {
        return categoryDao.findByName(name);
    }

    @Transactional
    public void persist(Category category) {
        categoryDao.persist(category);
    }

    @Transactional
    public void update(Category category) {
        categoryDao.update(category);
    }

    @Transactional
    public void remove(Category category) {
        categoryDao.remove(category);
    }
}
