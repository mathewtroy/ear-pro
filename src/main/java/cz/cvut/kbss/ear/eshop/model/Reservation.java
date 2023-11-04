package cz.cvut.kbss.ear.eshop.model;

import javax.persistence.*;
import java.time.LocalDateTime;



@Entity
@NamedQueries({
        @NamedQuery(name = "Reservation", query = "SELECT r FROM Reservation r")
})
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ReservationID;

    @Basic(optional = false)
    @Column(nullable = false)
    private LocalDateTime DateOfReservation;
    @Basic(optional = false)
    @Column(nullable = false)
    private LocalDateTime ExpirationDate;
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
        return DateOfReservation;
    }

    public void setDateOfReservation(LocalDateTime dateOfReservation) {
        DateOfReservation = dateOfReservation;
    }

    public LocalDateTime getExpirationDate() {
        return ExpirationDate;
    }

    public void setExpirationDate(LocalDateTime expirationDate) {
        ExpirationDate = expirationDate;
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
                ", DateOfReservation=" + DateOfReservation +
                ", ExpirationDate=" + ExpirationDate +
                ", Status=" + Status +
                ", client=" + client +
                ", BookID=" + BookID +
                '}';
    }
}
