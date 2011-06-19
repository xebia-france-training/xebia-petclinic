package org.springframework.samples.petclinic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Index;
import org.hibernate.annotations.TypeDef;
import org.joda.time.LocalDate;
import org.springframework.beans.support.MutableSortDefinition;
import org.springframework.beans.support.PropertyComparator;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

/**
 * Simple JavaBean business object representing a pet.
 * 
 * @author Ken Krebs
 * @author Juergen Hoeller
 * @author Sam Brannen
 */
@Entity()
@Table(name = "pets")
public class Pet implements NamedEntity {

    @DateTimeFormat(iso=ISO.DATE)
    @Basic
    @Column(name = "birth_date")
    private LocalDate birthDate;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Basic
    @Index(name = "pets_name")
    private String name;

    @ManyToOne(fetch = FetchType.EAGER)
    private Owner owner;

    @ManyToOne(fetch = FetchType.EAGER)
    private PetType type;

    @OneToMany(mappedBy = "pet", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Visit> visits;

    public void addVisit(Visit visit) {
        getVisitsInternal().add(visit);
        visit.setPet(this);
    }

    public LocalDate getBirthDate() {
        return this.birthDate;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public String getName() {
        return this.name;
    }

    public Owner getOwner() {
        return this.owner;
    }

    public PetType getType() {
        return this.type;
    }

    public List<Visit> getVisits() {
        List<Visit> sortedVisits = new ArrayList<Visit>(getVisitsInternal());
        PropertyComparator.sort(sortedVisits, new MutableSortDefinition("date", false, false));
        return Collections.unmodifiableList(sortedVisits);
    }

    protected Set<Visit> getVisitsInternal() {
        if (this.visits == null) {
            this.visits = new HashSet<Visit>();
        }
        return this.visits;
    }

    @Override
    public boolean isNew() {
        return (this.id == null);
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    protected void setOwner(Owner owner) {
        this.owner = owner;
    }

    public void setType(PetType type) {
        this.type = type;
    }

    protected void setVisitsInternal(Set<Visit> visits) {
        this.visits = visits;
    }

    @Override
    public String toString() {
        return this.getName();
    }

}
