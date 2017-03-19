/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.offnet.ocpp.controllerS;

import com.offnet.ocpp.client.RemoteStopTransactionRequest;
import com.offnet.ocpp.client.RemoteStopTransactionResponse;
import com.offnet.ocpp.websocket.Device;
import com.offnet.ocpp.websocket.DeviceSessionHandler;
import java.util.List;

/**
 *
 * @author gabi
 */
public class RemoteStopTransactionController extends ControllerS{
    
    public RemoteStopTransactionController(List<String> array, String session_id) {
        super(array, session_id);
        super.setProcedureName("RemoteStopTransaction");
    }
    
    @Override
    public String processRequest() {
        
        RemoteStopTransactionRequest remoteStopTransactionRequest = new RemoteStopTransactionRequest();
        
        Device station = DeviceSessionHandler.getInstance().getDeviceBySerial(super.content.get("chargePointSerialNumber").getAsString());
        
        remoteStopTransactionRequest.setTransactionId(super.content.get("transactionId").getAsInt());
        
        setEndPointLocation(station.getAddress());
        
        RemoteStopTransactionResponse response = port.remoteStopTransaction(remoteStopTransactionRequest);
        
        return encapsulateResponse(response, callID);
    }
    
}
