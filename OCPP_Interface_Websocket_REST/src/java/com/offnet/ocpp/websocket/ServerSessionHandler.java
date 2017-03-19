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
public class ServerSessionHandler {
    private int deviceId = 0;
    private final Set<Session> sessions = new HashSet();
    private final Set<Device> servers = new HashSet();
    
    private static final Object lock = new Object();
    private static volatile ServerSessionHandler instance;

    public static ServerSessionHandler getInstance() {
        ServerSessionHandler r = instance;
        if (r == null) {
            synchronized (lock) {    // While we were waiting for the lock, another 
                r = instance;        // thread may have instantiated the object.
                if (r == null) {  
                    r = new ServerSessionHandler();
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
    
    public List getServers() {
        return new ArrayList(servers);
    }

    public List<Session> getSessions() {
        return new ArrayList(sessions);
    }
    
    public void addServer(Device device) {
        device.setId(deviceId);
        servers.add(device);
        deviceId++;
        //JsonObject addMessage = createAddMessage(device);
        //sendToAllConnectedSessions(addMessage);
    }

    public void removeServer(int id) {
        Device device = getServerById(id);
        if (device != null) {
            servers.remove(device);
        }
    }
    

    public void toggleServer(int id) {
        
        Device device = getServerById(id);
        if (device != null) {
            if ("On".equals(device.getStatus())) {
                device.setStatus("Off");
            } else {
                device.setStatus("On");
            }
        }
    }

    public Device getServerBySerial(String serial) {
        for (Device device : servers) {
            if (device.getSerial().equals(serial)) {
                return device;
            }
        }
        return null;
    }
    
    private Device getServerById(int id) {
        for (Device device : servers) {
            if (device.getId() == id) {
                return device;
            }
        }
        return null;
    }

    public Device getServerBySession(Session session) {
        for (Device device : servers) {
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

    private void sendToAllConnectedSessions(JsonObject message) {
        for (Session session : sessions) {
            sendToSession(session, message);
        }
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
    
    public void sendMessageToServers(JsonObject message) {
        for(Device device : servers) {
            sendToSession(device.getSession(), message);
        }
    }
    
    public void sendMessageToServers(String message) {
        for(Device device : servers) {
            sendToSession(device.getSession(), message);
        }
    }
    
    
}