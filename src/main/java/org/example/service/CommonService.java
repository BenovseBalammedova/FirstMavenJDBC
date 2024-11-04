package org.example.service;

import java.util.List;

public interface CommonService <T> {

    List<T> getAll();

    T create(T user);

    Integer delete(Integer id);

    T update(Integer id, T user);

    T getById(Integer id);

}
