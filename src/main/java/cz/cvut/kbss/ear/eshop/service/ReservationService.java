package cz.cvut.kbss.ear.eshop.service;

import cz.cvut.kbss.ear.eshop.dao.ReservationDao;
import cz.cvut.kbss.ear.eshop.model.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReservationService {

    private final ReservationDao reservationDao;

    @Autowired
    public ReservationService(ReservationDao reservationDao) {
        this.reservationDao = reservationDao;
    }

    @Transactional(readOnly = true)
    public List<Reservation> findAll() {
        return reservationDao.findAll();
    }

    @Transactional(readOnly = true)
    public Reservation find(Integer id) {
        return reservationDao.find(id);
    }

    @Transactional(readOnly = true)
    public List<Reservation> findByStatus(boolean status) {
        return reservationDao.findByStatus(status);
    }

    public List<Reservation> findByClientId(int clientId) {
        return reservationDao.findAll()
                .stream()
                .filter(reservation -> reservation.getClient() != null && reservation.getClient().getID() == clientId)
                .collect(Collectors.toList());
    }
    public long countReservationsByBookId(int bookId) {
        return reservationDao.findAll()
                .stream()
                .filter(reservation -> reservation.getBookID() != null && reservation.getBookID().getBookID() == bookId)
                .count();
    }

    @Transactional
    public void persist(Reservation reservation) {
        reservationDao.persist(reservation);
    }

    @Transactional
    public void update(Reservation reservation) {
        reservationDao.update(reservation);
    }

    @Transactional
    public void remove(Reservation reservation) {
        reservationDao.remove(reservation);
    }
}
