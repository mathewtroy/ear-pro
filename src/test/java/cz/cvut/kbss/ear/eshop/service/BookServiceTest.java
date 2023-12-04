package cz.cvut.kbss.ear.eshop.service;

import cz.cvut.kbss.ear.eshop.dao.BookDao;
import cz.cvut.kbss.ear.eshop.model.Book;
import org.junit.jupiter.api.*;
import org.mockito.*;
import java.util.*;
import static org.mockito.Mockito.*;

public class BookServiceTest {

    @Mock
    private BookDao bookDao;

    @InjectMocks
    private BookService bookService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void findAllTest() {
        // Arrange
        int expectedResult = 2;
        when(bookDao.findAll()).thenReturn(List.of(new Book(), new Book()));

        // Act
        List<Book> result = bookService.findAll();

        // Assert
        verify(bookDao).findAll();
        Assertions.assertEquals(expectedResult, result.size());
    }

    @Test
    public void findTest() {
        // Arrange
        int id = 1;
        Book book = new Book();
        when(bookDao.find(id)).thenReturn(book);

        // Act
        Book result = bookService.find(id);

        // Assert
        verify(bookDao).find(id);
        Assertions.assertEquals(book, result);
    }

    @Test
    public void updateTest() {
        // Arrange
        Book book = new Book();

        // Act
        bookService.update(book);

        // Assert
        verify(bookDao).update(book);
    }

    @Test
    public void removeTest() {
        // Arrange
        Book book = new Book();

        // Act
        bookService.remove(book);

        // Assert
        verify(bookDao).remove(book);
    }

    @Test
    public void findByAuthorTest() {
        // Arrange
        String author = "John Doe";
        int expectedResult = 2;
        when(bookDao.findByAuthor(author)).thenReturn(List.of(new Book(), new Book()));

        // Act
        List<Book> result = bookService.findByAuthor(author);

        // Assert
        verify(bookDao).findByAuthor(author);
        Assertions.assertEquals(expectedResult, result.size());
    }

}
