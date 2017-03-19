/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.offnet.ocpp.response;

/**
 *
 * @author gabi
 */
public class NoResponse implements Response {

    @Override
    public String toJson() {
        return "{}";
    }

    @Override
    public String toJsonSoapVersion() {
        return "{}";
    }
    
}
