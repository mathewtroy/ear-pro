package cz.cvut.kbss.ear.eshop.dao;

import cz.cvut.kbss.ear.eshop.model.Server;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import javax.persistence.*;
import java.util.*;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ServerDaoTest {

    @Mock
    private EntityManager entityManager;

    @InjectMocks
    private ServerDao serverDao;

    @BeforeEach
    void setUp() {
        serverDao.setEntityManager(entityManager);
    }

    @Test
    void findByLocation() {

        String location = "Data Center";
        TypedQuery<Server> query = mock(TypedQuery.class);
        List<Server> expectedServers = Collections.singletonList(new Server());

        when(entityManager.createQuery("SELECT s FROM Server s WHERE s.Location = :location", Server.class)).thenReturn(query);
        when(query.setParameter("location", location)).thenReturn(query);
        when(query.getResultList()).thenReturn(expectedServers);

        List<Server> result = serverDao.findByLocation(location);

        Assertions.assertEquals(expectedServers, result);
    }

}
