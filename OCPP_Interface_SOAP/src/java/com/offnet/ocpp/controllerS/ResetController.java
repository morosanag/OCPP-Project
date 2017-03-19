/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.offnet.ocpp.controllerS;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.offnet.ocpp.client.ResetRequest;
import com.offnet.ocpp.client.ResetResponse;
import com.offnet.ocpp.client.ResetType;
import com.offnet.ocpp.websocket.Device;
import com.offnet.ocpp.websocket.DeviceSessionHandler;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author gabi
 */
public class ResetController extends ControllerS{
    
    public ResetController(List<String> array, String session_id) {
        super(array, session_id);
        super.setProcedureName("Reset");
    }
    
    @Override
    public String processRequest() {
        
        ResetRequest resetRequest = new ResetRequest();
        Device station = DeviceSessionHandler.getInstance().getDeviceBySerial(super.content.get("chargePointSerialNumber").getAsString());
        resetRequest.setType(ResetType.fromValue(super.content.get("type").getAsString()));
        setEndPointLocation(station.getAddress());
        ResetResponse response = port.reset(resetRequest);
        
        return encapsulateResponse(response, callID);
    }
    
}
