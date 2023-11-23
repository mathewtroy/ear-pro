package cz.cvut.kbss.ear.eshop.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.*;
import java.time.Duration;

@Entity
@NamedQueries({
        @NamedQuery(name = "Visit.findByName", query = "SELECT v FROM Visit v")
})
public class Visit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int VisitID;

    @Basic(optional = false)
    @Column(nullable = false)
    private boolean BooksReturned;
    @OneToOne
    @JoinColumn(nullable = false)
    private Book BookID;
    @Column(name = "dateofvisit", nullable = false)
    private LocalDateTime DateOfVisit;
    @Column(name = "duration", nullable = false)
    private String duration;

    private String Purpose;
    @ManyToOne
    @JoinColumn(nullable = false)
    private Client client;

    public int getVisitID() {
        return VisitID;
    }

    public void setVisitID(int visitID) {
        VisitID = visitID;
    }

    public boolean isBooksReturned() {
        return BooksReturned;
    }

    public void setBooksReturned(boolean booksReturned) {
        BooksReturned = booksReturned;
    }

    public Book getBookID() {
        return BookID;
    }

    public void setBookID(Book bookID) {
        BookID = bookID;
    }

    public LocalDateTime getDateOfVisit() {
        return DateOfVisit;
    }

    public void setDateOfVisit(LocalDateTime dateOfVisit) {
        DateOfVisit = dateOfVisit;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getPurpose() {
        return Purpose;
    }

    public void setPurpose(String purpose) {
        Purpose = purpose;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }


    @Override
    public String toString() {
        return "Visit{" +
                "VisitID=" + VisitID +
                ", BooksReturned=" + BooksReturned +
                ", BooksTaken=" + BookID +
                ", DateOfVisit=" + DateOfVisit +
                ", Duration=" + duration +
                ", Purpose='" + Purpose + '\'' +
                ", client=" + client +
                '}';
    }
}
