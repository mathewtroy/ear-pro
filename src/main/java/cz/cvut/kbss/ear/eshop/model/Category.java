package cz.cvut.kbss.ear.eshop.model;

import javax.persistence.*;

/**
 * Represents product category.
 * <p>
 * There can be categories currently without products, but every product must belong at least to one category.
 */
@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int CategoryID;
    @Basic(optional = false)
    @Column(nullable = false)
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Category{" +
                "name='" + name + '\'' +
                "}";
    }
}
