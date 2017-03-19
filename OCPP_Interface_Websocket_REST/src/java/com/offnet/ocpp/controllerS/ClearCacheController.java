/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.offnet.ocpp.controllerS;

import com.offnet.ocpp.client.Client;
import com.offnet.ocpp.request.ClearCacheRequest;
import java.util.List;

/**
 *
 * @author gabi
 */
public class ClearCacheController  extends ControllerS{

    public ClearCacheController(List<String> array, String session_id) {
        super(array, session_id);
        super.setProcedureName("ClearCache");
    }

    @Override
    public String processRequest() {
        
        ClearCacheRequest clearCacheRequest = new ClearCacheRequest(super.content);
        clearCacheRequest.setCallID(callID);
        Client resetClient = new Client();
        resetClient.sendRequest(clearCacheRequest);
        
        return null;
    }
    
}
