/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.offnet.ocpp.controllerS;

import com.offnet.ocpp.client.AvailabilityStatus;
import com.offnet.ocpp.client.AvailabilityType;
import com.offnet.ocpp.client.ChangeAvailabilityRequest;
import com.offnet.ocpp.client.ChangeAvailabilityResponse;
import com.offnet.ocpp.client.ResetRequest;
import com.offnet.ocpp.client.ResetResponse;
import com.offnet.ocpp.client.ResetType;
import com.offnet.ocpp.websocket.Device;
import com.offnet.ocpp.websocket.DeviceSessionHandler;
import java.util.List;

/**
 *
 * @author gabi
 */
public class ChangeAvailabilityController extends ControllerS{
    
    public ChangeAvailabilityController(List<String> array, String session_id) {
        super(array, session_id);
        super.setProcedureName("ChangeAvailability");
    }
    
    @Override
    public String processRequest() {
        
        ChangeAvailabilityRequest changeAvailabilityRequest = new ChangeAvailabilityRequest();
        Device station = DeviceSessionHandler.getInstance().getDeviceBySerial(super.content.get("chargePointSerialNumber").getAsString());
        
        changeAvailabilityRequest.setConnectorId(super.content.get("connectorId").getAsInt());
        changeAvailabilityRequest.setType(AvailabilityType.fromValue(super.content.get("type").getAsString()));
        
        setEndPointLocation(station.getAddress());
        
        ChangeAvailabilityResponse response = port.changeAvailability(changeAvailabilityRequest);
        
        return encapsulateResponse(response, callID);
    }
    
}