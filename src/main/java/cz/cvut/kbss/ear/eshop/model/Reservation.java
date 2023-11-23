package cz.cvut.kbss.ear.eshop.model;

import javax.persistence.*;
import java.time.LocalDateTime;



@Entity
@NamedQueries({
        @NamedQuery(name = "Reservation.findAll", query = "SELECT r FROM Reservation r")
})
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ReservationID;

    @Column(name = "dateofreservation", nullable = false)
    private LocalDateTime dateOfReservation;
    @Column(name = "expirationDate", nullable = false)
    private LocalDateTime expirationDate;
    @Basic(optional = false)
    @Column(nullable = false)
    private boolean Status;
    @ManyToOne
    @JoinColumn(nullable = false)
    private Client client;
    @OneToOne
    @JoinColumn(nullable = false)
    private Book BookID;


    public int getReservationID() {
        return ReservationID;
    }

    public void setReservationID(int reservationID) {
        ReservationID = reservationID;
    }

    public LocalDateTime getDateOfReservation() {
        return dateOfReservation;
    }

    public void setDateOfReservation(LocalDateTime dateOfReservation) {
        this.dateOfReservation = dateOfReservation;
    }


    public LocalDateTime getExpirationDate() {
        return this.expirationDate;
    }
    public void setExpirationDate(LocalDateTime expirationDate) {
        this.expirationDate = expirationDate;
    }

    public boolean isStatus() {
        return Status;
    }

    public void setStatus(boolean status) {
        Status = status;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Book getBookID() {
        return BookID;
    }

    public void setBookID(Book bookID) {
        BookID = bookID;
    }


    @Override
    public String toString() {
        return "Reservation{" +
                "ReservationID=" + ReservationID +
                ", DateOfReservation=" + dateOfReservation +
                ", ExpirationDate=" + expirationDate +
                ", Status=" + Status +
                ", client=" + client +
                ", BookID=" + BookID +
                '}';
    }
}
