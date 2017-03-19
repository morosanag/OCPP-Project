/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.offnet.ocpp.controller;

import com.offnet.ocpp.dao.ChargePointDAO;
import com.offnet.ocpp.dao.ChargePointDAOImpl;
import com.offnet.ocpp.dao.ConnectorDAO;
import com.offnet.ocpp.dao.ConnectorDAOImpl;
import com.offnet.ocpp.entity.ChargePoint;
import com.offnet.ocpp.entity.Connector;
import com.offnet.ocpp.general.Constants_Business;
import com.offnet.ocpp.general.Utilities_Business;
import com.offnet.ocpp.request.ChangeAvailabilityRequest;
import com.offnet.ocpp.response.ChangeAvailabilityResponse;
import com.offnet.ocpp.response.Response;
import java.util.List;

/**
 *
 * @author gabi
 */
public class ChangeAvailabilityController extends Controller{

    public ChangeAvailabilityController(List<String> array, String session_id) {
        super(array, session_id);
    }

    @Override
    public Response processRequest() {
        ChangeAvailabilityResponse getDiagnosticsResponse = new ChangeAvailabilityResponse(Utilities_Business.fromStringToJsonObject(super.content));
        if(getDiagnosticsResponse.getStatus().equalsIgnoreCase(Constants_Business.CHANGE_STATUS.Accepted.toString())) {
            
            ChangeAvailabilityRequest request = (ChangeAvailabilityRequest) Utilities_Business.pendingRequests.remove(getCallID());
            
            
            
            ConnectorDAO connectorDAO = new ConnectorDAOImpl(session_id);
            ChargePointDAO chargePointDAO = new ChargePointDAOImpl(session_id);
            
            ChargePoint chargePoint = chargePointDAO.findById(getSerial_number());
            
            Connector connector = connectorDAO.findBySerial(request.getConnectorId(), chargePoint);
            
            if((connector.getStatus().equals(Constants_Business.CHARGE_POINT_STATUS.Available.toString()) || 
               connector.getStatus().equals(Constants_Business.CHARGE_POINT_STATUS.Occupied.toString()) || 
               connector.getStatus().equals(Constants_Business.CHARGE_POINT_STATUS.Reserved.toString())) &&
                    request.getType().equals("Inoperative")) {
            
                connector.setStatus(Constants_Business.CHARGE_POINT_STATUS.Unavailable.toString());
            
            } else if ((connector.getStatus().equals(Constants_Business.CHARGE_POINT_STATUS.Faulted.toString()) || 
                    
                    connector.getStatus().equals(Constants_Business.CHARGE_POINT_STATUS.Unavailable.toString())) &&
                    request.getType().equals("Operative")) {
            
            
                connector.setStatus(Constants_Business.CHARGE_POINT_STATUS.Available.toString());
            
            }
            
            connectorDAO.persist(connector);
            
        }
        
        return getDiagnosticsResponse;
    }
    
}
