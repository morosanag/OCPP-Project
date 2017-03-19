/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.offnet.ocpp.controllerS;

import com.google.gson.JsonArray;
import com.offnet.ocpp.client.GetConfigurationResponse;
import com.offnet.ocpp.client.GetConfigurationRequest;
import com.offnet.ocpp.websocket.Device;
import com.offnet.ocpp.websocket.DeviceSessionHandler;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;

/**
 *
 * @author gabi
 */
public class GetConfigurationController extends ControllerS{
    
    public GetConfigurationController(List<String> array, String session_id) {
        super(array, session_id);
        super.setProcedureName("GetConfiguration");
    }
    
    @Override
    public String processRequest() {
        
        GetConfigurationRequest getConfigurationRequest = new GetConfigurationRequest();
        Device station = DeviceSessionHandler.getInstance().getDeviceBySerial(super.content.get("chargePointSerialNumber").getAsString());
       
        JsonArray keys = super.content.get("key").getAsJsonArray();
        for(int i = 0; i < keys.size(); i++) {
            getConfigurationRequest.getKey().add(keys.get(i).getAsString());
        }        
        
        setEndPointLocation(station.getAddress());
        
        GetConfigurationResponse response = port.getConfiguration(getConfigurationRequest);
        
        return encapsulateResponse(response, callID);
    }
    
}
