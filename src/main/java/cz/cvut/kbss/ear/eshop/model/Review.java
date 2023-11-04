package cz.cvut.kbss.ear.eshop.model;

import javax.persistence.*;
import java.time.LocalDateTime;



@Entity
@NamedQueries({
        @NamedQuery(name = "Review", query = "SELECT rw FROM Review rw")
})
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ReviewID;

    @Basic(optional = false)
    @Column(nullable = false)
    private LocalDateTime DateOfReview;
    @Basic(optional = false)
    @Column(nullable = false)
    private String Comment;

    @Basic(optional = false)
    @Column(nullable = false)
    private int Rate;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Client client;
    @OneToOne
    @JoinColumn(nullable = false)
    private Book BookID;


    public int getReviewID() {
        return ReviewID;
    }

    public void setReviewID(int reviewID) {
        ReviewID = reviewID;
    }

    public LocalDateTime getDateOfReview() {
        return DateOfReview;
    }

    public void setDateOfReview(LocalDateTime dateOfReview) {
        DateOfReview = dateOfReview;
    }

    public String getComment() {
        return Comment;
    }

    public void setComment(String comment) {
        Comment = comment;
    }

    public int getRate() {
        return Rate;
    }

    public void setRate(int rate) {
        Rate = rate;
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
        return "Review{" +
                "ReviewID=" + ReviewID +
                ", DateOfReview=" + DateOfReview +
                ", Comment='" + Comment + '\'' +
                ", Rate=" + Rate +
                ", client=" + client +
                ", BookID=" + BookID +
                '}';
    }
}
