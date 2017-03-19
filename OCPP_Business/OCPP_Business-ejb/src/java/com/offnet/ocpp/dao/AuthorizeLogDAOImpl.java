/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.offnet.ocpp.dao;

import com.offnet.ocpp.entity.AuthorizeLog;
import com.offnet.ocpp.entity.ChargePoint;
import com.offnet.ocpp.general.Constants_Business;
import com.offnet.ocpp.middleware.EMF;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

/**
 *
 * @author gabi
 */
public class AuthorizeLogDAOImpl implements AuthorizeLogDAO{

    @Override
    public boolean persist(AuthorizeLog entity) {
        
        boolean ok = true;
        EntityManager em = EMF.createEntityManager();
        EntityTransaction etx = em.getTransaction();
        etx.begin();
        try {
            em.persist((Object)entity);
            etx.commit();
        }
        catch (Exception e) {
            ok = false;
            e.printStackTrace();
            etx.rollback();
        }
        finally {
            em.close();
        }
        return ok;
        
    }

    @Override
    public Integer persist_ind(AuthorizeLog entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(AuthorizeLog entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public AuthorizeLog findById(String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(AuthorizeLog entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<AuthorizeLog> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
