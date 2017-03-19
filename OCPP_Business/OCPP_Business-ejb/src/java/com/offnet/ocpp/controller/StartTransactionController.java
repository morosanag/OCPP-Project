/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.offnet.ocpp.controller;

import com.offnet.ocpp.dao.ChargePointDAO;
import com.offnet.ocpp.dao.ChargePointDAOImpl;
import com.offnet.ocpp.dao.ConnectorDAO;
import com.offnet.ocpp.dao.ConnectorDAOImpl;
import com.offnet.ocpp.dao.IdTokenDAO;
import com.offnet.ocpp.dao.IdTokenDAOImpl;
import com.offnet.ocpp.dao.TransactionDAO;
import com.offnet.ocpp.dao.TransactionDAOImpl;
import com.offnet.ocpp.entity.AuthorizationStatus;
import com.offnet.ocpp.entity.ChargePoint;
import com.offnet.ocpp.entity.Connector;
import com.offnet.ocpp.entity.IdToken;
import com.offnet.ocpp.entity.Reservation;
import com.offnet.ocpp.entity.Transaction;
import com.offnet.ocpp.request.IdTagInfo;
import com.offnet.ocpp.request.StartTransactionRequest;
import com.offnet.ocpp.response.Response;
import com.offnet.ocpp.response.StartTransactionResponse;
import java.util.GregorianCalendar;
import java.util.List;

import static com.offnet.ocpp.general.Constants_Business.TOKEN_STATUS;
import static com.offnet.ocpp.general.Constants_Business.INVALID_ID;
import static com.offnet.ocpp.general.Constants_Business.INVALID_ID_STR;
import com.offnet.ocpp.general.Utilities_Business;
import static com.offnet.ocpp.general.Utilities_Business.fromDateToCalendar;
import java.util.Date;

/**
 *
 * @author gabi
 */
public class StartTransactionController extends Controller{

    public StartTransactionController(List<String> array, String session_id) {
        super(array, session_id);
    }

    @Override
    public Response processRequest() {
        StartTransactionRequest request = new StartTransactionRequest(Utilities_Business.fromStringToJsonObject(super.content));
        
        StartTransactionResponse response = new StartTransactionResponse();
        IdTokenDAO idTokenDAO = new IdTokenDAOImpl(super.session_id);
        TransactionDAO transactionDAO = new TransactionDAOImpl(super.session_id);
        
        IdToken idToken = idTokenDAO.findByValue(request.getIdTag());
        IdTagInfo tagInfo = new IdTagInfo();
        if(idToken == null) {
            
            response.setTransactionId(INVALID_ID);
            tagInfo.setStatus(new AuthorizationStatus(TOKEN_STATUS.Invalid.toString()));
            tagInfo.setParentIdTag(INVALID_ID_STR);
            GregorianCalendar now = (GregorianCalendar) GregorianCalendar.getInstance();
            tagInfo.setExpiryDate(now);
            response.setIdTagInfo(tagInfo);
            
        } else {
            
            tagInfo.setExpiryDate(fromDateToCalendar(idToken.getExpiryDate()));
            if(idToken.getParentIdTag() == null) {
                tagInfo.setParentIdTag(INVALID_ID_STR);
            }
            
            if(idToken.getStatus().equalsIgnoreCase(TOKEN_STATUS.Blocked.toString())) {
                
                response.setTransactionId(INVALID_ID);
                tagInfo.setStatus(new AuthorizationStatus(TOKEN_STATUS.Blocked.toString()));
                        
            } else if(idToken.getStatus().equalsIgnoreCase(TOKEN_STATUS.Expired.toString())) {
            
                response.setTransactionId(INVALID_ID);
                tagInfo.setStatus(new AuthorizationStatus(TOKEN_STATUS.Expired.toString()));
            
            } else {
            
                boolean checkConcurrent = transactionDAO.checkTransaction(request, this.getSerial_number());
                if(!checkConcurrent) {
                
                    response.setTransactionId(INVALID_ID);
                    tagInfo.setStatus(new AuthorizationStatus(TOKEN_STATUS.ConcurrentTx.toString()));
                
                } else {
                
                    Transaction transaction = createTransaction(request);
                    Integer transaction_id = transactionDAO.persist_ind(transaction);
                    if(transaction_id != -1) {
                        
                        // set connector on Charging status
                        ConnectorDAO connectorDAO = new ConnectorDAOImpl(session_id);
                        Connector connector = new Connector();
                        connector.setStatus("Occupied");
                        connector.setInfo("Charging");
                        connector.setConnectorId(transaction.getConnectorId());
                        connectorDAO.update(connector);
                        
                        response.setTransactionId(transaction_id);
                        tagInfo.setStatus(new AuthorizationStatus(TOKEN_STATUS.Accepted.toString()));
                    
                    } else {
                    
                        response.setTransactionId(INVALID_ID);
                        tagInfo.setStatus(new AuthorizationStatus(TOKEN_STATUS.Invalid.toString()));

                    }
                }
            }
            response.setIdTagInfo(tagInfo);
        }
        
        return response;
    }

    private Transaction createTransaction(StartTransactionRequest request) {
        
        Transaction transaction = new Transaction();
        
        ChargePointDAO chargePointDAO = new ChargePointDAOImpl(session_id);
        ChargePoint chargePoint = chargePointDAO.findById(this.getSerial_number());
        
        ConnectorDAO connectorDAO = new ConnectorDAOImpl(session_id);
        Connector connector = connectorDAO.findBySerial(request.getConnectorId(), chargePoint);
        Connector connectorToBeAdded = null;
        // if there was no StatusRequest for that connector before
        if(connector == null) {
            connectorToBeAdded = new Connector();
            connectorToBeAdded.setChargeBoxSerialNumber(chargePoint);
            connectorToBeAdded.setConnectorSerial(request.getConnectorId());
            connectorToBeAdded.setErrorCode("NoError");
            connectorToBeAdded.setStatus("Available");
            connectorToBeAdded.setTime(new Date());
            connectorDAO.persist(connectorToBeAdded);
        }
        
        transaction.setId(-1);
        transaction.setConnectorId(connector == null ? connectorToBeAdded.getConnectorId() : connector.getConnectorId());
        transaction.setIdToken(new IdToken(request.getIdTag()));
        transaction.setMeterStart(request.getMeterStart());
        transaction.setMeterStop(0);
        
        if(request.getReservationId() != null && request.getReservationId() != 0) {
            transaction.setReservationId(new Reservation(request.getReservationId()));
        }
        
        transaction.setTime(request.getTimestamp().getTime());
        
        return transaction;
        
    }
    
}
