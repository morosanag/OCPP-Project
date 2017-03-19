/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.offnet.ocpp.task;

import com.offnet.ocpp.websocket.Device;
import com.offnet.ocpp.websocket.DeviceSessionHandler;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.ScheduleExpression;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.ejb.TimerConfig;
import javax.ejb.TimerService;



/**
 *
 * @author gabi
 */
@Startup
@Singleton
public class ConnectionUpdaterJob {
    
    @Resource
    private TimerService timerService;
    
    @PostConstruct
    public void createProgrammaticalTimer() {
        
        ScheduleExpression everyTenSeconds = new ScheduleExpression();
        
        everyTenSeconds.minute("*/15");
        everyTenSeconds.hour("*");
        timerService.createCalendarTimer(everyTenSeconds, new TimerConfig(
                "passed message " + new Date(), false));
    }
    
    @Timeout
    public void handleTimer(final Timer timer) {
        List<Device> to_be_deleted = new ArrayList<>();
        Date current_date = new Date();
        long current_minutes = current_date.getTime() / (1000 * 60);
        for(Device device : DeviceSessionHandler.getInstance().getDevices()) {
            long device_minutes = device.getLastUpdate().getTime() / (1000 * 60);
            if(current_minutes - device_minutes > 15) {
                to_be_deleted.add(device);
            }
        }
        for(Device device : to_be_deleted) {
            DeviceSessionHandler.getInstance().removeDevice(device.getId());
        }
    }
    
}
