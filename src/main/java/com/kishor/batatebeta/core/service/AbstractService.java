package com.kishor.batatebeta.core.service;

import java.io.Serializable;

/**
 * Created by Nandakishor on 8/12/2015.
 */
public interface AbstractService<T extends Serializable> {
    T create(T entity);

    T update(T entity);

    T findByUid(String uid);

    T findAll();
}
