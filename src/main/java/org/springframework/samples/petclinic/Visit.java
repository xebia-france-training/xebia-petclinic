package org.springframework.samples.petclinic;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Index;
import org.joda.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

/**
 * Simple JavaBean domain object representing a visit.
 * 
 * @author Ken Krebs
 */
@Entity
@Table(name = "visits")
public class Visit implements BaseEntity {
    
    @DateTimeFormat(iso=ISO.DATE)
    @Basic
    @Column(name = "visit_date")
    private LocalDate date;

    @Basic
    private String description;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @Index(name = "visits_pet_id")
    private Pet pet;

    /** Creates a new instance of Visit for the current date */
    public Visit() {
        this.date = new LocalDate();
    }

    public LocalDate getDate() {
        return this.date;
    }

    public String getDescription() {
        return this.description;
    }

    @Override
    public Integer getId() {
        return id;
    }

    public Pet getPet() {
        return this.pet;
    }

    @Override
    public boolean isNew() {
        return (this.id == null);
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }

}
