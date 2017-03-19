/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.offnet.ocpp.controller;

import com.offnet.ocpp.entity.RegistrationStatus;
import com.offnet.ocpp.dao.ChargePointDAO;
import com.offnet.ocpp.dao.ChargePointDAOImpl;
import com.offnet.ocpp.entity.ChargePoint;
import com.offnet.ocpp.general.Constants_Business;
import com.offnet.ocpp.general.Utilities_Business;
import com.offnet.ocpp.response.BootNotificationResponse;
import com.offnet.ocpp.response.Response;
import java.io.Serializable;
import java.util.GregorianCalendar;
import java.util.List;

/**
 *
 * @author gabi
 */
public class BootNotificationController extends Controller implements Serializable{

    public BootNotificationController(List<String> array, String session_id) {
        super(array, session_id);
    }

    @Override
    public Response processRequest() {
        
        ChargePointDAO chargePointDAO = new ChargePointDAOImpl(super.session_id);
        
        ChargePoint request = chargePointDAO.toClass(Utilities_Business.fromStringToJsonObject(super.content), super.session_id, super.serial_number);
        request.setStatus("Available");
        boolean ok = chargePointDAO.persist(request);
        
        BootNotificationResponse response = new BootNotificationResponse();
        // set heartbeat
        response.setHeartbeatInterval(Constants_Business.HEARTBEAT_INTERVAL);
        // set current date
        GregorianCalendar cal = (GregorianCalendar) GregorianCalendar.getInstance();
        response.setCurrentTime(cal);
        // set status
        if(ok) {
            response.setStatus(new RegistrationStatus("Accepted"));
        } else {
            response.setStatus(new RegistrationStatus("Rejected"));   
        }
        
        return response;
    }

}
