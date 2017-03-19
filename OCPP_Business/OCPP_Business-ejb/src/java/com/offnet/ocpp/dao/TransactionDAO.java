/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.offnet.ocpp.dao;

import com.offnet.ocpp.entity.Transaction;
import com.offnet.ocpp.request.StartTransactionRequest;

/**
 *
 * @author gabi
 */
public interface TransactionDAO extends ObjectDAO<Transaction, String>{
    //"SELECT t FROM Transaction t"
    public String Transaction_checkTransaction = "SELECT t FROM Transaction t WHERE t.endTime is NULL AND t.connectorId = :connectorId";

    public boolean checkTransaction(StartTransactionRequest request, String chargeBoxSerialNumber);
    public Integer getLastTransactionId (Integer connectorId, String chargeBoxSerialNumber);
}
