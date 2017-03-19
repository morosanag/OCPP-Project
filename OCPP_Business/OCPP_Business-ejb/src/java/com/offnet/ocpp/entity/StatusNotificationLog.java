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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author gabi
 */
@Entity
@Table(name = "StatusNotificationLog")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "StatusNotificationLog.findAll", query = "SELECT s FROM StatusNotificationLog s"),
    @NamedQuery(name = "StatusNotificationLog.findByChargePointSerialNumber", query = "SELECT s FROM StatusNotificationLog s WHERE s.chargePointSerialNumber = :chargePointSerialNumber"),
    @NamedQuery(name = "StatusNotificationLog.findByTimestamp", query = "SELECT s FROM StatusNotificationLog s WHERE s.timestamp = :timestamp"),
    @NamedQuery(name = "StatusNotificationLog.findByConnectorId", query = "SELECT s FROM StatusNotificationLog s WHERE s.connectorId = :connectorId"),
    @NamedQuery(name = "StatusNotificationLog.findByStatus", query = "SELECT s FROM StatusNotificationLog s WHERE s.status = :status"),
    @NamedQuery(name = "StatusNotificationLog.findByErrorCode", query = "SELECT s FROM StatusNotificationLog s WHERE s.errorCode = :errorCode"),
    @NamedQuery(name = "StatusNotificationLog.findByInfo", query = "SELECT s FROM StatusNotificationLog s WHERE s.info = :info"),
    @NamedQuery(name = "StatusNotificationLog.findById", query = "SELECT s FROM StatusNotificationLog s WHERE s.id = :id")})
public class StatusNotificationLog implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "chargePointSerialNumber")
    private String chargePointSerialNumber;
    @Basic(optional = false)
    @NotNull
    @Column(name = "timestamp")
    @Temporal(TemporalType.TIMESTAMP)
    private Date timestamp;
    @Basic(optional = false)
    @NotNull
    @Column(name = "connectorId")
    private int connectorId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 11)
    @Column(name = "status")
    private String status;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 21)
    @Column(name = "errorCode")
    private String errorCode;
    @Size(max = 100)
    @Column(name = "info")
    private String info;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;

    public StatusNotificationLog() {
    }

    public StatusNotificationLog(Integer id) {
        this.id = id;
    }

    public StatusNotificationLog(Integer id, String chargePointSerialNumber, Date timestamp, int connectorId, String status, String errorCode) {
        this.id = id;
        this.chargePointSerialNumber = chargePointSerialNumber;
        this.timestamp = timestamp;
        this.connectorId = connectorId;
        this.status = status;
        this.errorCode = errorCode;
    }

    public String getChargePointSerialNumber() {
        return chargePointSerialNumber;
    }

    public void setChargePointSerialNumber(String chargePointSerialNumber) {
        this.chargePointSerialNumber = chargePointSerialNumber;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public int getConnectorId() {
        return connectorId;
    }

    public void setConnectorId(int connectorId) {
        this.connectorId = connectorId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
        if (!(object instanceof StatusNotificationLog)) {
            return false;
        }
        StatusNotificationLog other = (StatusNotificationLog) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.offnet.ocpp.entity.StatusNotificationLog[ id=" + id + " ]";
    }
    
}
