/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.offnet.ocpp.controller;

import com.offnet.ocpp.response.Response;
import java.util.List;

/**
 *
 * @author gabi
 */
public class NotImplementedController extends Controller_WebSocket{

    public NotImplementedController(List<String> array, String session_id) {
        super(array, session_id);
    }


    @Override
    public Response processRequest() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    
}
