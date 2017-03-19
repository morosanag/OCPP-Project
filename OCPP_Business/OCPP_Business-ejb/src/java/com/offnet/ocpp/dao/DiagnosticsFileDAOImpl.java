/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.offnet.ocpp.dao;

import com.google.gson.JsonObject;
import com.offnet.ocpp.entity.ChargePoint;
import com.offnet.ocpp.entity.DiagnosticsFile;
import com.offnet.ocpp.general.Utilities_Business;
import com.offnet.ocpp.middleware.EMF;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

/**
 *
 * @author gabi
 */
public class DiagnosticsFileDAOImpl implements DiagnosticsFileDAO{

    private final String findByChargePoint = "SELECT d FROM DiagnosticsFile d WHERE d.chargeBoxSerialNumber = ?1 AND d.diagnosticsFileId = (SELECT MAX(e.diagnosticsFileId) FROM DiagnosticsFile e WHERE e.chargeBoxSerialNumber = ?1)";
    
    @Override
    public boolean persist(DiagnosticsFile entity) {
        boolean ok = true;
        EntityManager em = EMF.createEntityManager();
        DiagnosticsFile diagnosticsFile = null;
        if(entity.getDiagnosticsFileId() != null) {
            diagnosticsFile = (DiagnosticsFile)em.find((Class)DiagnosticsFile.class, (Object)entity.getDiagnosticsFileId());
        }
        EntityTransaction etx = em.getTransaction();
        etx.begin();
        try {
            if(diagnosticsFile == null) {
                em.persist(entity);
            } else {
                diagnosticsFile.setStatus(entity.getStatus());
                diagnosticsFile.setFilename(entity.getFilename());
            }
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
    public Integer persist_ind(DiagnosticsFile entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(DiagnosticsFile entity) {
        boolean ok = true;
        EntityManager em = EMF.createEntityManager();
        DiagnosticsFile transaction_2 = em.find(DiagnosticsFile.class, entity.getDiagnosticsFileId());
        EntityTransaction etx = em.getTransaction();
        etx.begin();
        try {
            if(transaction_2 == null) {
            ok = false;
            } else {
                transaction_2.setFilename(entity.getFilename());
            }
            etx.commit();
        } catch (Exception e) {
            etx.rollback();
            ok = false;
        } finally {
            em.close();
            return ok;
        }
    
    }

    
    @Override
    public DiagnosticsFile findById(String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(DiagnosticsFile entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<DiagnosticsFile> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public DiagnosticsFile toClass(JsonObject json, String session_id, String station_id) {
        DiagnosticsFile diagnosticsFile = new DiagnosticsFile();
        
        diagnosticsFile.setLocation(json.get("location").getAsString());
        if(json.has("startTime")) {
            diagnosticsFile.setStartTime(Utilities_Business.formatCalendar(json.get("startTime").getAsString()).getTime());
        }
        if(json.has("stopTime")) {
            diagnosticsFile.setStopTime(Utilities_Business.formatCalendar(json.get("stopTime").getAsString()).getTime());
        }
        if(json.has("retries")) {
            diagnosticsFile.setRetries(Integer.parseInt(json.get("retries").getAsString()));
        }
        if(json.has("retryInterval")) {
            diagnosticsFile.setRetries(Integer.parseInt(json.get("retryInterval").getAsString()));
        }  
        
        return diagnosticsFile;
    }

    @Override
    public DiagnosticsFile findLastDiagnosticsBySerial(ChargePoint chargePointId) {
        EntityManager em = EMF.createEntityManager();
        
        Query query = em.createQuery(findByChargePoint)
                .setParameter( 1, chargePointId);
        
        List<DiagnosticsFile> depts = query.getResultList();
        
        if(depts == null || depts.isEmpty()) {
            return null;
        }
  
        em.close();
        
        return depts.get(0);
    }
    
}
