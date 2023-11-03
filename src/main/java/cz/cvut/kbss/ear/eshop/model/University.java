package cz.cvut.kbss.ear.eshop.model;

import javax.persistence.*;

@Entity
@DiscriminatorValue("University")
public class University extends EducationalInstitution {
    private int UniversityID;
    private String quote;
    private String DeanName;


    public String getQuote() {
        return quote;
    }

    public void setQuote(String quote) {
        this.quote = quote;
    }

    public String getDeanName() {
        return DeanName;
    }

    public void setDeanName(String deanName) {
        DeanName = deanName;
    }


    @Override
    public String toString() {
        return "University{" +
                ", quote='" + quote + '\'' +
                ", DeanName='" + DeanName + '\'' +
                '}';
    }
}
