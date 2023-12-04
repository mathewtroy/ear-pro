package cz.cvut.kbss.ear.eshop.dao;

import cz.cvut.kbss.ear.eshop.exception.PersistenceException;
import cz.cvut.kbss.ear.eshop.model.Reservation;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import javax.persistence.*;
import java.util.*;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ReservationDaoTest {

    @Mock
    private EntityManager entityManager;

    @InjectMocks
    private ReservationDao reservationDao;

    @BeforeEach
    void setUp() {
        reservationDao.setEntityManager(entityManager);
    }

    @Test
    void findByStatus() {

        boolean reservationStatus = true;
        TypedQuery<Reservation> query = mock(TypedQuery.class);
        List<Reservation> expectedReservations = Collections.singletonList(new Reservation());

        when(entityManager.createQuery("SELECT r FROM Reservation r WHERE r.Status = :status", Reservation.class)).thenReturn(query);
        when(query.setParameter("status", reservationStatus)).thenReturn(query);
        when(query.getResultList()).thenReturn(expectedReservations);

        List<Reservation> result = reservationDao.findByStatus(reservationStatus);

        Assertions.assertEquals(expectedReservations, result);
    }

    @Test
    void findByStatusWithRuntimeException() {

        boolean reservationStatus = true;
        when(entityManager.createQuery("SELECT r FROM Reservation r WHERE r.Status = :status", Reservation.class))
                .thenThrow(new RuntimeException("Simulating a runtime exception"));

        Assertions.assertThrows(PersistenceException.class, () -> reservationDao.findByStatus(reservationStatus));
    }
}
