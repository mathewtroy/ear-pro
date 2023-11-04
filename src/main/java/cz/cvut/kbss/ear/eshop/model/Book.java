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
    private int BooksReturned;
    @Basic(optional = false)
    @Column(nullable = false)
    private String Author;
    @ManyToMany
    @JoinColumn(nullable = false)
    private List<Category> categories;
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

    public int getBooksReturned() {
        return BooksReturned;
    }

    public void setBooksReturned(int booksReturned) {
        BooksReturned = booksReturned;
    }

    public String getAuthor() {
        return Author;
    }

    public void setAuthor(String author) {
        Author = author;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
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
                ", BooksReturned=" + BooksReturned +
                ", Author='" + Author + '\'' +
                ", categories=" + categories +
                ", Title='" + Title + '\'' +
                ", Status=" + Status +
                '}';
    }
}
