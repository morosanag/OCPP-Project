/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.offnet.ocpp.controllerS;

import com.offnet.ocpp.client.GetDiagnosticsRequest;
import com.offnet.ocpp.client.GetDiagnosticsResponse;
import com.offnet.ocpp.general.Utilities;
import com.offnet.ocpp.websocket.Device;
import com.offnet.ocpp.websocket.DeviceSessionHandler;
import java.util.List;

/**
 *
 * @author gabi
 */
public class GetDiagnosticsController extends ControllerS{
    
    public GetDiagnosticsController(List<String> array, String session_id) {
        super(array, session_id);
        super.setProcedureName("GetDiagnostics");
    }
    
    @Override
    public String processRequest() {
        
        GetDiagnosticsRequest getDiagnosticsRequest = new GetDiagnosticsRequest();
        Device station = DeviceSessionHandler.getInstance().getDeviceBySerial(super.content.get("chargePointSerialNumber").getAsString());
        
        getDiagnosticsRequest.setLocation(super.content.get("location").getAsString());
        if(super.content.has("startTime")) {
            getDiagnosticsRequest.setStartTime(Utilities.formatXMLCalendar(super.content.get("startTime").getAsString()));
        }
        if(super.content.has("stopTime")) {
            getDiagnosticsRequest.setStopTime(Utilities.formatXMLCalendar(super.content.get("stopTime").getAsString()));
        }
        if(super.content.has("retries")) {
            getDiagnosticsRequest.setRetries(super.content.get("retries").getAsInt());
        }
        if(super.content.has("retryInterval")) {
            getDiagnosticsRequest.setRetries(super.content.get("retryInterval").getAsInt());
        }
        
        setEndPointLocation(station.getAddress());
        
        GetDiagnosticsResponse response = port.getDiagnostics(getDiagnosticsRequest);
        
        return encapsulateResponse(response, callID);
    }
}
