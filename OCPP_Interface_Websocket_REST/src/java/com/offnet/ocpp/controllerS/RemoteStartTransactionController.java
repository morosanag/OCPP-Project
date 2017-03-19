/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.offnet.ocpp.controllerS;

import com.offnet.ocpp.client.Client;
import com.offnet.ocpp.request.RemoteStartTransactionRequest;
import java.util.List;

/**
 *
 * @author gabi
 */
public class RemoteStartTransactionController extends ControllerS {

    public RemoteStartTransactionController(List<String> array, String session_id) {
        super(array, session_id);
        super.setProcedureName("RemoteStartTransaction");
    }

    @Override
    public String processRequest() {

        RemoteStartTransactionRequest remoteStartTransaction = new RemoteStartTransactionRequest(super.content);
        remoteStartTransaction.setCallID(callID);
        Client resetClient = new Client();
        resetClient.sendRequest(remoteStartTransaction);
        
        return null;
    }
    
}
