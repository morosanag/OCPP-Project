/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.offnet.ocpp.client;

import com.google.gson.JsonObject;
import com.offnet.ocpp.general.Utilities_Websocket;
import com.offnet.ocpp.request.Request;
import com.offnet.ocpp.response.Response;
import com.offnet.ocpp.websocket.Device;
import com.offnet.ocpp.websocket.DeviceSessionHandler;
import java.util.ArrayList;

/**
 *
 * @author gabi
 */
public class Client {
    
    private String encapsulateRequest(Request request) {
        ArrayList request_list = new ArrayList();
        request_list.add(2);
        request_list.add("\"" + request.getCallID() + "\"");
        request_list.add("\"" + request.getProcedureName() + "\"");
        request_list.add(request.toJson());
        
        return request_list.toString();
    }

    public void sendRequest(Request request) {

        DeviceSessionHandler deviceSessionHandler = DeviceSessionHandler.getInstance();
        Device device = deviceSessionHandler.getDeviceBySerial(request.getDeviceSerial());
        deviceSessionHandler.sendMessageToDevice(device, encapsulateRequest(request));
        
    }
    
    public void sendRequest(String destination, String request) {

        DeviceSessionHandler deviceSessionHandler = DeviceSessionHandler.getInstance();
        Device device = deviceSessionHandler.getDeviceBySerial(destination);
        deviceSessionHandler.sendMessageToDevice(device, request);
        
    }
}
