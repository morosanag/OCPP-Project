/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.offnet.ocpp.entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author gabi
 */
@Entity
@Table(name = "Transaction")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Transaction.findAll", query = "SELECT t FROM Transaction t"),
    @NamedQuery(name = "Transaction.findById", query = "SELECT t FROM Transaction t WHERE t.id = :id"),
    @NamedQuery(name = "Transaction.findByConnectorId", query = "SELECT t FROM Transaction t WHERE t.connectorId = :connectorId"),
    @NamedQuery(name = "Transaction.findByTime", query = "SELECT t FROM Transaction t WHERE t.time = :time"),
    @NamedQuery(name = "Transaction.findByMeterStart", query = "SELECT t FROM Transaction t WHERE t.meterStart = :meterStart"),
    @NamedQuery(name = "Transaction.findByMeterStop", query = "SELECT t FROM Transaction t WHERE t.meterStop = :meterStop")})
public class Transaction implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "transactionId")
    private Collection<TransactionData> transactionDataCollection;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "connectorId")
    private int connectorId;
    @Column(name = "time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date time;
    @Column(name = "endTime")
    @Temporal(TemporalType.TIMESTAMP)
    private Date endTime;
    @Basic(optional = false)
    @Column(name = "meterStart")
    private int meterStart;
    @Basic(optional = false)
    @Column(name = "meterStop")
    private int meterStop;
    @Basic(optional = false)
    @Column(name = "meterDiff")
    private int meterDiff;
    @JoinColumn(name = "idToken", referencedColumnName = "value")
    @ManyToOne(optional = false)
    private IdToken idToken;
    @JoinColumn(name = "reservationId", referencedColumnName = "id")
    @ManyToOne
    private Reservation reservationId;

    public Transaction() {
    }

    public Transaction(Integer id) {
        this.id = id;
    }

    public Transaction(Integer id, int connectorId, int meterStart, int meterStop) {
        this.id = id;
        this.connectorId = connectorId;
        this.meterStart = meterStart;
        this.meterStop = meterStop;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getConnectorId() {
        return connectorId;
    }

    public void setConnectorId(int connectorId) {
        this.connectorId = connectorId;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public int getMeterStart() {
        return meterStart;
    }

    public void setMeterStart(int meterStart) {
        this.meterStart = meterStart;
    }

    public int getMeterStop() {
        return meterStop;
    }

    public void setMeterStop(int meterStop) {
        this.meterStop = meterStop;
    }

    public IdToken getIdToken() {
        return idToken;
    }

    public void setIdToken(IdToken idToken) {
        this.idToken = idToken;
    }

    public Reservation getReservationId() {
        return reservationId;
    }

    public void setReservationId(Reservation reservationId) {
        this.reservationId = reservationId;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public int getMeterDiff() {
        return meterDiff;
    }

    public void setMeterDiff(int meterDiff) {
        this.meterDiff = meterDiff;
    }
   
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Transaction)) {
            return false;
        }
        Transaction other = (Transaction) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.offnet.ocpp.entity.Transaction[ id=" + id + " ]";
    }

    @XmlTransient
    public Collection<TransactionData> getTransactionDataCollection() {
        return transactionDataCollection;
    }

    public void setTransactionDataCollection(Collection<TransactionData> transactionDataCollection) {
        this.transactionDataCollection = transactionDataCollection;
    }
    
}
