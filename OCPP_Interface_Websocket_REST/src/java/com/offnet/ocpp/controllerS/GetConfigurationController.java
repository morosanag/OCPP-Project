/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.offnet.ocpp.controllerS;

import com.offnet.ocpp.client.Client;
import com.offnet.ocpp.controllerS.ControllerS;
import com.offnet.ocpp.request.GetConfigurationRequest;
import java.util.List;

/**
 *
 * @author gabi
 */
public class GetConfigurationController extends ControllerS {

    public GetConfigurationController(List<String> array, String session_id) {
        super(array, session_id);
        super.setProcedureName("GetConfiguration");
    }

    @Override
    public String processRequest() {
        
        GetConfigurationRequest remoteStartTransaction = new GetConfigurationRequest(super.content);
        remoteStartTransaction.setCallID(callID);
        Client resetClient = new Client();
        resetClient.sendRequest(remoteStartTransaction);
        
        return null;
    }
    
}
