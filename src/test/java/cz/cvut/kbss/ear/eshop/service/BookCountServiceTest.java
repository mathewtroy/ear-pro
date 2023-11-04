package cz.cvut.kbss.ear.eshop.service;

import cz.cvut.kbss.ear.eshop.dao.BookCountDao;
import cz.cvut.kbss.ear.eshop.model.BookCount;
import org.junit.jupiter.api.*;
import org.mockito.*;
import java.util.*;

import static org.mockito.Mockito.*;

public class BookCountServiceTest {

    @Mock
    private BookCountDao bookCountDao;

    @InjectMocks
    private BookCountService bookCountService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void findAllTest() {
        // Arrange
        BookCount bookCount = new BookCount();
        int expectedResult = 1;

        when(bookCountDao.findAll()).thenReturn(Collections.singletonList(bookCount));

        // Act
        List<BookCount> result = bookCountService.findAll();

        // Assert
        verify(bookCountDao).findAll();
        Assertions.assertEquals(expectedResult, result.size());
        Assertions.assertEquals(bookCount, result.get(0));
    }

    @Test
    public void findTest() {
        // Arrange
        int id = 1;
        BookCount bookCount = new BookCount();
        when(bookCountDao.find(id)).thenReturn(bookCount);

        // Act
        BookCount result = bookCountService.find(id);

        // Assert
        verify(bookCountDao).find(id);
        Assertions.assertEquals(bookCount, result);
    }

    @Test
    public void findByTotalCountTest() {
        // Arrange
        int totalCount = 10;
        int expectedResult = 1;
        BookCount bookCount = new BookCount();
        when(bookCountDao.findByTotalCount(totalCount)).thenReturn(Collections.singletonList(bookCount));

        // Act
        List<BookCount> result = bookCountService.findByTotalCount(totalCount);

        // Assert
        verify(bookCountDao).findByTotalCount(totalCount);
        Assertions.assertEquals(expectedResult, result.size());
        Assertions.assertEquals(bookCount, result.get(0));
    }

    @Test
    public void persistTest() {
        // Arrange
        BookCount bookCount = new BookCount();

        // Act
        bookCountService.persist(bookCount);

        // Assert
        verify(bookCountDao).persist(bookCount);
    }

    @Test
    public void updateTest() {
        // Arrange
        BookCount bookCount = new BookCount();

        // Act
        bookCountService.update(bookCount);

        // Assert
        verify(bookCountDao).update(bookCount);
    }

    @Test
    public void removeTest() {
        // Arrange
        BookCount bookCount = new BookCount();

        // Act
        bookCountService.remove(bookCount);

        // Assert
        verify(bookCountDao).remove(bookCount);
    }

}
