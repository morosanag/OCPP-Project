/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.offnet.ocpp.controllerS;

import com.offnet.ocpp.dao.ChargePointDAO;
import com.offnet.ocpp.dao.ChargePointDAOImpl;
import com.offnet.ocpp.dao.DiagnosticsFileDAO;
import com.offnet.ocpp.dao.DiagnosticsFileDAOImpl;
import com.offnet.ocpp.entity.ChargePoint;
import com.offnet.ocpp.entity.DiagnosticsFile;
import com.offnet.ocpp.general.Utilities_Business;
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
        Utilities_Business.pendingRequests.put(getCallID(), getDiagnosticsRequest);
        
        DiagnosticsFileDAO diagnosticsFileDAO = new DiagnosticsFileDAOImpl();
        DiagnosticsFile diagnosticsFile = diagnosticsFileDAO.toClass(content, session_id, getDiagnosticsRequest.getDeviceSerial());
        
        ChargePointDAO chargePointDAO = new ChargePointDAOImpl(session_id);
        ChargePoint chargePoint = chargePointDAO.findById(getDiagnosticsRequest.getDeviceSerial());
        
        diagnosticsFile.setChargeBoxSerialNumber(chargePoint);
        diagnosticsFile.setStatus("Started");
            
        diagnosticsFileDAO.persist(diagnosticsFile);
        
        return null;
    
    }
    
    
}
