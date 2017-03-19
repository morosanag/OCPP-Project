/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.offnet.ocpp.controllerS;

import java.util.List;

/**
 *
 * @author gabi
 */
public class ControllerSFactory {
    public static ControllerS getControllerS(List<String> array, String session_id) {
        
        if(array.get(2).equalsIgnoreCase("Reset")) {
            return new ResetController(array, session_id);
        } else if(array.get(2).equalsIgnoreCase("GetAllChargePoints")) {
            return new GetAllChargePointsController(array, session_id);
        } else if(array.get(2).equalsIgnoreCase("ChangeAvailability")) {
            return new ChangeAvailabilityController(array, session_id);
        } else if(array.get(2).equalsIgnoreCase("GetDiagnostics")) {
            return new GetDiagnosticsController(array, session_id);
        } else if(array.get(2).equalsIgnoreCase("RemoteStartTransaction")) {
            return new RemoteStartTransactionController(array, session_id);
        } else if(array.get(2).equalsIgnoreCase("RemoteStopTransaction")) {
            return new RemoteStopTransactionController(array, session_id);
        } else if(array.get(2).equalsIgnoreCase("UnlockConnector")) {
            return new UnlockConnectorController(array, session_id);
        } else if(array.get(2).equalsIgnoreCase("GetConfiguration")) {
            return new GetConfigurationController(array, session_id);
        } else if(array.get(2).equalsIgnoreCase("ChangeConfiguration")) {
            return new ChangeConfigurationController(array, session_id);
        } else if(array.get(2).equalsIgnoreCase("SendLocalList")) {
            return new SendLocalListController(array, session_id);
        } else if(array.get(2).equalsIgnoreCase("GetLocalListVersion")) {
            return new GetLocalListVersionController(array, session_id);
        } else if(array.get(2).equalsIgnoreCase("ClearCache")) {
            return new ClearCacheController(array, session_id);
        } else if(array.get(2).equalsIgnoreCase("UpdateFirmware")) {
            return new UpdateFirmwareController(array, session_id);
        } else {
            return new NotImplementedControllerS(array, session_id);
        }
    }
}
