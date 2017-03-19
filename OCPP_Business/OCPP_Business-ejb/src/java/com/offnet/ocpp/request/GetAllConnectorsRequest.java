/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.offnet.ocpp.request;

import com.google.gson.JsonObject;

/**
 *
 * @author gabi
 */
public class GetAllConnectorsRequest extends Request{
    
    private String chargePointSerial;

    public String getChargePointSerial() {
        return chargePointSerial;
    }

    public void setChargePointSerial(String chargePointSerial) {
        this.chargePointSerial = chargePointSerial;
    }

    @Override
    public String toJson() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public GetAllConnectorsRequest (JsonObject json) {
        setProcedureName("GetAllConnectors");
        setChargePointSerial(json.get("chargePointSerial").getAsString());
    }
    
    
}
