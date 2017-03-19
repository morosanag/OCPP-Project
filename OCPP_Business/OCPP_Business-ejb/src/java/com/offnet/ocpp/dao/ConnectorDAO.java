/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.offnet.ocpp.dao;

import com.offnet.ocpp.entity.ChargePoint;
import com.offnet.ocpp.entity.Connector;
import java.util.List;
import org.json.JSONObject;

/**
 *
 * @author gabi
 */
public interface ConnectorDAO extends ObjectDAO<Connector, String>{
    public Connector findBySerial(Integer value, ChargePoint chargePointId);
    public List<Connector> findByChargePoint(ChargePoint chargePointSerial);
    public JSONObject toJSONObject(Connector connector);
}
