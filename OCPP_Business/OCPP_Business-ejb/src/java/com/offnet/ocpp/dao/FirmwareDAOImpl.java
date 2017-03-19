/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.offnet.ocpp.dao;

import com.offnet.ocpp.entity.ChargePoint;
import com.offnet.ocpp.entity.Firmware;
import com.offnet.ocpp.middleware.EMF;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
/**
 *
 * @author gabi
 */
public class FirmwareDAOImpl implements FirmwareDAO{

    private final String findByChargePoint = "SELECT d FROM Firmware d WHERE d.chargeBoxSerialNumber = ?1 AND d.lastUpdate = (SELECT MAX(e.lastUpdate) FROM Firmware e WHERE e.chargeBoxSerialNumber = ?1)";
   // select * from Transaction where idToken = '1C3FFDC9' and time = (select MAX(t.time) from Transaction t where t.idToken = '1C3FFDC9');
    
    @Override
    public boolean persist(Firmware entity) {
    
        boolean ok = true;
        EntityManager em = EMF.createEntityManager();
        
        Firmware firmware = null; 
        if(entity.getId() != null) {
            firmware = em.find(Firmware.class, entity.getId());
        }
        
        EntityTransaction etx = em.getTransaction();
        etx.begin();
        try {
            if(firmware == null) {
                em.persist(entity);
            } else {
                firmware = em.find(Firmware.class, entity.getId());
                firmware.setLastUpdate(new Date());
                em.merge(firmware);
            }
            etx.commit();
        } catch (Exception e) {
            ok = false;
            etx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
        return ok; 
        
    }

    @Override
    public Integer persist_ind(Firmware entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(Firmware entity) {
        boolean ok = true;
        EntityManager em = EMF.createEntityManager();
        Firmware firmware = em.find(Firmware.class, entity.getId());
        EntityTransaction etx = em.getTransaction();
        etx.begin();
        try {
            if(firmware == null) {
                ok = false;
            } else {
                firmware.setStatus(entity.getStatus());
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
    public Firmware findById(String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Firmware entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Firmware> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Firmware findLastFirmwareByChargePoint(ChargePoint chargeBoxSerial) {
    
        EntityManager em = EMF.createEntityManager();
        
        Query query = em.createQuery(findByChargePoint)
                .setParameter( 1, chargeBoxSerial);
        
        List<Firmware> depts = query.getResultList();
        
        if(depts == null || depts.isEmpty()) {
            return null;
        }
  
        em.close();
        
        return depts.get(0);
        
    }
    
}
