/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.offnet.ocpp.controllerS;

import java.util.List;

/**
 *
 * @author gabi
 */
public class NotImplementedControllerS extends ControllerS{

    public NotImplementedControllerS(List<String> array, String session_id) {
        super(array, session_id);
    }

    @Override
    public String processRequest() {
        return null;
    }

}
