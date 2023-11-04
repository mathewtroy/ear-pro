package cz.cvut.kbss.ear.eshop.model;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "institution_type", discriminatorType = DiscriminatorType.STRING)
@NamedQueries({
        @NamedQuery(name = "EducationalInstitution.findByName", query = "SELECT e FROM EducationalInstitution e WHERE e.name = :name")
})
public abstract class EducationalInstitution {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int InstitutionID;

    @Basic(optional = false)
    @Column(nullable = false)
    private String Address;

    @Basic(optional = false)
    @Column(nullable = false)
    private int EstablishedYear;

    @Basic(optional = false)
    @Column(nullable = false)
    private String name;

    public int getInstitutionID() {
        return InstitutionID;
    }

    public void setInstitutionID(int institutionID) {
        InstitutionID = institutionID;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public int getEstablishedYear() {
        return EstablishedYear;
    }

    public void setEstablishedYear(int establishedYear) {
        EstablishedYear = establishedYear;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public String toString() {
        return "EducationalInstitution{" +
                "InstitutionID=" + InstitutionID +
                ", Address='" + Address + '\'' +
                ", EstablishedYear=" + EstablishedYear +
                ", name='" + name + '\'' +
                '}';
    }
}
