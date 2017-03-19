/*
* Decompiled with CFR 0_115.
*
* Could not load the following classes:
*  com.google.gson.JsonElement
*  com.google.gson.JsonObject
*  com.offnet.ocpp.dao.ChargePointDAO
*  com.offnet.ocpp.entity.ChargePoint
*  com.offnet.ocpp.general.Constants_Business
*  com.offnet.ocpp.general.Constants_Business$CHARGE_POINT_STATUS
*  com.offnet.ocpp.general.Utilities_Business
*  com.offnet.ocpp.middleware.EMF
*  javax.persistence.EntityManager
*  javax.persistence.EntityTransaction
*  javax.persistence.Query
*/
package com.offnet.ocpp.dao;

import com.google.gson.JsonObject;
import com.offnet.ocpp.entity.ChargePoint;
import com.offnet.ocpp.general.Constants_Business;
import com.offnet.ocpp.general.Utilities_Business;
import com.offnet.ocpp.middleware.EMF;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

public class ChargePointDAOImpl
        implements ChargePointDAO {
    private String session_id;
    
    public String getSession_id() {
        return this.session_id;
    }
    
    public ChargePointDAOImpl(String session_id) {
        this.session_id = session_id;
    }
    
    public void setSession_id(String session_id) {
        this.session_id = session_id;
    }
    
    /*
    * WARNING - Removed try catching itself - possible behaviour change.
    */
    @Override
    public boolean persist(ChargePoint entity) {
        boolean ok = true;
        EntityManager em = EMF.createEntityManager();
        ChargePoint chargePoint_2 = (ChargePoint)em.find((Class)ChargePoint.class, (Object)entity.getChargeBoxSerialNumber());
        EntityTransaction etx = em.getTransaction();
        etx.begin();
        try {
            if (chargePoint_2 == null) {
                em.persist((Object)entity);
            } else {
                chargePoint_2.setStatus(Constants_Business.CHARGE_POINT_STATUS.Available.toString());
                chargePoint_2.setLastUpdate(new Date());
                chargePoint_2.setSessionId(this.session_id);
            }
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
    public boolean update(ChargePoint entity) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @Override
    public ChargePoint findById(String id) {
        EntityManager em = EMF.createEntityManager();
        List depts = em.createNamedQuery("ChargePoint.findByChargeBoxSerialNumber").setParameter("chargeBoxSerialNumber", (Object)id).getResultList();
        if (depts == null || depts.isEmpty()) {
            return null;
        }
        em.close();
        return (ChargePoint)depts.get(0);
    }
    
    @Override
    public void delete(ChargePoint entity) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @Override
    public List<ChargePoint> findAll() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @Override
    public void deleteAll() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @Override
    public ChargePoint toClass(JsonObject json, String session_id, String station_id) {
        ChargePoint request = new ChargePoint();
        request.setChargePointVendor(json.get("chargePointVendor").getAsString());
        request.setChargePointModel(json.get("chargePointModel").getAsString());
        if (Utilities_Business.checkNull((JsonObject)json, (String)"chargePointSerialNumber")) {
            request.setChargePointSerialNumber(json.get("chargePointSerialNumber").getAsString());
        }
        if (Utilities_Business.checkNull((JsonObject)json, (String)"chargeBoxSerialNumber")) {
            request.setChargeBoxSerialNumber(json.get("chargeBoxSerialNumber").getAsString());
        } else {
            request.setChargeBoxSerialNumber(station_id);
        }
        if (Utilities_Business.checkNull((JsonObject)json, (String)"firmwareVersion")) {
            request.setFirmwareVersion(json.get("firmwareVersion").getAsString());
        }
        if (Utilities_Business.checkNull((JsonObject)json, (String)"iccid")) {
            request.setIccid(json.get("iccid").getAsString());
        }
        if (Utilities_Business.checkNull((JsonObject)json, (String)"imsi")) {
            request.setImsi(json.get("imsi").getAsString());
        }
        if (Utilities_Business.checkNull((JsonObject)json, (String)"meterType")) {
            request.setMeterType(json.get("meterType").getAsString());
        }
        if (Utilities_Business.checkNull((JsonObject)json, (String)"meterSerialNumber")) {
            request.setMeterSerialNumber(json.get("meterSerialNumber").getAsString());
        }
        request.setSessionId(session_id);
        request.setStatus("Available");
        request.setLastUpdate(new Date());
        return request;
    }
    
    @Override
    public boolean updateByChargePointSerial(String chargeBoxSerialNumber) {
        EntityManager em = EMF.createEntityManager();
        EntityTransaction etx = em.getTransaction();
        boolean status = false;
        etx.begin();
        List depts = em.createNamedQuery("ChargePoint.findByChargeBoxSerialNumber").setParameter("chargeBoxSerialNumber", (Object)chargeBoxSerialNumber).getResultList();
        if (depts == null || depts.isEmpty()) {
            return false;
        }
        try {
            ChargePoint chargePoint = (ChargePoint)depts.get(0);
            chargePoint.setLastUpdate(new Date());
            if (chargePoint.getStatus().equalsIgnoreCase(Constants_Business.CHARGE_POINT_STATUS.Unavailable.toString())) {
                chargePoint.setStatus(Constants_Business.CHARGE_POINT_STATUS.Available.toString());
            }
            status =  this.persist(chargePoint);
            etx.commit();
        } catch(Exception ex) {
            etx.rollback();
            ex.printStackTrace();
        }
        em.close();
        return status;
    }
    
    @Override
    public Integer persist_ind(ChargePoint entity) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @Override
    public int regularUpdate() {
        EntityManager em = EMF.createEntityManager();
        EntityTransaction etx = em.getTransaction();
        etx.begin();
        int updated = -1;
        try {//"UPDATE ChargePoint c SET c.status = 'Unavailable' WHERE ((c.lastUpdate + " + Constants_Business.HEARTBEAT_INTERVAL + ") < CURRENT_TIMESTAMP)");
            Date date = new Date();
            date.setTime(date.getTime() - Constants_Business.HEARTBEAT_INTERVAL * 1000);
            Query query = em.createQuery("UPDATE ChargePoint c SET c.status = 'Unavailable' WHERE (c.lastUpdate < ?1)");
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