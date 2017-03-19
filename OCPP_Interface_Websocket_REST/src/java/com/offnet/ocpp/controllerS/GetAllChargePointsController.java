/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.offnet.ocpp.controllerS;

import com.offnet.ocpp.websocket.Device;
import com.offnet.ocpp.websocket.DeviceSessionHandler;
import java.util.List;
import org.json.JSONArray;

/**
 *
 * @author gabi
 */
public class GetAllChargePointsController extends ControllerS {

    public GetAllChargePointsController(List<String> array, String session_id) {
        super(array, session_id);
    }

    @Override
    public String processRequest() {
    
        JSONArray response = new JSONArray();
        DeviceSessionHandler deviceSessionHandler = DeviceSessionHandler.getInstance();
        List<Device> devices = deviceSessionHandler.getDevices();
        for(Device device : devices) {
            response.put(device.toJSONObject());
        }
          
        return response.toString();
    
    }

    
}
