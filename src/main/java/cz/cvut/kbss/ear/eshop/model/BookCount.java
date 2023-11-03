package cz.cvut.kbss.ear.eshop.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Entity
@NamedQueries({
        @NamedQuery(name = "BookCount", query = "SELECT bc FROM BookCount bc")
})
public class BookCount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int BookCountID;

    @Basic(optional = false)
    @Column(nullable = false)
    private int AvailableCount;

    @Column(columnDefinition = "TIMESTAMP")
    private LocalDateTime DateUpdated;
    @OneToOne
    @JoinColumn(nullable = false)
    private Book BookID;
    @Basic(optional = false)
    @Column(nullable = false)
    private int TotalCount;

    public int getBookCountID() {
        return BookCountID;
    }

    public void setBookCountID(int bookCountID) {
        BookCountID = bookCountID;
    }

    public int getAvailableCount() {
        return AvailableCount;
    }

    public void setAvailableCount(int availableCount) {
        AvailableCount = availableCount;
    }

    public LocalDateTime getDateUpdated() {
        return DateUpdated;
    }

    public void setDateUpdated(LocalDateTime dateUpdated) {
        DateUpdated = dateUpdated;
    }

    public Book getBookID() {
        return BookID;
    }

    public void setBookID(Book bookID) {
        BookID = bookID;
    }

    public int getTotalCount() {
        return TotalCount;
    }

    public void setTotalCount(int totalCount) {
        TotalCount = totalCount;
    }

    @Override
    public String toString() {
        return "BookCount{" +
                "BookCountID=" + BookCountID +
                ", AvailableCount=" + AvailableCount +
                ", DateUpdated=" + DateUpdated +
                ", BookID=" + BookID +
                ", TotalCount=" + TotalCount +
                '}';
    }
}
