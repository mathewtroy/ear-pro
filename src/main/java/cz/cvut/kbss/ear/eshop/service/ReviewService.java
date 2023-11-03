package cz.cvut.kbss.ear.eshop.service;

import cz.cvut.kbss.ear.eshop.dao.ReviewDao;
import cz.cvut.kbss.ear.eshop.model.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ReviewService {

    private final ReviewDao reviewDao;

    @Autowired
    public ReviewService(ReviewDao reviewDao) {
        this.reviewDao = reviewDao;
    }

    @Transactional(readOnly = true)
    public List<Review> findAll() {
        return reviewDao.findAll();
    }

    @Transactional(readOnly = true)
    public Review find(Integer id) {
        return reviewDao.find(id);
    }

    @Transactional(readOnly = true)
    public List<Review> findByRate(int rate) {
        return reviewDao.findByRate(rate);
    }

    @Transactional
    public void persist(Review review) {
        reviewDao.persist(review);
    }

    @Transactional
    public void update(Review review) {
        reviewDao.update(review);
    }

    @Transactional
    public void remove(Review review) {
        reviewDao.remove(review);
    }
}
