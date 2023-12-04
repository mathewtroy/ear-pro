package cz.cvut.kbss.ear.eshop.rest;

import cz.cvut.kbss.ear.eshop.model.Server;
import cz.cvut.kbss.ear.eshop.service.ServerService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ServerControllerTest {

    @Mock
    private ServerService serverService;

    @InjectMocks
    private ServerController serverController;

    @Test
    void getAllBooks() {
        List<Server> expectedServers = Collections.singletonList(new Server());
        when(serverService.findAll()).thenReturn(expectedServers);

        ResponseEntity<List<Server>> response = serverController.getAllBooks();

        Assertions.assertEquals(expectedServers, response.getBody());
    }

}
