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
@Table(name = "Connector")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Connector.findAll", query = "SELECT c FROM Connector c"),
    @NamedQuery(name = "Connector.findByConnectorId", query = "SELECT c FROM Connector c WHERE c.connectorId = :connectorId"),
    @NamedQuery(name = "Connector.findByConnectorSerial", query = "SELECT c FROM Connector c WHERE c.connectorSerial = :connectorSerial"),
    @NamedQuery(name = "Connector.findByStatus", query = "SELECT c FROM Connector c WHERE c.status = :status"),
    @NamedQuery(name = "Connector.findByErrorCode", query = "SELECT c FROM Connector c WHERE c.errorCode = :errorCode"),
    @NamedQuery(name = "Connector.findByInfo", query = "SELECT c FROM Connector c WHERE c.info = :info"),
    @NamedQuery(name = "Connector.findByTime", query = "SELECT c FROM Connector c WHERE c.time = :time"),
    @NamedQuery(name = "Connector.findByVendorId", query = "SELECT c FROM Connector c WHERE c.vendorId = :vendorId"),
    @NamedQuery(name = "Connector.findByVendorErrorCode", query = "SELECT c FROM Connector c WHERE c.vendorErrorCode = :vendorErrorCode")})
public class Connector implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "connectorId")
    private Integer connectorId;
    @Basic(optional = false)
    @Column(name = "connectorSerial")
    private int connectorSerial;
    @Column(name = "status")
    private String status;
    @Column(name = "errorCode")
    private String errorCode;
    @Column(name = "info")
    private String info;
    @Basic(optional = false)
    @Column(name = "time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date time;
    @Column(name = "vendorId")
    private String vendorId;
    @Column(name = "vendorErrorCode")
    private String vendorErrorCode;
    @JoinColumn(name = "chargeBoxSerialNumber", referencedColumnName = "chargeBoxSerialNumber")
    @ManyToOne(optional = false)
    private ChargePoint chargeBoxSerialNumber;

    public Connector() {
    }

    public Connector(Integer connectorId) {
        this.connectorId = connectorId;
    }

    public Connector(Integer connectorId, int connectorSerial, Date time) {
        this.connectorId = connectorId;
        this.connectorSerial = connectorSerial;
        this.time = time;
    }

    public Integer getConnectorId() {
        return connectorId;
    }

    public void setConnectorId(Integer connectorId) {
        this.connectorId = connectorId;
    }

    public int getConnectorSerial() {
        return connectorSerial;
    }

    public void setConnectorSerial(int connectorSerial) {
        this.connectorSerial = connectorSerial;
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

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getVendorId() {
        return vendorId;
    }

    public void setVendorId(String vendorId) {
        this.vendorId = vendorId;
    }

    public String getVendorErrorCode() {
        return vendorErrorCode;
    }

    public void setVendorErrorCode(String vendorErrorCode) {
        this.vendorErrorCode = vendorErrorCode;
    }

    public ChargePoint getChargeBoxSerialNumber() {
        return chargeBoxSerialNumber;
    }

    public void setChargeBoxSerialNumber(ChargePoint chargeBoxSerialNumber) {
        this.chargeBoxSerialNumber = chargeBoxSerialNumber;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (connectorId != null ? connectorId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Connector)) {
            return false;
        }
        Connector other = (Connector) object;
        if ((this.connectorId == null && other.connectorId != null) || (this.connectorId != null && !this.connectorId.equals(other.connectorId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Connector{" + "connectorId=" + connectorId + ", connectorSerial=" + connectorSerial + ", status=" + status + ", errorCode=" + errorCode + ", info=" + info + ", time=" + time + ", vendorId=" + vendorId + ", vendorErrorCode=" + vendorErrorCode + ", chargeBoxSerialNumber=" + chargeBoxSerialNumber + '}';
    }

    
    
    
}
