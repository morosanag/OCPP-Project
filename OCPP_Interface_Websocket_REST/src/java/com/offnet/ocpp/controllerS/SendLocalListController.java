/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.offnet.ocpp.controllerS;

import com.offnet.ocpp.client.Client;
import com.offnet.ocpp.request.GetLocalListVersionRequest;
import com.offnet.ocpp.request.SendLocalListRequest;
import java.util.List;

/**
 *
 * @author gabi
 */
public class SendLocalListController extends ControllerS {

    public SendLocalListController(List<String> array, String session_id) {
        super(array, session_id);
        super.setProcedureName("SendLocalList");
    }

    @Override
    public String processRequest() {
        SendLocalListRequest sendLocalListRequest = new SendLocalListRequest(super.content);
        sendLocalListRequest.setCallID(callID);
        Client resetClient = new Client();
        resetClient.sendRequest(sendLocalListRequest);
        
        return null;
    
    }
    
}
