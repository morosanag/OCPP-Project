/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.offnet.ocpp.controllerS;

import com.offnet.ocpp.client.ClearCacheRequest;
import com.offnet.ocpp.client.ClearCacheResponse;
import com.offnet.ocpp.websocket.Device;
import com.offnet.ocpp.websocket.DeviceSessionHandler;
import java.util.List;

/**
 *
 * @author gabi
 */
public class ClearCacheController extends ControllerS{
    
    public ClearCacheController(List<String> array, String session_id) {
        super(array, session_id);
        super.setProcedureName("ClearCache");
    }
    
    @Override
    public String processRequest() {
        
        ClearCacheRequest clearCacheRequest = new ClearCacheRequest();
        
        Device station = DeviceSessionHandler.getInstance().getDeviceBySerial(super.content.get("chargePointSerialNumber").getAsString());
        
        setEndPointLocation(station.getAddress());
        ClearCacheResponse response = port.clearCache(clearCacheRequest);
        
        return encapsulateResponse(response, callID);
    }
    
}