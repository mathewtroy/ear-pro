package cz.cvut.kbss.ear.eshop.dao;

import cz.cvut.kbss.ear.eshop.model.Review;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import javax.persistence.*;
import java.util.*;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ReviewDaoTest {

    @Mock
    private EntityManager entityManager;

    @InjectMocks
    private ReviewDao reviewDao;

    @BeforeEach
    void setUp() {
        reviewDao.setEntityManager(entityManager);
    }

    @Test
    void findByRate() {

        int reviewRate = 5;
        TypedQuery<Review> query = mock(TypedQuery.class);
        List<Review> expectedReviews = Collections.singletonList(new Review());

        when(entityManager.createQuery("SELECT rw FROM Review rw WHERE rw.Rate = :rate", Review.class)).thenReturn(query);
        when(query.setParameter("rate", reviewRate)).thenReturn(query);
        when(query.getResultList()).thenReturn(expectedReviews);

        List<Review> result = reviewDao.findByRate(reviewRate);

        Assertions.assertEquals(expectedReviews, result);
    }

}
