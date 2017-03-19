/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.offnet.ocpp.websocket;

/**
 *
 * @author gabi
 */

import com.google.gson.JsonObject;
import java.io.IOException;
import java.util.ArrayList;
import javax.enterprise.context.ApplicationScoped;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.websocket.Session;

@ApplicationScoped
public class DeviceSessionHandler {
    private int deviceId = 0;
    private volatile Set<Session> sessions = new HashSet();
    private volatile Set<Device> devices = new HashSet();
    
    private static final Object lock = new Object();
    private static volatile DeviceSessionHandler instance;

    public static DeviceSessionHandler getInstance() {
        DeviceSessionHandler r = instance;
        if (r == null) {
            synchronized (lock) {    // While we were waiting for the lock, another 
                r = instance;        // thread may have instantiated the object.
                if (r == null) {  
                    r = new DeviceSessionHandler();
                    instance = r;
                }
            }
        }
        return r;
    }
    
    
    public void addSession(Session session) {
        sessions.add(session);
    }

    public void removeSession(Session session) {
        sessions.remove(session);
    }
    
    public List<Device> getDevices() {
        return new ArrayList(devices);
    }
    
    public List<Session> getSessions() {
        return new ArrayList(sessions);
    }
    
    public void addDevice(Device device) {
        boolean isNew = true;
        for(Device device_in : devices) {
            if(device_in.getSerial().equals(device.getSerial())) {
                device.setId(device_in.getId());
                devices.remove(device_in);
                devices.add(device);
                isNew = false;
            }
        }
        
        if(isNew) {
            device.setId(deviceId);
            devices.add(device);
            deviceId++;
        }
    }

    public void removeDevice(int id) {
        Device device = getDeviceById(id);
        if (device != null) {
            devices.remove(device);
        }
    }
    

    public void toggleDevice(int id) {
        
        Device device = getDeviceById(id);
        if (device != null) {
            if ("On".equals(device.getStatus())) {
                device.setStatus("Off");
            } else {
                device.setStatus("On");
            }
        }
    }

    public Device getDeviceBySerial(String serial) {
        for (Device device : devices) {
            if (device.getSerial().equals(serial)) {
                return device;
            }
        }
        return null;
    }
    
    private Device getDeviceById(int id) {
        for (Device device : devices) {
            if (device.getId() == id) {
                return device;
            }
        }
        return null;
    }
    

    public Device getDeviceBySession(Session session) {
        for (Device device : devices) {
            if (device.getSession().equals(session)) {
                return device;
            }
        }
        return null;
    }
    
    private JsonObject createAddMessage(Device device) {
        
        JsonObject addMessage = new JsonObject();
        
        addMessage.addProperty("serial", device.getSerial());
        addMessage.addProperty("status", device.getStatus());
        addMessage.addProperty("session", device.getSession().toString());
        
        return addMessage;
    }

    private void sendToSession(Session session, JsonObject message) {
        try {
            session.getBasicRemote().sendText(message.toString());
        } catch (IOException ex) {
            sessions.remove(session);
            ex.printStackTrace();
        }
    }
    
    private void sendToSession(Session session, String message) {
        try {
            session.getBasicRemote().sendText(message.toString());
        } catch (IOException ex) {
            sessions.remove(session);
            ex.printStackTrace();
        }
    }
    
    public void sendMessageToDevice(Device device, JsonObject message) {
        sendToSession(device.getSession(), message);
    }
    
    public void sendMessageToDevice(Device device, String message) {
        
        sendToSession(device.getSession(), message);
    }
    
}