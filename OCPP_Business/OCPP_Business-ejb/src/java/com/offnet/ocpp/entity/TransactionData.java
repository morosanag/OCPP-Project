/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.offnet.ocpp.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author gabi
 */
@Entity
@Table(name = "TransactionData")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TransactionData.findAll", query = "SELECT t FROM TransactionData t"),
    @NamedQuery(name = "TransactionData.findById", query = "SELECT t FROM TransactionData t WHERE t.id = :id"),
    @NamedQuery(name = "TransactionData.findByValue", query = "SELECT t FROM TransactionData t WHERE t.value = :value"),
    @NamedQuery(name = "TransactionData.findByTime", query = "SELECT t FROM TransactionData t WHERE t.time = :time"),
    @NamedQuery(name = "TransactionData.findByContext", query = "SELECT t FROM TransactionData t WHERE t.context = :context"),
    @NamedQuery(name = "TransactionData.findByFormat", query = "SELECT t FROM TransactionData t WHERE t.format = :format"),
    @NamedQuery(name = "TransactionData.findByMeasurand", query = "SELECT t FROM TransactionData t WHERE t.measurand = :measurand"),
    @NamedQuery(name = "TransactionData.findByLocation", query = "SELECT t FROM TransactionData t WHERE t.location = :location"),
    @NamedQuery(name = "TransactionData.findByUnit", query = "SELECT t FROM TransactionData t WHERE t.unit = :unit")})
public class TransactionData implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "value")
    private String value;
    @Basic(optional = false)
    @Column(name = "time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date time;
    @Column(name = "context")
    private String context;
    @Column(name = "format")
    private String format;
    @Column(name = "measurand")
    private String measurand;
    @Column(name = "location")
    private String location;
    @Column(name = "unit")
    private String unit;
    @JoinColumn(name = "idToken", referencedColumnName = "value")
    @ManyToOne
    private IdToken idToken;
    @JoinColumn(name = "transactionId", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Transaction transactionId;

    public TransactionData() {
    }

    public TransactionData(Integer id) {
        this.id = id;
    }

    public TransactionData(Integer id, String value, Date time) {
        this.id = id;
        this.value = value;
        this.time = time;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getMeasurand() {
        return measurand;
    }

    public void setMeasurand(String measurand) {
        this.measurand = measurand;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public IdToken getIdToken() {
        return idToken;
    }

    public void setIdToken(IdToken idToken) {
        this.idToken = idToken;
    }

    public Transaction getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Transaction transactionId) {
        this.transactionId = transactionId;
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
        if (!(object instanceof TransactionData)) {
            return false;
        }
        TransactionData other = (TransactionData) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "TransactionData{" + "id=" + id + ", value=" + value + ", time=" + time + ", context=" + context + ", format=" + format + ", measurand=" + measurand + ", location=" + location + ", unit=" + unit + ", idToken=" + idToken + ", transactionId=" + transactionId + '}';
    }

    
    
}
