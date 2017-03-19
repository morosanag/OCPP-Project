/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.offnet.ocpp.controller;

import com.offnet.ocpp.dao.AuthorizeLogDAO;
import com.offnet.ocpp.dao.AuthorizeLogDAOImpl;
import com.offnet.ocpp.dao.IdTokenDAO;
import com.offnet.ocpp.dao.IdTokenDAOImpl;
import com.offnet.ocpp.entity.AuthorizationStatus;
import com.offnet.ocpp.entity.AuthorizeLog;
import com.offnet.ocpp.entity.IdToken;

import com.offnet.ocpp.general.Constants_Business.TOKEN_STATUS;
import com.offnet.ocpp.general.Utilities_Business;
import com.offnet.ocpp.request.AuthorizeRequest;
import com.offnet.ocpp.response.AuthorizeResponse;
import com.offnet.ocpp.response.Response;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 *
 * @author gabi
 */
public class AuthorizeController extends Controller{

    public AuthorizeController(List<String> array, String session_id) {
        super(array, session_id);
    }

    @Override
    public Response processRequest() {
        AuthorizeRequest request = new AuthorizeRequest(Utilities_Business.fromStringToJsonObject(super.content));
        AuthorizeResponse response = new AuthorizeResponse();
       
        IdTokenDAO idTokenDAO = new IdTokenDAOImpl(super.session_id);
        
        IdToken idToken = idTokenDAO.findByValue(request.getIdTag());
        
        if(idToken == null) {
            response.setStatus(new AuthorizationStatus(TOKEN_STATUS.Invalid.toString()));
        } else {
            GregorianCalendar expiryDate = new GregorianCalendar();
            if(idToken.getExpiryDate() != null) {
                expiryDate.setTime(idToken.getExpiryDate());
            }
            response.setExpiryDate(expiryDate);
            if(idToken.getParentIdTag() != null) {
                response.setParentIdTag(idToken.getParentIdTag().getValue());
            }
            response.setStatus(new AuthorizationStatus(idToken.getStatus()));
        }
        
        AuthorizeLog authorizeLog = new AuthorizeLog();
        authorizeLog.setChargeBoxSerialNumber(super.serial_number);
        authorizeLog.setIdTag(request.getIdTag());
        authorizeLog.setStatus(response.getStatus().getValue());
        authorizeLog.setTimestamp(new Date());
        
        AuthorizeLogDAO authorizeLogDAO = new AuthorizeLogDAOImpl();
        authorizeLogDAO.persist(authorizeLog);
        
        return response;
        
    }
    
}
