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
        
        if(array.get(2).equalsIgnoreCase("GetDiagnostics")) {
            return new GetDiagnosticsController(array, session_id);
        } else if(array.get(2).equalsIgnoreCase("GetAllIdTokens")) {
            return new GetAllIdTokensController(array, session_id);
        } else if(array.get(2).equalsIgnoreCase("GetAllConnectors")) {
            return new GetAllConnectorsController(array, session_id);
        } else if(array.get(2).equalsIgnoreCase("ChangeAvailability")) {
            return new ChangeAvailabilityController(array, session_id);
        } else if(array.get(2).equalsIgnoreCase("UpdateFirmware")) {
            return new UpdateFirmwareController(array, session_id);
        } else {
            return new NotImplementedControllerS(array, session_id);
        }
    }
}
