/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.offnet.ocpp.controller;

import com.offnet.ocpp.dao.IdTokenDAO;
import com.offnet.ocpp.dao.IdTokenDAOImpl;
import com.offnet.ocpp.entity.IdToken;
import com.offnet.ocpp.entity.Users;
import com.offnet.ocpp.general.Constants_Business;
import com.offnet.ocpp.general.Utilities_Business;
import com.offnet.ocpp.middleware.EMF;
import com.offnet.ocpp.request.AddNewIdTokenRequest;
import com.offnet.ocpp.response.AddNewIdTokenResponse;
import com.offnet.ocpp.response.Response;
import java.util.GregorianCalendar;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author gabi
 */
public class AddNewIdToken extends Controller{

    public AddNewIdToken(List<String> array, String session_id) {
        super(array, session_id);
    }
    
    @Override
    public Response processRequest() {
        AddNewIdTokenRequest request = new AddNewIdTokenRequest(Utilities_Business.fromStringToJsonObject(super.content));
        EntityManager em = EMF.createEntityManager();
        IdToken idToken = new IdToken();
        Users account = em.find(Users.class, 1);
        
        idToken.setUserId(account);
        idToken.setValue(request.getIdTag());
        idToken.setStatus(Constants_Business.TOKEN_STATUS.Accepted.toString());
        
        GregorianCalendar cal = (GregorianCalendar) GregorianCalendar.getInstance();
        cal.add(GregorianCalendar.MONTH, 1);
         
        idToken.setExpiryDate(cal.getTime());
        
        IdTokenDAO idTokenDAO = new IdTokenDAOImpl(super.session_id);
         
        boolean ok = idTokenDAO.persist(idToken);
        
        AddNewIdTokenResponse response = new AddNewIdTokenResponse();
        response.setStatus(ok);
        
        return response;
        
    }
    
}
