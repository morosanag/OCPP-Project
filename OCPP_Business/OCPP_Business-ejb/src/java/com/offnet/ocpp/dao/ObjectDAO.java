/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.offnet.ocpp.dao;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author gabi
 * @param <T>
 * @param <Id>
 */
public interface ObjectDAO<T, Id extends Serializable>{
    // insert or update
    public boolean persist(T entity);
    
    public Integer persist_ind(T entity);

    public boolean update(T entity);

    public T findById(Id id);

    public void delete(T entity);

    public List<T> findAll();

    public void deleteAll();
    
}
