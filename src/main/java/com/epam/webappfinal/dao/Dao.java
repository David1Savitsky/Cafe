package com.epam.webappfinal.dao;

import com.epam.webappfinal.entity.Identifiable;
import com.epam.webappfinal.exception.DaoException;

import java.util.List;
import java.util.Optional;
//CRUD
public interface Dao<T extends Identifiable> {

    Optional<T> getById(Long id) throws DaoException;

    List<T> getAll() throws DaoException;

    void save(T item) throws DaoException;

    void removeById(Long id) throws DaoException;

}
