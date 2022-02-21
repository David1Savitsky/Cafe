package com.epam.webappfinal.dao;

import com.epam.webappfinal.entity.Identifiable;
import com.epam.webappfinal.entity.User;
import com.epam.webappfinal.exception.DaoException;

import java.util.List;
import java.util.Optional;
//CRUD
public interface Dao<T extends Identifiable> {

    Optional<User> getById(Long id);

    List<T> getAll() throws DaoException;

    void save(User item);

    void removeById(Long id);

}
