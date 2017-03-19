/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.offnet.ocpp.controller;

import com.offnet.ocpp.dao.TransactionDAO;
import com.offnet.ocpp.dao.TransactionDAOImpl;
import com.offnet.ocpp.dao.TransactionDataDAO;
import com.offnet.ocpp.dao.TransactionDataDAOImpl;
import com.offnet.ocpp.entity.Transaction;
import com.offnet.ocpp.entity.TransactionData;
import com.offnet.ocpp.general.Utilities_Business;
import com.offnet.ocpp.request.MeterValuesRequest;
import com.offnet.ocpp.response.MeterValuesResponse;
import com.offnet.ocpp.response.Response;
import java.util.List;

/**
 *
 * @author gabi
 */
public class MeterValuesController extends Controller {

    public MeterValuesController(List<String> array, String session_id) {
        super(array, session_id);
    }

    @Override
    public Response processRequest() {
        
        
        MeterValuesRequest request = new MeterValuesRequest(Utilities_Business.fromStringToJsonObject(super.content));
        MeterValuesResponse response = new MeterValuesResponse();
        
        // get TransactionId of the last unfinished transaction based on connectorId and ChargePointSerial
        TransactionDAO transactionDAO = new TransactionDAOImpl(session_id);
        Integer transactionId = null;
        transactionId = transactionDAO.getLastTransactionId(request.getConnectorId(), getSerial_number());
        if(transactionId == null) {
            return response;
        }
        request.setTransactionId(transactionId);
        saveTransactionDataList(request);
        return response;
    }
    
    public boolean saveTransactionDataList(MeterValuesRequest request) {
    
        for(TransactionData transactionData : request.getTransactionData()) {
            transactionData.setTransactionId(new Transaction(request.getTransactionId()));
        }
        
        TransactionDataDAO transactionDataDAO = new TransactionDataDAOImpl(super.session_id);
        return transactionDataDAO.persist_bulk(request.getTransactionData());
    }
    
    
    
}
