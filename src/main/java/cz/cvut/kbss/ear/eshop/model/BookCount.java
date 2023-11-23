package cz.cvut.kbss.ear.eshop.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
@NamedQueries({
        @NamedQuery(name = "BookCount", query = "SELECT bc FROM BookCount bc")
})
public class BookCount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int BookCountID;

    @JsonProperty("availablecount")
    @Basic(optional = false)
    @Column(nullable = false)
    private int AvailableCount;

    @JsonProperty("dateupdated")
    @Column(columnDefinition = "TIMESTAMP")
    private LocalDateTime DateUpdated;

    @JsonProperty("bookid")
    @OneToOne
    @JoinColumn(nullable = false)
    private Book BookID;

    @JsonProperty("totalcount")
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
