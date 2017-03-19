/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.offnet.ocpp.business;

import com.offnet.ocpp.controller.Controller;
import com.offnet.ocpp.controller.ControllerFactory;
import com.offnet.ocpp.controller.NotImplementedController;
import com.offnet.ocpp.controllerS.ControllerS;
import com.offnet.ocpp.controllerS.ControllerSFactory;
import com.offnet.ocpp.response.Response;
import java.util.List;
import javax.ejb.Stateless;

/**
 *
 * @author gabi
 */
@Stateless
public class OCPPBusiness implements OCPPBusinessRemote {

    @Override
    public Response processRequest(Controller controller) {
        
        Response response = controller.processRequest();
    
        return response;
    
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    @Override
    public Controller getController(List<String> array, String session_id) {
        
        Controller controller = ControllerFactory.getController(array, session_id);
    
        return controller;
    
    }

    @Override
    public ControllerS getControllerS(List<String> array, String session_id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String processRequestS(ControllerS controller) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String processComplexRequest(List<String> array, String session_id, String stationId) {
        Controller controller = ControllerFactory.getController(array, session_id);
            
        if(controller instanceof NotImplementedController) {
            return null;
        } 
        controller.setSerial_number(stationId);
        

        Response response = controller.processRequest();
        
    
        return controller.encapsulateResponse(response);
    }

    @Override
    public String processComplexRequestS(List<String> array, String session_id, String stationId) {
        
        ControllerS controllerS = ControllerSFactory.getControllerS(array, session_id);
            
        controllerS.setSerial_number(stationId);

        String resp = controllerS.processRequest();
            
        return resp;
        
    }

    @Override
    public String processRequest(List<String> array, String session_id, String stationId) {
        
        Controller controller = ControllerFactory.getController(array, session_id);
            
        if(controller instanceof NotImplementedController) {
            return null;
        } 
        controller.setSerial_number(stationId);
        

        Response response = controller.processRequest();
        
        
        return response.toJsonSoapVersion();
        
    }
    
    
    
    
}
