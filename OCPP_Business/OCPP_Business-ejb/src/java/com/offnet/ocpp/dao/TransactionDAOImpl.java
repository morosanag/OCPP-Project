/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.offnet.ocpp.dao;

import com.offnet.ocpp.entity.ChargePoint;
import com.offnet.ocpp.entity.Connector;
import com.offnet.ocpp.entity.Transaction;
import com.offnet.ocpp.middleware.EMF;
import com.offnet.ocpp.request.StartTransactionRequest;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

/**
 *
 * @author gabi
 */
public class TransactionDAOImpl implements TransactionDAO{

    private final String session_id;

    public TransactionDAOImpl(String session_id) {
        this.session_id = session_id;
    }
        
    @Override
    public boolean persist(Transaction entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates. 
    }

    @Override
    public boolean update(Transaction entity) {
        boolean ok = true;
        EntityManager em = EMF.createEntityManager();
        Transaction transaction_2 = em.find(Transaction.class, entity.getId());
        EntityTransaction etx = em.getTransaction();
        etx.begin();
        try {
            if(transaction_2 == null) {
            ok = false;
            } else {
                if(transaction_2.getMeterStop() == 0) {
                    transaction_2.setMeterStop(entity.getMeterStop());
                    transaction_2.setMeterDiff(entity.getMeterDiff());
                    transaction_2.setEndTime(entity.getEndTime());
                } else {
                    ok = false;
                }
            }
            etx.commit();
        } catch (Exception e) {
            etx.rollback();
            ok = false;
        } finally {
            em.close();
            return ok;
        }
    
    }

    @Override
    public Transaction findById(String id) {
        
        EntityManager em = EMF.createEntityManager();
        
        Integer id_Int = Integer.parseInt(id);
        
        List<Transaction> depts = em.createNamedQuery("Transaction.findById").setParameter("id", id_Int).getResultList();
        if(depts == null || depts.isEmpty()) {
            return null;
        }
  
        em.close();
        
        return depts.get(0);
    }

    @Override
    public void delete(Transaction entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Transaction> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean checkTransaction(StartTransactionRequest request, String chargeBoxSerialNumber) {
        EntityManager em = EMF.createEntityManager();

        ChargePointDAO chargePointDAO = new ChargePointDAOImpl(session_id);
        ChargePoint chargePoint = chargePointDAO.findById(chargeBoxSerialNumber);
        
        ConnectorDAO connectorDAO = new ConnectorDAOImpl(session_id);
        Connector connector = connectorDAO.findBySerial(request.getConnectorId(), chargePoint);
        
        
        if(connector == null) {
            em.close();
            return true;
        }
        
        List<Transaction> transactions = em.createQuery(Transaction_checkTransaction)
                .setParameter("connectorId", connector.getConnectorId())
                .getResultList();  
        
        em.close();
        return transactions == null || transactions.isEmpty();
    }

    @Override
    public Integer persist_ind(Transaction entity) {
        EntityManager em = EMF.createEntityManager();
        Transaction transaction_2 = em.find(Transaction.class, entity.getId());
        EntityTransaction etx = em.getTransaction();
        etx.begin();
        try {
            if(transaction_2 == null) {
                // create
                entity.setId(null);
                em.persist(entity);
            } else {
                
                transaction_2.setMeterStop(entity.getMeterStop());
            }
            etx.commit();
            
        } catch (Exception e) {
            etx.rollback();
            e.printStackTrace();
            return -1;
        } finally {
            em.close();
            return entity.getId();
        }
        
    }

    @Override
    public Integer getLastTransactionId(Integer connectorId, String chargeBoxSerialNumber) {
        
        ChargePointDAO chargePointDAO = new ChargePointDAOImpl(session_id);
        ChargePoint chargePoint = chargePointDAO.findById(chargeBoxSerialNumber);
        ConnectorDAO connectorDAO = new ConnectorDAOImpl(session_id);
        Connector connector = connectorDAO.findBySerial(connectorId, chargePoint);
        EntityManager em = EMF.createEntityManager();
        
        Query query = em.createQuery( "SELECT c FROM Transaction c WHERE c.connectorId = ?1 and c.meterStop = 0" );
        query.setParameter( 1, connector.getConnectorId());
        
        List<Transaction> depts = query.getResultList();
        
        em.close();
        
        if(depts == null || depts.isEmpty()) {
            return null;
        }
        
        return depts.get(0).getId();
    
    }
    
}
