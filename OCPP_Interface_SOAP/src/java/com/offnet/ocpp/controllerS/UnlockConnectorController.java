/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.offnet.ocpp.controllerS;

import com.offnet.ocpp.client.UnlockConnectorRequest;
import com.offnet.ocpp.client.UnlockConnectorResponse;
import com.offnet.ocpp.websocket.Device;
import com.offnet.ocpp.websocket.DeviceSessionHandler;
import java.util.List;

/**
 *
 * @author gabi
 */
public class UnlockConnectorController extends ControllerS{
    
    public UnlockConnectorController(List<String> array, String session_id) {
        super(array, session_id);
        super.setProcedureName("UnlockConnector");
    }
    
    @Override
    public String processRequest() {
        
        UnlockConnectorRequest unlockConnectorRequest = new UnlockConnectorRequest();
        Device station = DeviceSessionHandler.getInstance().getDeviceBySerial(super.content.get("chargePointSerialNumber").getAsString());
        
        unlockConnectorRequest.setConnectorId(super.content.get("connectorId").getAsInt());
        
        setEndPointLocation(station.getAddress());
        
        UnlockConnectorResponse response = port.unlockConnector(unlockConnectorRequest);
        
        return encapsulateResponse(response, callID);
    }
    
}
