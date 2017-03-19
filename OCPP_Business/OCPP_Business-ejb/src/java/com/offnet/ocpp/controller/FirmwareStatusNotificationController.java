/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.offnet.ocpp.controller;

import com.offnet.ocpp.dao.ChargePointDAO;
import com.offnet.ocpp.dao.ChargePointDAOImpl;
import com.offnet.ocpp.dao.FirmwareDAO;
import com.offnet.ocpp.dao.FirmwareDAOImpl;
import com.offnet.ocpp.entity.Firmware;
import com.offnet.ocpp.general.Utilities_Business;
import com.offnet.ocpp.request.FirmwareStatusNotificationRequest;
import com.offnet.ocpp.response.FirmwareStatusNotificationResponse;
import com.offnet.ocpp.response.Response;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author gabi
 */
public class FirmwareStatusNotificationController extends Controller implements Serializable{

    public FirmwareStatusNotificationController(List<String> array, String session_id) {
        super(array, session_id);
    }

    @Override
    public Response processRequest() {
        
        FirmwareStatusNotificationRequest request = new FirmwareStatusNotificationRequest(Utilities_Business.fromStringToJsonObject(super.content));
        FirmwareStatusNotificationResponse response = new FirmwareStatusNotificationResponse();
        
        ChargePointDAO chargePointDAO = new ChargePointDAOImpl(session_id);
        
        FirmwareDAO firmwareDAO = new FirmwareDAOImpl();
        Firmware firmware = firmwareDAO.findLastFirmwareByChargePoint(chargePointDAO.findById(getSerial_number()));
        Firmware firmwareToUpdate = new Firmware();
        firmwareToUpdate.setId(firmware.getId());
        firmwareToUpdate.setStatus(request.getStatus().value());
        firmwareDAO.update(firmwareToUpdate);
        
        return response;
    }

}
