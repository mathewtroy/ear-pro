package cz.cvut.kbss.ear.eshop.model;

import javax.persistence.*;
import java.util.*;

@Entity
@NamedQueries({
        @NamedQuery(name = "Book", query = "SELECT b FROM Book b")
})
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int BookID;

    @Basic(optional = false)
    @Column(nullable = false)
    private String Author;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "categoryID")
    private Category category;


    @Basic(optional = false)
    @Column(nullable = false)
    private String Title;
    @Basic(optional = false)
    @Column(nullable = false)
    private boolean Status;

    public int getBookID() {
        return BookID;
    }

    public void setBookID(int bookID) {
        BookID = bookID;
    }


    public String getAuthor() {
        return Author;
    }

    public void setAuthor(String author) {
        Author = author;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }


    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public boolean isStatus() {
        return Status;
    }

    public void setStatus(boolean status) {
        Status = status;
    }


    @Override
    public String toString() {
        return "Book{" +
                "BookID=" + BookID +
                ", Author='" + Author + '\'' +
                ", categories=" + category +
                ", Title='" + Title + '\'' +
                ", Status=" + Status +
                '}';
    }
}
