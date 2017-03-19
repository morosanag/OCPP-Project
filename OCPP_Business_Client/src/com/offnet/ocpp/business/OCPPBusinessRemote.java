/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.offnet.ocpp.business;

import com.offnet.ocpp.controller.Controller;
import com.offnet.ocpp.controllerS.ControllerS;
import com.offnet.ocpp.response.Response;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author gabi
 */
@Remote
public interface OCPPBusinessRemote {
    
    public Response processRequest(Controller controller);
    
    public String processRequestS(ControllerS controller);
    
    public ControllerS getControllerS(List<String> array, String session_id);
    
    public Controller getController(List<String> array, String session_id);
    
    public String processComplexRequest(List<String> array, String session_id, String stationId);
    
    public String processRequest(List<String> array, String session_id, String stationId);
    
    public String processComplexRequestS(List<String> array, String session_id, String stationId);
    
    
}
