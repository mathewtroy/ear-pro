package cz.cvut.kbss.ear.eshop.service;

import cz.cvut.kbss.ear.eshop.dao.ReservationDao;
import cz.cvut.kbss.ear.eshop.model.Reservation;
import org.junit.jupiter.api.*;
import org.mockito.*;
import java.util.*;
import static org.mockito.Mockito.*;

public class ReservationServiceTest {

    @Mock
    private ReservationDao reservationDao;

    @InjectMocks
    private ReservationService reservationService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    public void findAllTest() {
        // Arrange
        int expectedResult = 2;
        when(reservationDao.findAll()).thenReturn(List.of(new Reservation(), new Reservation()));

        // Act
        List<Reservation> result = reservationService.findAll();

        // Assert
        verify(reservationDao).findAll();
        Assertions.assertEquals(expectedResult, result.size());
    }

    @Test
    public void findTest() {
        // Arrange
        int id = 1;
        Reservation reservation = new Reservation();
        when(reservationDao.find(id)).thenReturn(reservation);

        // Act
        Reservation result = reservationService.find(id);

        // Assert
        verify(reservationDao).find(id);
        Assertions.assertEquals(reservation, result);
    }

    @Test
    public void findByStatusTest() {
        // Arrange
        boolean status = true;
        int expectedResult = 2;
        when(reservationDao.findByStatus(status)).thenReturn(List.of(new Reservation(), new Reservation()));

        // Act
        List<Reservation> result = reservationService.findByStatus(status);

        // Assert
        verify(reservationDao).findByStatus(status);
        Assertions.assertEquals(expectedResult, result.size());
    }

    @Test
    public void persistTest() {
        // Arrange
        Reservation reservation = new Reservation();

        // Act
        reservationService.persist(reservation);

        // Assert
        verify(reservationDao).persist(reservation);
    }

    @Test
    public void updateTest() {
        // Arrange
        Reservation reservation = new Reservation();

        // Act
        reservationService.update(reservation);

        // Assert
        verify(reservationDao).update(reservation);
    }

    @Test
    public void removeTest() {
        // Arrange
        Reservation reservation = new Reservation();

        // Act
        reservationService.remove(reservation);

        // Assert
        verify(reservationDao).remove(reservation);
    }
}
