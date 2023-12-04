package cz.cvut.kbss.ear.eshop.rest;

import cz.cvut.kbss.ear.eshop.dao.ClientDao;
import cz.cvut.kbss.ear.eshop.model.*;
import cz.cvut.kbss.ear.eshop.service.BookService;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.*;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BookControllerTest {

    @Mock
    private BookService bookService;

    @Mock
    private ClientDao clientDao;

    @InjectMocks
    private BookController bookController;

    @Test
    void getAllBooks() {
        List<Book> expectedBooks = Collections.singletonList(new Book());
        when(bookService.findAll()).thenReturn(expectedBooks);

        ResponseEntity<List<Book>> response = bookController.getAllBooks();

        Assertions.assertEquals(expectedBooks, response.getBody());
    }

    @Test
    void checkAccess() {
        String currentUserName = "admin";
        Authentication authentication = mock(Authentication.class);
        when(authentication.getName()).thenReturn(currentUserName);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        Client adminClient = new Client();
        adminClient.setRole(Role.ADMIN);
        when(clientDao.findByUserName(currentUserName)).thenReturn(adminClient);

        Assertions.assertTrue(bookController.checkAccess());
    }

    @Test
    void checkAccessNonAdmin() {
        String currentUserName = "user";
        Authentication authentication = mock(Authentication.class);
        when(authentication.getName()).thenReturn(currentUserName);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        Client regularClient = new Client();
        regularClient.setRole(Role.USER);
        when(clientDao.findByUserName(currentUserName)).thenReturn(regularClient);

        Assertions.assertFalse(bookController.checkAccess());
    }
}
