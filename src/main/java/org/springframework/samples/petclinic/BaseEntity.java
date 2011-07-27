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

    /**
     * Introduced because for tomcat 6 & 7 portability:<p/>
     * Tomcat 6 doesn't support "${owner.isNew()}"<p/>
     * Tomcat 7 doesn't support "${owner.new} </p>
     */
    public boolean getIsNew();
}
