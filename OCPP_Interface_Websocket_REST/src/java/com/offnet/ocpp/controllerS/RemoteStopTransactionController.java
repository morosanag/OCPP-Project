/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.offnet.ocpp.controllerS;

import com.offnet.ocpp.client.Client;
import com.offnet.ocpp.controllerS.ControllerS;
import com.offnet.ocpp.request.RemoteStopTransactionRequest;
import java.util.List;

/**
 *
 * @author gabi
 */
public class RemoteStopTransactionController extends ControllerS {

    public RemoteStopTransactionController(List<String> array, String session_id) {
        super(array, session_id);
        super.setProcedureName("RemoteStopTransaction");
    }

    @Override
    public String processRequest() {

        RemoteStopTransactionRequest remoteStopTransactionRequest = new RemoteStopTransactionRequest(super.content);
        remoteStopTransactionRequest.setCallID(callID);
        Client resetClient = new Client();
        resetClient.sendRequest(remoteStopTransactionRequest);
        
        return null;
    }
    
}
