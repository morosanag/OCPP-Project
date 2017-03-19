/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.offnet.ocpp.response;

import com.google.gson.JsonObject;

/**
 *
 * @author gabi
 */
public class AddNewIdTokenResponse implements Response{

    private boolean status;

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
    
    @Override
    public String toJson() {
        JsonObject json = new JsonObject();
        json.addProperty("status", getStatus());
        return json.toString();
    }

    @Override
    public String toJsonSoapVersion() {
        return "AddnewODTokenResponse";
    }
    
}
