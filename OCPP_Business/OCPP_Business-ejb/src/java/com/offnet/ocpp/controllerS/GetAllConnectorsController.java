/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.offnet.ocpp.controllerS;

import com.offnet.ocpp.dao.ChargePointDAO;
import com.offnet.ocpp.dao.ChargePointDAOImpl;
import com.offnet.ocpp.dao.ConnectorDAO;
import com.offnet.ocpp.dao.ConnectorDAOImpl;
import com.offnet.ocpp.entity.ChargePoint;
import com.offnet.ocpp.entity.Connector;
import com.offnet.ocpp.request.GetAllConnectorsRequest;
import java.util.List;
import org.json.JSONArray;

/**
 *
 * @author gabi
 */
public class GetAllConnectorsController extends ControllerS{

    public GetAllConnectorsController(List<String> array, String session_id) {
        super(array, session_id);
    }

    @Override
    public String processRequest() {
        GetAllConnectorsRequest getDiagnosticsRequest = new GetAllConnectorsRequest(super.content);
        JSONArray response = new JSONArray();
        ConnectorDAO connectorDAO = new ConnectorDAOImpl(session_id);
        
        ChargePointDAO chargePointDAO = new ChargePointDAOImpl(session_id);
        ChargePoint chargePoint = chargePointDAO.findById(getDiagnosticsRequest.getChargePointSerial());
        
        List<Connector> connectors = connectorDAO.findByChargePoint(chargePoint);
        
        for(Connector connector : connectors) {
            response.put(connectorDAO.toJSONObject(connector));
        }
        
        return response.toString();
    }
    
}
