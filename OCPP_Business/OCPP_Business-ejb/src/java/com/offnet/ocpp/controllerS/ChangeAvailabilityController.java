/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.offnet.ocpp.controllerS;

import com.offnet.ocpp.general.Utilities_Business;
import com.offnet.ocpp.request.ChangeAvailabilityRequest;
import java.util.List;

/**
 *
 * @author gabi
 */
public class ChangeAvailabilityController extends ControllerS{
    
    public ChangeAvailabilityController(List<String> array, String session_id) {
        super(array, session_id);
        super.setProcedureName("ChangeAvailability");
    }

    @Override
    public String processRequest() {
        ChangeAvailabilityRequest changeAvailabilityRequest = new ChangeAvailabilityRequest(super.content);
        changeAvailabilityRequest.setCallID(callID);
        
        Utilities_Business.pendingRequests.put(getCallID(), changeAvailabilityRequest);
        return null;
    
    }
    
}
