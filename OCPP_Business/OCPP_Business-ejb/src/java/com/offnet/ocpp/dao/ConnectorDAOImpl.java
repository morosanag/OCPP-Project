/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.offnet.ocpp.dao;

import com.offnet.ocpp.entity.ChargePoint;
import com.offnet.ocpp.entity.Connector;
import com.offnet.ocpp.general.Utilities_Business;
import com.offnet.ocpp.middleware.EMF;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import org.json.JSONObject;

/**
 *
 * @author gabi
 */
public class ConnectorDAOImpl implements ConnectorDAO{

    private final String session_id;

    public ConnectorDAOImpl(String session_id) {
        this.session_id = session_id;
    }
    
    @Override
    public boolean persist(Connector entity) {
        boolean ok = true;
        EntityManager em = EMF.createEntityManager();
        
        Connector connector = findBySerial(entity.getConnectorSerial(), entity.getChargeBoxSerialNumber());
        
        EntityTransaction etx = em.getTransaction();
        
        etx.begin();
        try {
            if(connector == null) {
                em.persist(entity);
            } else {
                connector = em.find(Connector.class, connector.getConnectorId());
                connector.setStatus(entity.getStatus());
                connector.setErrorCode(entity.getErrorCode().replace("_", ""));
                connector.setInfo(entity.getInfo());
                connector.setTime(new Date());
                connector.setVendorErrorCode(entity.getVendorErrorCode());
                connector.setVendorId(entity.getVendorId());
                //em.merge(connector);
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
    public Integer persist_ind(Connector entity) {
        
        EntityManager em = EMF.createEntityManager();
        
        Connector connector = findBySerial(entity.getConnectorSerial(), entity.getChargeBoxSerialNumber());
        
        EntityTransaction etx = em.getTransaction();
        
        etx.begin();
        try {
            if(connector == null) {
                em.persist(entity);
            } else {
                connector = em.find(Connector.class, connector.getConnectorId());
                connector.setStatus(entity.getStatus());
                connector.setErrorCode(entity.getErrorCode().replace("_", ""));
                connector.setInfo(entity.getInfo());
                connector.setTime(new Date());
                connector.setVendorErrorCode(entity.getVendorErrorCode());
                connector.setVendorId(entity.getVendorId());
                //em.merge(connector);
            }
            etx.commit();
        } catch (Exception e) {
            etx.rollback();
            e.printStackTrace();
            return -1;
        } finally {
            em.close();
            return connector.getConnectorId();  
        }
    }
    
    
    @Override
    public boolean update(Connector entity) {
        boolean ok = true;
        EntityManager em = EMF.createEntityManager();
        Connector connector_2 = em.find(Connector.class, entity.getConnectorId());
        EntityTransaction etx = em.getTransaction();
        etx.begin();
        try {
            if(connector_2 == null) {
                ok = false;
            } else {
                connector_2.setStatus(entity.getStatus());
                connector_2.setInfo(entity.getInfo());
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
    public Connector findById(String id) {
        
        EntityManager em = EMF.createEntityManager();
        
        List<Connector> depts = em.createNamedQuery("Connector.findByConnectorId").setParameter("connectorId", id).getResultList();
        if(depts == null || depts.isEmpty()) {
            return null;
        }
  
        em.close();
        
        return depts.get(0);
    }

    @Override
    public void delete(Connector entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Connector> findAll() {
    
        EntityManager em = EMF.createEntityManager();
        List<Connector> depts = em.createNamedQuery("Connector.findAll").getResultList();
        em.close();
        
        return depts;
    
    }

    @Override
    public void deleteAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    @Override
    public Connector findBySerial(Integer value, ChargePoint chargePointId) {
        EntityManager em = EMF.createEntityManager();
        
        Query query = em.createQuery( "SELECT c FROM Connector c WHERE c.connectorSerial = ?1 AND c.chargeBoxSerialNumber = ?2" );
        query.setParameter( 1, value );
        query.setParameter( 2, chargePointId );
        
        List<Connector> depts = query.getResultList();
        
        em.close();
        
        if(depts == null || depts.isEmpty()) {
            return null;
        }
        
        return depts.get(0);
    
    }

    @Override
    public JSONObject toJSONObject(Connector connector) {
        
        JSONObject response = new JSONObject();
        
        Utilities_Business.addToJSON(response, "connectorId", connector.getConnectorId(), false);
        Utilities_Business.addToJSON(response, "connectorSerial", connector.getConnectorSerial(), false);
        Utilities_Business.addToJSON(response, "status", connector.getStatus(), false);
        
        return response;
        
    }

    @Override
    public List<Connector> findByChargePoint(ChargePoint chargePointSerial) {
        EntityManager em = EMF.createEntityManager();
        
        Query query = em.createQuery( "SELECT c FROM Connector c WHERE c.chargeBoxSerialNumber = ?1" );
        query.setParameter( 1, chargePointSerial );
        
        List<Connector> depts = query.getResultList();
        
        em.close();
        
        if(depts == null || depts.isEmpty()) {
            return null;
        }
        
        return depts;
    }

    
}
