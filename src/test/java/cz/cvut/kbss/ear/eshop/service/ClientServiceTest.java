package cz.cvut.kbss.ear.eshop.service;

import cz.cvut.kbss.ear.eshop.dao.ClientDao;
import cz.cvut.kbss.ear.eshop.model.Client;
import org.junit.jupiter.api.*;
import org.mockito.*;
import java.util.*;
import static org.mockito.Mockito.*;

public class ClientServiceTest {

    @Mock
    private ClientDao clientDao;

    @InjectMocks
    private ClientService clientService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void findAllTest() {
        // Arrange
        int expectedResult = 2;
        when(clientDao.findAll()).thenReturn(List.of(new Client(), new Client()));

        // Act
        List<Client> result = clientService.findAll();

        // Assert
        verify(clientDao).findAll();
        Assertions.assertEquals(expectedResult, result.size());
    }

    @Test
    public void findTest() {
        // Arrange
        int id = 1;
        Client client = new Client();
        when(clientDao.find(id)).thenReturn(client);

        // Act
        Client result = clientService.find(id);

        // Assert
        verify(clientDao).find(id);
        Assertions.assertEquals(client, result);
    }

    @Test
    public void findByFirstNameAndLastNameTest() {
        // Arrange
        String firstName = "John";
        String lastName = "Doe";
        int expectedResult = 2;

        when(clientDao.findByFirstNameAndLastName(firstName, lastName)).thenReturn(List.of(new Client(), new Client()));

        // Act
        List<Client> result = clientService.findByFirstNameAndLastName(firstName, lastName);

        // Assert
        verify(clientDao).findByFirstNameAndLastName(firstName, lastName);
        Assertions.assertEquals(expectedResult, result.size());
    }

    @Test
    public void updateTest() {
        // Arrange
        Client client = new Client();

        // Act
        clientService.update(client);

        // Assert
        verify(clientDao).update(client);
    }

    @Test
    public void removeTest() {
        // Arrange
        Client client = new Client();

        // Act
        clientService.remove(client);

        // Assert
        verify(clientDao).remove(client);
    }
}
