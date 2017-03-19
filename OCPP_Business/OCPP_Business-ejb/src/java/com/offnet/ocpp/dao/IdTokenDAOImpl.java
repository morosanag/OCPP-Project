/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.offnet.ocpp.dao;

import com.offnet.ocpp.entity.IdToken;
import com.offnet.ocpp.general.Utilities_Business;
import com.offnet.ocpp.middleware.EMF;
import java.util.Date;
import java.util.List;
import javax.persistence.Cacheable;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import org.json.JSONObject;

/**
 *
 * @author gabi
 */
@Cacheable(false)
public class IdTokenDAOImpl implements IdTokenDAO{

    private final String session_id;

    public IdTokenDAOImpl(String session_id) {
        this.session_id = session_id;
    }
        
    @Override
    public boolean persist(IdToken entity) {
        
        boolean ok = true;
        EntityManager em = EMF.createEntityManager();
        EntityTransaction etx = em.getTransaction();
        etx.begin();
        try {
            em.persist(entity);
            etx.commit();
        } catch (Exception e) {
            ok = false;
            e.printStackTrace();
            etx.rollback();
        } finally {
            em.close();
        }
        return ok;      
    
    }

    @Override
    public boolean update(IdToken entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public IdToken findById(String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(IdToken entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<IdToken> findAll() {
        EntityManager em = EMF.createEntityManager();
        
        List<IdToken> depts = em.createNamedQuery("IdToken.findAll").getResultList();
        em.close();
        
        return depts;
    }

    @Override
    public void deleteAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public IdToken findByValue(String value) {
        EntityManager em = EMF.createEntityManager();
        
        List<IdToken> depts = em.createNamedQuery("IdToken.findByValue").setParameter("value", value).getResultList();
        if(depts == null || depts.isEmpty()) {
            return null;
        }
  
        em.close();
        
        return depts.get(0);
    }

    @Override
    public Integer persist_ind(IdToken entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    @Override
    public JSONObject toJSONObject(IdToken idToken) {
        JSONObject response = new JSONObject();
        
        Utilities_Business.addToJSON(response, "value", idToken.getValue(), false);
        Utilities_Business.addToJSON(response, "accountId", idToken.getUserId().getId(), false);
        Utilities_Business.addToJSON(response, "status", idToken.getStatus(), false);
        
        return response;
        
    }
    
    @Override
    public int regularUpdate() {
    
        EntityManager em = EMF.createEntityManager();
        EntityTransaction etx = em.getTransaction();
        etx.begin();
        int updated = -1;  
        try {
            Date date = new Date();
            Query query = em.createQuery("UPDATE IdToken i SET i.status = 'Expired' WHERE (i.expiryDate < ?1)");
            query.setParameter(1, date);
            updated = query.executeUpdate();
            etx.commit();
        } catch (Exception e) {
            etx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
        
        return updated;
    }
}
