/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.offnet.ocpp.controllerS;

import com.offnet.ocpp.client.GetLocalListVersionRequest;
import com.offnet.ocpp.client.GetLocalListVersionResponse;
import com.offnet.ocpp.websocket.Device;
import com.offnet.ocpp.websocket.DeviceSessionHandler;
import java.util.List;

/**
 *
 * @author gabi
 */
public class GetLocalListVersionController extends ControllerS{
    
    public GetLocalListVersionController(List<String> array, String session_id) {
        super(array, session_id);
        super.setProcedureName("GetLocalListVersion");
    }
    
    @Override
    public String processRequest() {
        
        GetLocalListVersionRequest getLocalListVersionRequest = new GetLocalListVersionRequest();
        Device station = DeviceSessionHandler.getInstance().getDeviceBySerial(super.content.get("chargePointSerialNumber").getAsString());
        
        setEndPointLocation(station.getAddress());
        
        GetLocalListVersionResponse response = port.getLocalListVersion(getLocalListVersionRequest);
        
        return encapsulateResponse(response, callID);
    }
    
}

