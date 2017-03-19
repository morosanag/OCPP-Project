/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.offnet.ocpp.controllerS;

import com.offnet.ocpp.client.Client;
import com.offnet.ocpp.controllerS.ControllerS;
import com.offnet.ocpp.request.ChangeConfigurationRequest;
import java.util.List;

/**
 *
 * @author gabi
 */
public class ChangeConfigurationController extends ControllerS {

    public ChangeConfigurationController(List<String> array, String session_id) {
        super(array, session_id);
        super.setProcedureName("ChangeConfiguration");
    }
    
    @Override
    public String processRequest() {
        
        ChangeConfigurationRequest changeConfigurationRequest = new ChangeConfigurationRequest(super.content);
        changeConfigurationRequest.setCallID(callID);
        Client resetClient = new Client();
        resetClient.sendRequest(changeConfigurationRequest);
        
        return null;
    
    }
    
}
