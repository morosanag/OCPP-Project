/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.offnet.ocpp.job;

import com.offnet.ocpp.dao.ChargePointDAO;
import com.offnet.ocpp.dao.ChargePointDAOImpl;
import com.offnet.ocpp.dao.IdTokenDAO;
import com.offnet.ocpp.dao.IdTokenDAOImpl;
import java.util.Date;
 
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
public class EntityUpdaterJob {
 
	@Resource
	private TimerService timerService;
 
	@PostConstruct
	public void createProgrammaticalTimer() {
                
		ScheduleExpression chargePointExpression = new ScheduleExpression();
                chargePointExpression.minute("*/1");
                chargePointExpression.hour("*");
                final TimerConfig chargePointTimerConfig = new TimerConfig("passed message " + new Date(), false);
                chargePointTimerConfig.setInfo("chargePointUpdaterJob");
                
                ScheduleExpression idTokenExpression = new ScheduleExpression();
                idTokenExpression.minute("*/1");
                idTokenExpression.hour("*");
                final TimerConfig idTokenTimerConfig = new TimerConfig("passed message " + new Date(), false);
                idTokenTimerConfig.setInfo("idTokenUpdaterJob");
                
                timerService.createCalendarTimer(chargePointExpression, chargePointTimerConfig);
                timerService.createCalendarTimer(idTokenExpression, idTokenTimerConfig);
	}
 
	@Timeout
	public void handleTimer(final Timer timer) {
                if(timer.getInfo().equals("chargePointUpdaterJob")) {
                    ChargePointDAO chargePointDAO = new ChargePointDAOImpl("");
                    int updated = chargePointDAO.regularUpdate();
                } else if(timer.getInfo().equals("idTokenUpdaterJob")) {
                    IdTokenDAO idTokenDAO = new IdTokenDAOImpl("");
                    int updated = idTokenDAO.regularUpdate();
                }
	}
}
