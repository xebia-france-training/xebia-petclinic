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
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Index;
import org.springframework.beans.support.MutableSortDefinition;
import org.springframework.beans.support.PropertyComparator;
import org.springframework.core.style.ToStringCreator;

/**
 * Simple JavaBean domain object representing an owner.
 * 
 * @author Ken Krebs
 * @author Juergen Hoeller
 * @author Sam Brannen
 */
@Entity
@Table(name = "owners")
public class Owner implements Person {

    @Basic
    private String address;

    @Basic
    private String city;

    @Basic
    @Column(name = "first_name")
    private String firstName;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Basic
    @Column(name = "last_name")
    @Index(name = "owners_last_name")
    private String lastName;

    @OneToMany(targetEntity = Pet.class, mappedBy = "owner", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Pet> pets;

    @Basic
    private String telephone;

    public void addPet(Pet pet) {
        getPetsInternal().add(pet);
        pet.setOwner(this);
    }

    public String getAddress() {
        return this.address;
    }

    public String getCity() {
        return this.city;
    }

    @Override
    public String getFirstName() {
        return this.firstName;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public String getLastName() {
        return this.lastName;
    }

    /**
     * Return the Pet with the given name, or null if none found for this Owner.
     * 
     * @param name
     *            to test
     * @return true if pet name is already in use
     */
    public Pet getPet(String name) {
        return getPet(name, false);
    }

    /**
     * Return the Pet with the given name, or null if none found for this Owner.
     * 
     * @param name
     *            to test
     * @return true if pet name is already in use
     */
    public Pet getPet(String name, boolean ignoreNew) {
        name = name.toLowerCase();
        for (Pet pet : getPetsInternal()) {
            if (!ignoreNew || !pet.isNew()) {
                String compName = pet.getName();
                compName = compName.toLowerCase();
                if (compName.equals(name)) {
                    return pet;
                }
            }
        }
        return null;
    }

    public List<Pet> getPets() {
        List<Pet> sortedPets = new ArrayList<Pet>(getPetsInternal());
        PropertyComparator.sort(sortedPets, new MutableSortDefinition("name", true, true));
        return Collections.unmodifiableList(sortedPets);
    }

    protected Set<Pet> getPetsInternal() {
        if (this.pets == null) {
            this.pets = new HashSet<Pet>();
        }
        return this.pets;
    }

    public String getTelephone() {
        return this.telephone;
    }

    @Override
    public boolean isNew() {
        return (this.id == null);
    }
    
    @Override
    public boolean getIsNew() {
        return isNew();
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    protected void setPetsInternal(Set<Pet> pets) {
        this.pets = pets;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    @Override
    public String toString() {
        return new ToStringCreator(this) //
                .append("id", this.getId()) //
                .append("new", this.isNew()) //
                .append("lastName", this.getLastName()) //
                .append("firstName", this.getFirstName()) //
                .append("address", this.address) //
                .append("city", this.city) //
                .append("telephone", this.telephone) //
                .toString();
    }
}
