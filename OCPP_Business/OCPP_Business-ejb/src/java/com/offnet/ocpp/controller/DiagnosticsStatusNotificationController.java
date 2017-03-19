/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.offnet.ocpp.controller;

import com.offnet.ocpp.dao.ChargePointDAO;
import com.offnet.ocpp.dao.ChargePointDAOImpl;
import com.offnet.ocpp.dao.DiagnosticsFileDAO;
import com.offnet.ocpp.dao.DiagnosticsFileDAOImpl;
import com.offnet.ocpp.entity.ChargePoint;
import com.offnet.ocpp.entity.DiagnosticsFile;
import com.offnet.ocpp.general.Utilities_Business;
import com.offnet.ocpp.request.DiagnosticsStatusNotificationRequest;
import com.offnet.ocpp.response.DiagnosticsStatusNotificationResponse;
import com.offnet.ocpp.response.Response;
import java.util.List;

/**
 *
 * @author gabi
 */
public class DiagnosticsStatusNotificationController extends Controller{

    public DiagnosticsStatusNotificationController(List<String> array, String session_id) {
        super(array, session_id);
    }

    @Override
    public Response processRequest() {
        DiagnosticsStatusNotificationRequest request = new DiagnosticsStatusNotificationRequest(Utilities_Business.fromStringToJsonObject(content));
        DiagnosticsFileDAO diagnosticsFileDAO = new DiagnosticsFileDAOImpl();
        Utilities_Business.pendingRequests.remove(session_id);
        ChargePointDAO chargePointDAO = new ChargePointDAOImpl(session_id);
        ChargePoint chargePoint = chargePointDAO.findById(super.serial_number);
        DiagnosticsFile diagnosticsFile = diagnosticsFileDAO.findLastDiagnosticsBySerial(chargePoint);
        if(diagnosticsFile != null) {
            diagnosticsFile.setStatus(request.getStatus());
            diagnosticsFileDAO.persist(diagnosticsFile);
        }
        DiagnosticsStatusNotificationResponse response = new DiagnosticsStatusNotificationResponse();
        
        
        return response;
        
    }
    
    
    
}
