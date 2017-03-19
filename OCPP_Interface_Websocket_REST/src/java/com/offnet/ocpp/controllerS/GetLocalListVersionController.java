/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.offnet.ocpp.controllerS;

import com.offnet.ocpp.client.Client;
import com.offnet.ocpp.request.GetLocalListVersionRequest;
import java.util.List;

/**
 *
 * @author gabi
 */
public class GetLocalListVersionController extends ControllerS {

    public GetLocalListVersionController(List<String> array, String session_id) {
        super(array, session_id);
        super.setProcedureName("GetLocalListVersion");
    }

    @Override
    public String processRequest() {
        
        GetLocalListVersionRequest getLocalListVersionRequest = new GetLocalListVersionRequest(super.content);
        getLocalListVersionRequest.setCallID(callID);
        Client resetClient = new Client();
        resetClient.sendRequest(getLocalListVersionRequest);
        
        return null;
    
    }
    
}
