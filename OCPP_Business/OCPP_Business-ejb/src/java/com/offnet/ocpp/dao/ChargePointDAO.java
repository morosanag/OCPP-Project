/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.offnet.ocpp.dao;

import com.google.gson.JsonObject;
import com.offnet.ocpp.entity.ChargePoint;

/**
 *
 * @author gabi
 */
public interface ChargePointDAO extends ObjectDAO<ChargePoint, String>{
    
    public boolean updateByChargePointSerial(String chargeBoxSerialNumber);
    public ChargePoint toClass(JsonObject json, String session_id, String station_id);
    public int regularUpdate();
}
