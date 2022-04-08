package com.epam.webappfinal.dao;

import com.epam.webappfinal.entity.Identifiable;
import com.epam.webappfinal.exception.DaoException;

import java.util.List;
import java.util.Optional;

/**
 * This interface declares the CRUD methods that will interact with database.
 *
 * @author David Savitsky
 * @version 1.0
 * @since 1.0
 */
public interface Dao<T extends Identifiable> {

    /**
     * Purpose of this method is to get specific item from database.
     *
     * @param id  item which would be got from database.
     * @return {@code empty Optional<T>} if item is not found.
     * @throws DaoException if any dao exception occurred during processing
     */
    Optional<T> getById(Long id) throws DaoException;

    /**
     * Purpose of this method is to get all items from database.
     *
     * @return {@code empty List<T>} if items are not found.
     * @throws DaoException if any dao exception occurred during processing
     */
    List<T> getAll() throws DaoException;

    /**
     * Purpose of this method is to save specific item in database.
     *
     * @param item  item which would be saved.
     * @throws DaoException if any dao exception occurred during processing
     */
    void save(T item) throws DaoException;

    /**
     * Purpose of this method is to remove specific item from database by id.
     *
     * @param id id of item which will be deleted.
     * @throws DaoException if any dao exception occurred during processing
     */
    void removeById(Long id) throws DaoException;

}
