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
import com.offnet.ocpp.dao.StatusNotificationLogDAO;
import com.offnet.ocpp.dao.StatusNotificationLogDAOImpl;
import com.offnet.ocpp.entity.ChargePoint;
import com.offnet.ocpp.entity.Connector;
import com.offnet.ocpp.entity.StatusNotificationLog;
import com.offnet.ocpp.general.Utilities_Business;
import com.offnet.ocpp.request.StatusNotificationRequest;
import com.offnet.ocpp.response.Response;
import com.offnet.ocpp.response.StatusNotificationResponse;
import java.util.Date;
import java.util.List;

/**
 *
 * @author gabi
 */
public class StatusNotificationController extends Controller{

    public StatusNotificationController(List<String> array, String session_id) {
        super(array, session_id);
    }

    @Override
    public Response processRequest() {
        StatusNotificationRequest request = new StatusNotificationRequest(Utilities_Business.fromStringToJsonObject(super.content));
        StatusNotificationResponse response = new StatusNotificationResponse();
        ConnectorDAO connectorDAO = new ConnectorDAOImpl(super.session_id);
        ChargePointDAO chargePointDAO = new ChargePointDAOImpl(super.session_id);
        ChargePoint chargePoint = chargePointDAO.findById(super.serial_number);
        Connector connector = new Connector();
        connector.setChargeBoxSerialNumber(chargePoint);
        connector.setConnectorSerial(request.getConnectorId());
        connector.setErrorCode(request.getErrorCode().replace("_", ""));
        connector.setInfo(request.getInfo());
        connector.setStatus(request.getStatus());
        
        connector.setTime(new Date());
        
        connector.setVendorErrorCode(request.getVendorErrorCode());
        connector.setVendorId(connector.getVendorId());
        int connectorId = connectorDAO.persist_ind(connector);
        
        // set StatusNotificationLog
        
        StatusNotificationLogDAO statusNotificationLogDAO = new StatusNotificationLogDAOImpl();
        StatusNotificationLog statusNotificationLog = new StatusNotificationLog();
        statusNotificationLog.setChargePointSerialNumber(super.serial_number);
        statusNotificationLog.setConnectorId(connectorId);
        statusNotificationLog.setErrorCode(connector.getErrorCode());
        statusNotificationLog.setInfo(connector.getInfo());
        statusNotificationLog.setStatus(connector.getStatus());
        statusNotificationLog.setTimestamp(new Date());
        
        statusNotificationLogDAO.persist(statusNotificationLog);
        
        return response;
    }
    
    
}
