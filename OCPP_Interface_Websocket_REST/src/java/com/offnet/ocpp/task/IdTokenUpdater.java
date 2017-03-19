/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.offnet.ocpp.task;

import com.offnet.ocpp.websocket.DeviceSessionHandler;
import java.util.List;
import java.util.TimerTask;
import javax.websocket.Session;

/**
 *
 * @author gabi
 */
public class IdTokenUpdater extends TimerTask {
    
    @Override
    public void run() {
        DeviceSessionHandler deviceHandler = DeviceSessionHandler.getInstance();
        
        List<Session> listSessions = deviceHandler.getSessions();
        for(Session session : listSessions) {
        }
        
    }
    
}
