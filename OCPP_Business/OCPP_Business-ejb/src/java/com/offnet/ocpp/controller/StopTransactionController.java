/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.offnet.ocpp.controller;

import com.offnet.ocpp.dao.ConnectorDAO;
import com.offnet.ocpp.dao.ConnectorDAOImpl;
import com.offnet.ocpp.dao.IdTokenDAO;
import com.offnet.ocpp.dao.IdTokenDAOImpl;
import com.offnet.ocpp.dao.TransactionDAO;
import com.offnet.ocpp.dao.TransactionDAOImpl;
import com.offnet.ocpp.dao.TransactionDataDAO;
import com.offnet.ocpp.dao.TransactionDataDAOImpl;
import com.offnet.ocpp.entity.AuthorizationStatus;
import com.offnet.ocpp.entity.Connector;
import com.offnet.ocpp.entity.IdToken;
import com.offnet.ocpp.entity.Transaction;
import com.offnet.ocpp.entity.TransactionData;
import com.offnet.ocpp.general.Constants;
import com.offnet.ocpp.general.Constants.TOKEN_STATUS;
import com.offnet.ocpp.general.Constants_Business;
import static com.offnet.ocpp.general.Constants_Business.INVALID_ID_STR;
import com.offnet.ocpp.general.Utilities_Business;
import static com.offnet.ocpp.general.Utilities_Business.fromDateToCalendar;
import com.offnet.ocpp.request.IdTagInfo;
import com.offnet.ocpp.request.StopTransactionRequest;
import com.offnet.ocpp.response.Response;
import com.offnet.ocpp.response.StopTransactionResponse;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
/**
 *
 * @author gabi
 */
public class StopTransactionController extends Controller {

    public StopTransactionController(List<String> array, String session_id) {
        super(array, session_id);
    }

    @Override
    public Response processRequest() {
        
        StopTransactionRequest request = new StopTransactionRequest(Utilities_Business.fromStringToJsonObject(super.content));
        StopTransactionResponse response = new StopTransactionResponse();
        
        // special case when transactionId = -1
        if(request.getTransactionId() == -1) {
            IdTagInfo tagInfo = new IdTagInfo();
            
            tagInfo.setStatus(new AuthorizationStatus(TOKEN_STATUS.Accepted.toString()));
            
            GregorianCalendar now = (GregorianCalendar) GregorianCalendar.getInstance();
            tagInfo.setExpiryDate(now);           
            
            response.setIdTagInfo(tagInfo);
            
            return response;
        }
        
        IdTokenDAO idTokenDAO = new IdTokenDAOImpl(super.session_id);
        
        TransactionDAO transactionDAO = new TransactionDAOImpl(session_id);
        Transaction transaction1 = transactionDAO.findById(request.getTransactionId() + "");
        
        IdToken idToken = idTokenDAO.findByValue(request.getIdTag());
        IdTagInfo tagInfo = new IdTagInfo();
        
        if(transaction1 != null) {
        
            // check if the idToken that tries to stop transaction is the same with the one 
            // which started transaction
            if(request.getIdTag() != null && transaction1 != null) {

                if(!transaction1.getIdToken().getValue().equals(request.getIdTag())) {
                    idToken = null; // in order to enter in the invalid case (below)
                    tagInfo.setStatus(new AuthorizationStatus(Constants_Business.TOKEN_STATUS.Invalid.toString()));
                } else {
                    boolean saveTransaction = saveTransaction(request, transaction1.getMeterStart());

                    if( saveTransaction) {
                        saveTransactionDataList(request);
                    }
                    
                }
            } else if(transaction1 != null) {
                request.setIdTag(transaction1.getIdToken().getValue());
                boolean saveTransaction = saveTransaction(request, transaction1.getMeterStart());
                tagInfo.setStatus(new AuthorizationStatus(Constants_Business.TOKEN_STATUS.Accepted.toString()));
                if( saveTransaction) {
                    saveTransactionDataList(request);
                }
                
            }
            
            // set Connector on Available
            ConnectorDAO connectorDAO = new ConnectorDAOImpl(session_id);
            Connector connector = new Connector();
            connector.setStatus("Available");
            connector.setInfo("");
            connector.setConnectorId(transaction1.getConnectorId());
            connectorDAO.update(connector);
        }
        
        if(idToken == null) {
            
            tagInfo.setParentIdTag(INVALID_ID_STR);
            GregorianCalendar now = (GregorianCalendar) GregorianCalendar.getInstance();
            tagInfo.setExpiryDate(now);
            response.setIdTagInfo(tagInfo);
            
        } else {
            
            tagInfo.setExpiryDate(fromDateToCalendar(idToken.getExpiryDate()));
            if(idToken.getParentIdTag() == null) {
                tagInfo.setParentIdTag(INVALID_ID_STR);
            }
            
            tagInfo.setStatus(new AuthorizationStatus(idToken.getStatus()));
            
            response.setIdTagInfo(tagInfo);
        }
        
        return response;
    }
    
    public boolean saveTransaction(StopTransactionRequest request, Integer meterStart) {
        Transaction transaction = new Transaction();
        
        transaction.setId(request.getTransactionId());
        transaction.setMeterStop(request.getMeterStop());
        
        transaction.setMeterDiff(request.getMeterStop() - meterStart);
        transaction.setEndTime(request.getTimestamp().getTime());
        
        TransactionDAO transactionDAO = new TransactionDAOImpl(super.session_id);
        
        return transactionDAO.update(transaction);
    }
    
    public boolean saveTransactionDataList(StopTransactionRequest request) {
    
        for(TransactionData transactionData : request.getTransactionData()) { 
            transactionData.setIdToken(new IdToken(request.getIdTag()));
            transactionData.setTransactionId(new Transaction(request.getTransactionId()));
        
        }
        
        TransactionDataDAO transactionDataDAO = new TransactionDataDAOImpl(super.session_id);
        return transactionDataDAO.persist_bulk(request.getTransactionData());
    }
    
}
