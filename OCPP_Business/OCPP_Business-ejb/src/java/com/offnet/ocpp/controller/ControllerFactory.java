/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.offnet.ocpp.controller;

import com.offnet.ocpp.general.Utilities_Business;
import com.offnet.ocpp.request.Request;
import java.util.List;

/**
 *
 * @author gabi
 */
public class ControllerFactory {
    public static Controller getController(List<String> array, String session_id) {
        
        if(array.get(2).equalsIgnoreCase("BootNotification")) {
            return new BootNotificationController(array, session_id);
        } else if(array.get(2).equalsIgnoreCase("HeartBeat")) {
            return new HeartbeatController(array, session_id);
        } else if(array.get(2).equalsIgnoreCase("Authorize")) {
            return new AuthorizeController(array, session_id);
        } else if(array.get(2).equalsIgnoreCase("StartTransaction")) {
            return new StartTransactionController(array, session_id);    
        } else if(array.get(2).equalsIgnoreCase("StopTransaction")) {
            return new StopTransactionController(array, session_id);    
        } else if(array.get(2).equalsIgnoreCase("StatusNotification")) {
            return new StatusNotificationController(array, session_id);    
        } else if(array.get(2).equalsIgnoreCase("MeterValues")) {
            return new MeterValuesController(array, session_id);    
        } else if(array.get(2).equalsIgnoreCase("AddNewIdtoken")) {
            return new AddNewIdToken(array, session_id);    
        } else if(array.get(2).equalsIgnoreCase("DiagnosticsStatusNotification")) {
            return new DiagnosticsStatusNotificationController(array, session_id);    
        } else if(array.get(2).equalsIgnoreCase("ChangeAvailability")) {
            return new ChangeAvailabilityController(array, session_id);    
        } else if(array.get(2).equalsIgnoreCase("FirmwareStatusNotification")) {
            return new FirmwareStatusNotificationController(array, session_id);
        } else if(array.get(2).equalsIgnoreCase("GetDiagnostics")) {
            return new GetDiagnosticsController(array, session_id);
        } else {
            // for responses from the station to be processed as requests (response does not contain type of the message
            Request request = Utilities_Business.pendingRequests.get(array.get(1));
            if(request != null && request.getProcedureName() != null && !request.getProcedureName().equals("")) {
                array.add(2, request.getProcedureName());
                return getController(array, session_id);
            }
            
            return new NotImplementedController(array, session_id);
        }
    }
}
