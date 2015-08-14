package com.kishor.batatebeta.core.service;

import com.kishor.batatebeta.exception.BatateException;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Nandakishor on 8/12/2015.
 */
public interface AbstractService<T extends Serializable> {
    T create(T entity) throws BatateException;

    T update(T entity) throws BatateException;

    T findByUid(String uid) throws BatateException;
}
