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
public class AddNewIdTokenRequest extends Request{
    protected String idTag;

    public String getIdTag() {
        return idTag;
    }

    public void setIdTag(String idTag) {
        this.idTag = idTag;
    }
    
    public AddNewIdTokenRequest (JsonObject json) {
        this.setIdTag(json.get("idTag").getAsString());
    }

    @Override
    public String toJson() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

  
}
