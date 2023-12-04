package cz.cvut.kbss.ear.eshop.dao;

import cz.cvut.kbss.ear.eshop.exception.PersistenceException;
import cz.cvut.kbss.ear.eshop.model.BookCount;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import javax.persistence.*;
import java.util.*;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BookCountDaoTest {

    @Mock
    private EntityManager entityManager;

    @InjectMocks
    private BookCountDao bookCountDao;

    @BeforeEach
    void setUp() {
        bookCountDao.setEntityManager(entityManager);
    }

    @Test
    void findByTotalCount() {

        int totalCount = 100;
        TypedQuery<BookCount> query = mock(TypedQuery.class);
        List<BookCount> expectedBookCounts = Collections.singletonList(new BookCount());
        when(entityManager.createQuery("SELECT bc FROM BookCount bc WHERE bc.TotalCount = :totalCount", BookCount.class)).thenReturn(query);
        when(query.setParameter("totalCount", totalCount)).thenReturn(query);
        when(query.getResultList()).thenReturn(expectedBookCounts);

        List<BookCount> result = bookCountDao.findByTotalCount(totalCount);

        Assertions.assertEquals(expectedBookCounts, result);
    }

    @Test
    void findByTotalCountWithRuntimeException() {

        int totalCount = 100;
        when(entityManager.createQuery("SELECT bc FROM BookCount bc WHERE bc.TotalCount = :totalCount", BookCount.class))
                .thenThrow(new RuntimeException("Simulating a runtime exception"));

        Assertions.assertThrows(PersistenceException.class, () -> bookCountDao.findByTotalCount(totalCount));
    }
}
