/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.offnet.ocpp.dao;

import com.offnet.ocpp.entity.ChargePoint;
import com.offnet.ocpp.entity.Firmware;

/**
 *
 * @author gabi
 */
public interface FirmwareDAO extends ObjectDAO<Firmware, String>{
    public Firmware findLastFirmwareByChargePoint(ChargePoint chargeBoxSerial);
}
