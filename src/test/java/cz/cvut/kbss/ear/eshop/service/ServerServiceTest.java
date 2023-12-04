package cz.cvut.kbss.ear.eshop.service;

import cz.cvut.kbss.ear.eshop.dao.ServerDao;
import cz.cvut.kbss.ear.eshop.model.Server;
import org.junit.jupiter.api.*;
import org.mockito.*;
import java.util.*;
import java.util.logging.*;
import static org.mockito.Mockito.*;

public class ServerServiceTest {

    @Mock
    private ServerDao serverDao;

    @InjectMocks
    private ServerService serverService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void updateServerStatusTest() {
        // Arrange
        Server server = new Server();
        boolean newStatus = true;
        int wantedNumberOfInvocations = 1;

        // Act
        serverService.updateServerStatus(server, newStatus);

        // Assert
        verify(serverDao, times(wantedNumberOfInvocations)).update(server);
        Assertions.assertEquals(newStatus, server.isStatus());
    }


    @Test
    public void findServersByLocation() {
        // Arrange
        String location = "TestLocation";
        Server server = new Server();
        int wantedNumberOfInvocations = 1;
        int expectedResult = 1;

        server.setLocation(location);
        when(serverDao.findByLocation(location)).thenReturn(Collections.singletonList(server));

        // Act
        List<Server> foundServers = serverService.findServersByLocation(location);

        // Assert
        verify(serverDao, times(wantedNumberOfInvocations)).findByLocation(location);
        Assertions.assertEquals(expectedResult, foundServers.size());
        Assertions.assertEquals(location, foundServers.get(0).getLocation());
    }


    @Test
    public void performMaintenanceTest() {
        // Arrange
        Server server = new Server();
        String maintenanceDescription = "Test Maintenance Description";

        // Act
        serverService.performMaintenance(server, maintenanceDescription);

        // Assert

        // Assert that server status is set to true after maintenance
        Assertions.assertTrue(server.isStatus());

        // Assert that last maintenance date is not null
        Assertions.assertNotNull(server.getLastMaintenanceDate());
    }

}






