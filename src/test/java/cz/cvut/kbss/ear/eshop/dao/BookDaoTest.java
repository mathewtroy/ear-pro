package cz.cvut.kbss.ear.eshop.dao;

import cz.cvut.kbss.ear.eshop.exception.PersistenceException;
import cz.cvut.kbss.ear.eshop.model.Book;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import javax.persistence.*;
import java.util.*;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BookDaoTest {

    @Mock
    private EntityManager entityManager;

    @InjectMocks
    private BookDao bookDao;

    @BeforeEach
    void setUp() {
        bookDao.setEntityManager(entityManager);
    }

    @Test
    void findByAuthorTest() {

        String author = "John Doe";
        TypedQuery<Book> query = mock(TypedQuery.class);
        List<Book> expectedBooks = Collections.singletonList(new Book());
        when(entityManager.createQuery("SELECT b FROM Book b WHERE b.Author = :author", Book.class)).thenReturn(query);
        when(query.setParameter("author", author)).thenReturn(query);
        when(query.getResultList()).thenReturn(expectedBooks);

        List<Book> result = bookDao.findByAuthor(author);

        Assertions.assertEquals(expectedBooks, result);
    }

    @Test
    void findByAuthorWithRuntimeExceptionTest() {

        String author = "John Doe";
        when(entityManager.createQuery("SELECT b FROM Book b WHERE b.Author = :author", Book.class))
                .thenThrow(new RuntimeException("Simulating a runtime exception"));

        Assertions.assertThrows(PersistenceException.class, () -> bookDao.findByAuthor(author));
    }
}
