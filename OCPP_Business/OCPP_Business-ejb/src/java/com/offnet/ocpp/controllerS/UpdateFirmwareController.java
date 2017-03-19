/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.offnet.ocpp.controllerS;

import com.offnet.ocpp.dao.ChargePointDAO;
import com.offnet.ocpp.dao.ChargePointDAOImpl;
import com.offnet.ocpp.dao.FirmwareDAO;
import com.offnet.ocpp.dao.FirmwareDAOImpl;
import com.offnet.ocpp.entity.ChargePoint;
import com.offnet.ocpp.entity.Firmware;
import com.offnet.ocpp.general.Utilities_Business;
import com.offnet.ocpp.request.UpdateFirmwareRequest;
import java.util.Date;
import java.util.List;

/**
 *
 * @author gabi
 */
public class UpdateFirmwareController extends ControllerS {

    public UpdateFirmwareController(List<String> array, String session_id) {
        super(array, session_id);
        super.setProcedureName("UpdateFirmware");
    }

    @Override
    public String processRequest() {
        UpdateFirmwareRequest updateFirmwareRequest = new UpdateFirmwareRequest(super.content);
        updateFirmwareRequest.setCallID(callID);
        FirmwareDAO firmwareDAO = new FirmwareDAOImpl();
        Firmware firmware = new Firmware();
        
        ChargePointDAO chargePointDAO = new ChargePointDAOImpl(session_id);  
        ChargePoint chargePoint = chargePointDAO.findById(getSerial_number());
        
        firmware.setChargeBoxSerialNumber(chargePoint);
        firmware.setLocation(updateFirmwareRequest.getLocation());
        firmware.setRetrieveDate(updateFirmwareRequest.getRetrieveDate().getTime());
        firmware.setLastUpdate(new Date());
        
        firmwareDAO.persist(firmware);
        
        
        //Utilities_Business.pendingRequests.put(getCallID(), updateFirmwareRequest);
        
        return null;
    }
    
}
