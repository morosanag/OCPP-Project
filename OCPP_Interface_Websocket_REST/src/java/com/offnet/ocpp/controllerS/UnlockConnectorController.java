/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.offnet.ocpp.controllerS;

import com.offnet.ocpp.client.Client;
import com.offnet.ocpp.controllerS.ControllerS;
import com.offnet.ocpp.request.UnlockConnectorRequest;
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
        
        UnlockConnectorRequest unlockConnectorRequest = new UnlockConnectorRequest(super.content);
        unlockConnectorRequest.setCallID(callID);
        Client resetClient = new Client();
        resetClient.sendRequest(unlockConnectorRequest);
        
        return null;
    }
    
}
