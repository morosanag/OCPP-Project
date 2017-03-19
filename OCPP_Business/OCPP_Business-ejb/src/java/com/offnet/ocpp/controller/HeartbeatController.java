/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.offnet.ocpp.controller;

import com.offnet.ocpp.dao.ChargePointDAO;
import com.offnet.ocpp.dao.ChargePointDAOImpl;
import com.offnet.ocpp.entity.ChargePoint;
import com.offnet.ocpp.response.HeartbeatResponse;
import com.offnet.ocpp.response.Response;
import java.util.GregorianCalendar;
import java.util.List;

/**
 *
 * @author gabi
 */
public class HeartbeatController extends Controller{

    public HeartbeatController(List<String> array, String session_id) {
        super(array, session_id);
    }

    @Override
    public Response processRequest() {
        ChargePointDAO chargePointDAO = new ChargePointDAOImpl(super.session_id);
        ChargePoint chargePoint = chargePointDAO.findById(getSerial_number());
        
        GregorianCalendar cal = (GregorianCalendar) GregorianCalendar.getInstance();
        
        boolean ok = chargePointDAO.updateByChargePointSerial(getSerial_number());
        
        HeartbeatResponse response = new HeartbeatResponse();
        // set current date
        response.setCurrentTime(cal);
        
        return response;
    }
    
}
