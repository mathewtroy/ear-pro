package cz.cvut.kbss.ear.eshop.service;

import cz.cvut.kbss.ear.eshop.dao.VisitDao;
import cz.cvut.kbss.ear.eshop.model.Visit;
import org.junit.jupiter.api.*;
import org.mockito.*;
import java.util.*;
import static org.mockito.Mockito.*;

public class VisitServiceTest {

    @Mock
    private VisitDao visitDao;

    @InjectMocks
    private VisitService visitService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindAll() {
        // Arrange
        // Setup the mock behavior of VisitDao
        when(visitDao.findAll()).thenReturn(Collections.emptyList());

        // Act
        List<Visit> visits = visitService.findAll();

        // Assert
        // Assert that the correct method of VisitDao was called
        verify(visitDao, times(1)).findAll();
        // Add more specific assertions based on the expected behavior
        Assertions.assertTrue(visits.isEmpty());
    }

    @Test
    public void testFind() {
        // Arrange
        int id = 1;
        Visit visit = new Visit();
        when(visitDao.find(id)).thenReturn(visit);

        // Act
        Visit result = visitService.find(id);

        // Assert
        verify(visitDao).find(id);
        Assertions.assertEquals(visit, result);
    }

    @Test
    public void testFindByPurpose() {
        // Arrange
        String purpose = "TestPurpose";
        Visit visit = new Visit();
        int wantedNumberOfInvocations = 1;
        int expectedResult = 1;

        visit.setPurpose(purpose);
        when(visitDao.findByPurpose(purpose)).thenReturn(Collections.singletonList(visit));

        // Act
        List<Visit> foundVisits = visitService.findByPurpose(purpose);

        // Assert
        verify(visitDao, times(wantedNumberOfInvocations)).findByPurpose(purpose);
        Assertions.assertEquals(expectedResult, foundVisits.size());
        Assertions.assertEquals(purpose, foundVisits.get(0).getPurpose());
    }

    @Test
    public void persistTest() {
        // Arrange
        Visit visit = new Visit();
        int wantedNumberOfInvocations = 1;

        // Act
        visitService.persist(visit);

        // Assert
        verify(visitDao, times(wantedNumberOfInvocations)).persist(visit);
    }

    @Test
    public void updateTest() {
        // Arrange
        Visit visit = new Visit();
        int wantedNumberOfInvocations = 1;

        // Act
        visitService.update(visit);

        // Assert
        verify(visitDao, times(wantedNumberOfInvocations)).update(visit);
    }

    @Test
    public void removeTest() {
        // Arrange
        Visit visit = new Visit();
        int wantedNumberOfInvocations = 1;

        // Act
        visitService.remove(visit);

        // Assert
        verify(visitDao, times(wantedNumberOfInvocations)).remove(visit);
    }


}
