/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.offnet.ocpp.controllerS;

import com.offnet.ocpp.client.Client;
import com.offnet.ocpp.request.GetDiagnosticsRequest;
import java.util.List;

/**
 *
 * @author gabi
 */
public class GetDiagnosticsController extends ControllerS {

    public GetDiagnosticsController(List<String> array, String session_id) {
        super(array, session_id);
        super.setProcedureName("GetDiagnostics");
    }

    @Override
    public String processRequest() {
        
        GetDiagnosticsRequest getDiagnosticsRequest = new GetDiagnosticsRequest(super.content);
        getDiagnosticsRequest.setCallID(callID);
        Client resetClient = new Client();
        resetClient.sendRequest(getDiagnosticsRequest);
        
        return null;
    
    }
    
    
}
