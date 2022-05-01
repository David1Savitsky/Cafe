package com.epam.webappfinal.entity;

import java.io.Serializable;

/**
 * Interface which is responsible for object to be identifiable.
 *
 * @author David Savitsky
 * @version 1.0
 * @since 1.0
 */
public interface Identifiable extends Serializable {

    /**
     * This method returns object's id.
     *
     * @return id identifier of object.
     *
     */
    Long getId();
}
