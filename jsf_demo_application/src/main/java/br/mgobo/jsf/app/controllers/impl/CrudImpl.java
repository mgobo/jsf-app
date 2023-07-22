package br.mgobo.jsf.app.controllers.impl;

import java.util.List;

public interface CrudImpl<T> {
    T merge(T object) throws Exception;

    List<T> findAll();

    T findById(Long id);

    T delete(T object) throws Exception;
}
