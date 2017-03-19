/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.offnet.ocpp.controllerS;

import com.offnet.ocpp.client.ChangeConfigurationRequest;
import com.offnet.ocpp.client.ChangeConfigurationResponse;
import com.offnet.ocpp.websocket.Device;
import com.offnet.ocpp.websocket.DeviceSessionHandler;
import java.util.List;

/**
 *
 * @author gabi
 */
public class ChangeConfigurationController extends ControllerS{
    
    public ChangeConfigurationController(List<String> array, String session_id) {
        super(array, session_id);
        super.setProcedureName("ChangeConfiguration");
    }
    
    @Override
    public String processRequest() {
        
        ChangeConfigurationRequest changeConfigurationRequest = new ChangeConfigurationRequest();
        Device station = DeviceSessionHandler.getInstance().getDeviceBySerial(super.content.get("chargePointSerialNumber").getAsString());
       
        changeConfigurationRequest.setKey(super.content.get("key").getAsString());
        changeConfigurationRequest.setValue(super.content.get("value").getAsString());
        
        setEndPointLocation(station.getAddress());
        ChangeConfigurationResponse response = port.changeConfiguration(changeConfigurationRequest);
        
        return encapsulateResponse(response, callID);
    }
    
}

