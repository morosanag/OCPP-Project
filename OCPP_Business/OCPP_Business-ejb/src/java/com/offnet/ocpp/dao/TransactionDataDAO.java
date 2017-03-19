/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.offnet.ocpp.dao;

import com.google.gson.JsonObject;
import com.offnet.ocpp.entity.TransactionData;
import java.util.List;

/**
 *
 * @author gabi
 */
public interface TransactionDataDAO extends ObjectDAO<TransactionData, String>{
    
    public boolean persist_bulk(List<TransactionData> entity);
    
    public TransactionData createTransaction(JsonObject json, String cal);
}
