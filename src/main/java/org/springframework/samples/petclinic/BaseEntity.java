package org.springframework.samples.petclinic;

/**
 * Simple JavaBean domain object with an id property. Used as a base class for
 * objects needing this property.
 * 
 * @author Ken Krebs
 * @author Juergen Hoeller
 */
public interface BaseEntity {

    public void setId(Integer id);

    public Integer getId();

    public boolean isNew();

}
