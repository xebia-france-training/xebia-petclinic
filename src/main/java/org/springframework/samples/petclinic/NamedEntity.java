package org.springframework.samples.petclinic;


/**
 * Simple JavaBean domain object adds a name property to <code>BaseEntity</code>
 * . Used as a base class for objects needing these properties.
 * 
 * @author Ken Krebs
 * @author Juergen Hoeller
 */
public interface NamedEntity extends BaseEntity {

    public void setName(String name);

    public String getName();

}
