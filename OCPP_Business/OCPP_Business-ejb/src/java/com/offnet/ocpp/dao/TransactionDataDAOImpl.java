/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.offnet.ocpp.dao;

import com.google.gson.JsonObject;
import com.offnet.ocpp.entity.TransactionData;
import com.offnet.ocpp.general.Utilities_Business;
import com.offnet.ocpp.middleware.EMF;
import java.util.GregorianCalendar;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

/**
 *
 * @author gabi
 */
public class TransactionDataDAOImpl implements TransactionDataDAO{

    private String session_id;

    public TransactionDataDAOImpl() {
    }
    
    public TransactionDataDAOImpl(String session_id) {
        this.session_id = session_id;
    }
    
    @Override
    public boolean persist(TransactionData entity) {
    
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
    public Integer persist_ind(TransactionData entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(TransactionData entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public TransactionData findById(String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(TransactionData entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<TransactionData> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean persist_bulk(List<TransactionData> entities) {
        
        boolean ok = true;
        
        // the automated conversion from REST to SOAP comes with few issues that need to be resolved
        for(TransactionData transactionData : entities) {
            if(transactionData.getMeasurand() != null) {
                transactionData.setMeasurand(transactionData.getMeasurand().replaceAll("_", "."));
            }
            if(transactionData.getUnit() != null) {
                transactionData.setUnit(transactionData.getUnit().replaceAll("_", ""));
            }
            ok &= persist(transactionData);
        }
        
        return ok;
    }
    
    @Override
    public TransactionData createTransaction(JsonObject json, String cal) {
        TransactionData transactionData = new TransactionData();
        GregorianCalendar data = Utilities_Business.formatCalendar(cal);
        
        transactionData.setTime(data.getTime());
        if(json.has("value") && json.get("value") != null) {
            transactionData.setValue(json.get("value").getAsString());
        }
        if(json.has("context") && !json.get("context").isJsonNull()) {
            transactionData.setContext(json.get("context").getAsString());
        }
        if(json.has("format") && !json.get("format").isJsonNull()) {
            transactionData.setFormat(json.get("format").getAsString());
        }
        if(json.has("measurand") && !json.get("measurand").isJsonNull()) {
            transactionData.setMeasurand(json.get("measurand").getAsString());
        }
        if(json.has("location") && !json.get("location").isJsonNull()) {
            transactionData.setLocation(json.get("location").getAsString());
        }
        if(json.has("unit") && !json.get("unit").isJsonNull()) {
            transactionData.setUnit(json.get("unit").getAsString());
        }
        return transactionData;
    }
    
}
