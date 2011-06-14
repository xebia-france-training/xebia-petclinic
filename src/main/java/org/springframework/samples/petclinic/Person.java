package org.springframework.samples.petclinic;


/**
 * Simple JavaBean domain object representing an person.
 * 
 * @author Ken Krebs
 */
public interface Person extends BaseEntity {
    public String getFirstName();

    public void setFirstName(String firstName);

    public String getLastName();

    public void setLastName(String lastName);
}
