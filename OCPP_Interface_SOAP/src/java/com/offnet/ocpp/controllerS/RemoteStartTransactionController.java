/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.offnet.ocpp.controllerS;

import com.offnet.ocpp.client.RemoteStartTransactionRequest;
import com.offnet.ocpp.client.RemoteStartTransactionResponse;
import com.offnet.ocpp.websocket.Device;
import com.offnet.ocpp.websocket.DeviceSessionHandler;
import java.util.List;

/**
 *
 * @author gabi
 */
public class RemoteStartTransactionController extends ControllerS{
    
    public RemoteStartTransactionController(List<String> array, String session_id) {
        super(array, session_id);
        super.setProcedureName("RemoteStartTransaction");
    }
    
    @Override
    public String processRequest() {
        
        RemoteStartTransactionRequest remoteStartTransactionRequest = new RemoteStartTransactionRequest();
        
        Device station = DeviceSessionHandler.getInstance().getDeviceBySerial(super.content.get("chargePointSerialNumber").getAsString());
        
        if(super.content.has("connectorId")) {
            remoteStartTransactionRequest.setConnectorId(super.content.get("connectorId").getAsInt());
        }
        remoteStartTransactionRequest.setIdTag(super.content.get("idTag").getAsString());
        
        setEndPointLocation(station.getAddress());
        
        RemoteStartTransactionResponse response = port.remoteStartTransaction(remoteStartTransactionRequest);
        
        return encapsulateResponse(response, callID);
    }
    
}
