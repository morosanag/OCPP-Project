/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.offnet.ocpp.controllerS;

import com.offnet.ocpp.client.UpdateFirmwareRequest;
import com.offnet.ocpp.client.UpdateFirmwareResponse;
import com.offnet.ocpp.general.Utilities;
import com.offnet.ocpp.websocket.Device;
import com.offnet.ocpp.websocket.DeviceSessionHandler;
import java.util.List;

/**
 *
 * @author gabi
 */
public class UpdateFirmwareController extends ControllerS{
    
    public UpdateFirmwareController(List<String> array, String session_id) {
        super(array, session_id);
        super.setProcedureName("UpdateFirmware");
    }
    
    @Override
    public String processRequest() {
        
        UpdateFirmwareRequest updateFirmwareRequest = new UpdateFirmwareRequest();
        Device station = DeviceSessionHandler.getInstance().getDeviceBySerial(super.content.get("chargePointSerialNumber").getAsString());
        
        updateFirmwareRequest.setLocation(super.content.get("location").getAsString());
        updateFirmwareRequest.setRetrieveDate(Utilities.formatXMLCalendar(super.content.get("retrieveDate").getAsString()));
        if(super.content.has("retries")) {
            updateFirmwareRequest.setRetries(super.content.get("retries").getAsInt());
        }
        
        if(super.content.has("retryInterval")) {
            updateFirmwareRequest.setRetryInterval(super.content.get("retryInterval").getAsInt());
        }
        
        setEndPointLocation(station.getAddress());
        
        UpdateFirmwareResponse response = port.updateFirmware(updateFirmwareRequest);
        
        return encapsulateResponse(response, callID);
    }
}