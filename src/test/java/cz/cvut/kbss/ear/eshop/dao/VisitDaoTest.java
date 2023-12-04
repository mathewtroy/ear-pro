package cz.cvut.kbss.ear.eshop.dao;

import cz.cvut.kbss.ear.eshop.exception.PersistenceException;
import cz.cvut.kbss.ear.eshop.model.Visit;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.persistence.*;
import java.util.*;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class VisitDaoTest {

    @Mock
    private EntityManager entityManager;

    @InjectMocks
    private VisitDao visitDao;

    @BeforeEach
    void setUp() {
        visitDao.setEntityManager(entityManager);
    }

    @Test
    void findByPurpose() {

        String purpose = "Business";
        TypedQuery<Visit> query = mock(TypedQuery.class);
        List<Visit> expectedVisits = Collections.singletonList(new Visit());

        when(entityManager.createQuery("SELECT v FROM Visit v WHERE v.Purpose = :purpose", Visit.class)).thenReturn(query);
        when(query.setParameter("purpose", purpose)).thenReturn(query);
        when(query.getResultList()).thenReturn(expectedVisits);

        List<Visit> result = visitDao.findByPurpose(purpose);

        Assertions.assertEquals(expectedVisits, result);
    }

    @Test
    void findByPurposeWithRuntimeException() {

        String purpose = "Business";
        when(entityManager.createQuery("SELECT v FROM Visit v WHERE v.Purpose = :purpose", Visit.class))
                .thenThrow(new RuntimeException("Simulating a runtime exception"));

        Assertions.assertThrows(PersistenceException.class, () -> visitDao.findByPurpose(purpose));
    }
}
