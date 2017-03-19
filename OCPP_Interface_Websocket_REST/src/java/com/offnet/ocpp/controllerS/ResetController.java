/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.offnet.ocpp.controllerS;

import com.offnet.ocpp.client.Client;
import com.offnet.ocpp.controllerS.ControllerS;
import com.offnet.ocpp.request.ResetRequest;
import java.util.List;

/**
 *
 * @author gabi
 */
public class ResetController extends ControllerS{

    public ResetController(List<String> array, String session_id) {
        super(array, session_id);
        super.setProcedureName("Reset");
    }

    @Override
    public String processRequest() {
        
        ResetRequest resetRequest = new ResetRequest(super.content);
        resetRequest.setCallID(callID);
        Client resetClient = new Client();
        resetClient.sendRequest(resetRequest);
        
        return null;
    }
    
}
