/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.offnet.ocpp.dao;

import com.google.gson.JsonObject;
import com.offnet.ocpp.entity.ChargePoint;
import com.offnet.ocpp.entity.DiagnosticsFile;

/**
 *
 * @author gabi
 */
public interface DiagnosticsFileDAO extends ObjectDAO<DiagnosticsFile, String>{
    public DiagnosticsFile findLastDiagnosticsBySerial(ChargePoint chargePointId);
    public DiagnosticsFile toClass(JsonObject json, String session_id, String station_id);
}
