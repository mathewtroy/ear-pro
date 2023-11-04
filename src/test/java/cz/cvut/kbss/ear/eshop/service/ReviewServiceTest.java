package cz.cvut.kbss.ear.eshop.service;

import cz.cvut.kbss.ear.eshop.dao.ReviewDao;
import cz.cvut.kbss.ear.eshop.model.Review;
import org.junit.jupiter.api.*;
import org.mockito.*;
import java.util.*;
import static org.mockito.Mockito.*;

public class ReviewServiceTest {

    @Mock
    private ReviewDao reviewDao;

    @InjectMocks
    private ReviewService reviewService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void findAllTest() {
        // Arrange
        int expectedResult = 2;
        when(reviewDao.findAll()).thenReturn(List.of(new Review(), new Review()));

        // Act
        List<Review> result = reviewService.findAll();

        // Assert
        verify(reviewDao).findAll();
        Assertions.assertEquals(expectedResult, result.size());

    }

    @Test
    public void findTest() {
        // Arrange
        int id = 1;
        Review review = new Review();
        when(reviewDao.find(id)).thenReturn(review);

        // Act
        Review result = reviewService.find(id);

        // Assert
        verify(reviewDao).find(id);
        Assertions.assertEquals(review, result);
    }

    @Test
    public void findByRateTest() {
        // Arrange
        int rate = 5;
        int expectedResult = 2;

        when(reviewDao.findByRate(rate)).thenReturn(List.of(new Review(), new Review()));

        // Act
        List<Review> result = reviewService.findByRate(rate);

        // Assert
        verify(reviewDao).findByRate(rate);
        Assertions.assertEquals(expectedResult, result.size());
    }

    @Test
    public void persistTest() {
        // Arrange
        Review review = new Review();

        // Act
        reviewService.persist(review);

        // Assert
        verify(reviewDao).persist(review);
    }

    @Test
    public void updateTest() {
        // Arrange
        Review review = new Review();

        // Act
        reviewService.update(review);

        // Assert
        verify(reviewDao).update(review);
    }

    @Test
    public void removeTest() {
        // Arrange
        Review review = new Review();

        // Act
        reviewService.remove(review);

        // Assert
        verify(reviewDao).remove(review);
    }

}
