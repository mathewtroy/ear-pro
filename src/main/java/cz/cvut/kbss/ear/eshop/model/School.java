package cz.cvut.kbss.ear.eshop.model;

import javax.persistence.*;

@Entity
@DiscriminatorValue("School")
public class School extends EducationalInstitution {
    private int SchoolID;
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
        return "School{" +
                ", quote='" + quote + '\'' +
                ", DeanName='" + DeanName + '\'' +
                '}';
    }
}
