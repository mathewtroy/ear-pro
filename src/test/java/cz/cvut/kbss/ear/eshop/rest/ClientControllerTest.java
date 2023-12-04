package cz.cvut.kbss.ear.eshop.rest;

import cz.cvut.kbss.ear.eshop.model.Client;
import cz.cvut.kbss.ear.eshop.service.ClientService;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import java.util.*;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ClientControllerTest {

    @Mock
    private ClientService clientService;

    @InjectMocks
    private ClientController clientController;

    @Test
    void getAllClients() {
        List<Client> expectedClients = Collections.singletonList(new Client());
        when(clientService.findAll()).thenReturn(expectedClients);

        ResponseEntity<List<Client>> response = clientController.getAllClients();

        Assertions.assertEquals(expectedClients, response.getBody());
    }

    @Test
    void updateReservation() {
        int clientId = 1;
        Client client = new Client();
        when(clientService.find(clientId)).thenReturn(client);

        ResponseEntity<Void> response = clientController.updateReservation(clientId, client);

        Assertions.assertEquals(204, response.getStatusCodeValue());
        verify(clientService, times(1)).update(client);
    }

    @Test
    void deleteReservation() {
        int clientId = 1;
        Client client = new Client();
        when(clientService.find(clientId)).thenReturn(client);

        ResponseEntity<Void> response = clientController.deleteReservation(clientId);

        Assertions.assertEquals(204, response.getStatusCodeValue());
        verify(clientService, times(1)).remove(client);
    }
}
