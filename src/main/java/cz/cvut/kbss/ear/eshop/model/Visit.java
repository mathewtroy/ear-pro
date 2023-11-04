package cz.cvut.kbss.ear.eshop.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.*;


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
    @OneToMany
    private List<Book> BooksTaken;

    @Column(columnDefinition = "TIMESTAMP")
    private LocalDateTime DateOfVisit;

    @Column(columnDefinition = "TIMESTAMP")
    private LocalDateTime Duration;

    @Column(columnDefinition = "TIMESTAMP")
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

    public List<Book> getBooksTaken() {
        return BooksTaken;
    }

    public void setBooksTaken(List<Book> booksTaken) {
        BooksTaken = booksTaken;
    }

    public LocalDateTime getDateOfVisit() {
        return DateOfVisit;
    }

    public void setDateOfVisit(LocalDateTime dateOfVisit) {
        DateOfVisit = dateOfVisit;
    }

    public LocalDateTime getDuration() {
        return Duration;
    }

    public void setDuration(LocalDateTime duration) {
        Duration = duration;
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
                ", BooksTaken=" + BooksTaken +
                ", DateOfVisit=" + DateOfVisit +
                ", Duration=" + Duration +
                ", Purpose='" + Purpose + '\'' +
                ", client=" + client +
                '}';
    }
}
