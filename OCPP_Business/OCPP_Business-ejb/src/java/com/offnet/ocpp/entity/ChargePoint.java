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
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author gabi
 */
@Entity
@Table(name = "ChargePoint")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ChargePoint.findAll", query = "SELECT c FROM ChargePoint c"),
    @NamedQuery(name = "ChargePoint.findByChargeBoxSerialNumber", query = "SELECT c FROM ChargePoint c WHERE c.chargeBoxSerialNumber = :chargeBoxSerialNumber"),
    @NamedQuery(name = "ChargePoint.findByChargePointSerialNumber", query = "SELECT c FROM ChargePoint c WHERE c.chargePointSerialNumber = :chargePointSerialNumber"),
    @NamedQuery(name = "ChargePoint.findByChargePointVendor", query = "SELECT c FROM ChargePoint c WHERE c.chargePointVendor = :chargePointVendor"),
    @NamedQuery(name = "ChargePoint.findByChargePointModel", query = "SELECT c FROM ChargePoint c WHERE c.chargePointModel = :chargePointModel"),
    @NamedQuery(name = "ChargePoint.findByFirmwareVersion", query = "SELECT c FROM ChargePoint c WHERE c.firmwareVersion = :firmwareVersion"),
    @NamedQuery(name = "ChargePoint.findByIccid", query = "SELECT c FROM ChargePoint c WHERE c.iccid = :iccid"),
    @NamedQuery(name = "ChargePoint.findByImsi", query = "SELECT c FROM ChargePoint c WHERE c.imsi = :imsi"),
    @NamedQuery(name = "ChargePoint.findByMeterType", query = "SELECT c FROM ChargePoint c WHERE c.meterType = :meterType"),
    @NamedQuery(name = "ChargePoint.findByMeterSerialNumber", query = "SELECT c FROM ChargePoint c WHERE c.meterSerialNumber = :meterSerialNumber"),
    @NamedQuery(name = "ChargePoint.findByStatus", query = "SELECT c FROM ChargePoint c WHERE c.status = :status"),
    @NamedQuery(name = "ChargePoint.findBySessionId", query = "SELECT c FROM ChargePoint c WHERE c.sessionId = :sessionId"),
    @NamedQuery(name = "ChargePoint.findByLastUpdate", query = "SELECT c FROM ChargePoint c WHERE c.lastUpdate = :lastUpdate")})
public class ChargePoint implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "chargeBoxSerialNumber")
    private Collection<Firmware> firmwareCollection;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "chargeBoxSerialNumber")
    private String chargeBoxSerialNumber;
    @Size(max = 50)
    @Column(name = "chargePointSerialNumber")
    private String chargePointSerialNumber;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "chargePointVendor")
    private String chargePointVendor;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "chargePointModel")
    private String chargePointModel;
    @Size(max = 50)
    @Column(name = "firmwareVersion")
    private String firmwareVersion;
    @Size(max = 50)
    @Column(name = "iccid")
    private String iccid;
    @Size(max = 50)
    @Column(name = "imsi")
    private String imsi;
    @Size(max = 50)
    @Column(name = "meterType")
    private String meterType;
    @Size(max = 50)
    @Column(name = "meterSerialNumber")
    private String meterSerialNumber;
    @Size(max = 11)
    @Column(name = "status")
    private String status;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "session_id")
    private String sessionId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "lastUpdate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdate;

    public ChargePoint() {
    }

    public ChargePoint(String chargeBoxSerialNumber) {
        this.chargeBoxSerialNumber = chargeBoxSerialNumber;
    }

    public ChargePoint(String chargeBoxSerialNumber, String chargePointVendor, String chargePointModel, String sessionId, Date lastUpdate) {
        this.chargeBoxSerialNumber = chargeBoxSerialNumber;
        this.chargePointVendor = chargePointVendor;
        this.chargePointModel = chargePointModel;
        this.sessionId = sessionId;
        this.lastUpdate = lastUpdate;
    }

    public String getChargeBoxSerialNumber() {
        return chargeBoxSerialNumber;
    }

    public void setChargeBoxSerialNumber(String chargeBoxSerialNumber) {
        this.chargeBoxSerialNumber = chargeBoxSerialNumber;
    }

    public String getChargePointSerialNumber() {
        return chargePointSerialNumber;
    }

    public void setChargePointSerialNumber(String chargePointSerialNumber) {
        this.chargePointSerialNumber = chargePointSerialNumber;
    }

    public String getChargePointVendor() {
        return chargePointVendor;
    }

    public void setChargePointVendor(String chargePointVendor) {
        this.chargePointVendor = chargePointVendor;
    }

    public String getChargePointModel() {
        return chargePointModel;
    }

    public void setChargePointModel(String chargePointModel) {
        this.chargePointModel = chargePointModel;
    }

    public String getFirmwareVersion() {
        return firmwareVersion;
    }

    public void setFirmwareVersion(String firmwareVersion) {
        this.firmwareVersion = firmwareVersion;
    }

    public String getIccid() {
        return iccid;
    }

    public void setIccid(String iccid) {
        this.iccid = iccid;
    }

    public String getImsi() {
        return imsi;
    }

    public void setImsi(String imsi) {
        this.imsi = imsi;
    }

    public String getMeterType() {
        return meterType;
    }

    public void setMeterType(String meterType) {
        this.meterType = meterType;
    }

    public String getMeterSerialNumber() {
        return meterSerialNumber;
    }

    public void setMeterSerialNumber(String meterSerialNumber) {
        this.meterSerialNumber = meterSerialNumber;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (chargeBoxSerialNumber != null ? chargeBoxSerialNumber.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ChargePoint)) {
            return false;
        }
        ChargePoint other = (ChargePoint) object;
        if ((this.chargeBoxSerialNumber == null && other.chargeBoxSerialNumber != null) || (this.chargeBoxSerialNumber != null && !this.chargeBoxSerialNumber.equals(other.chargeBoxSerialNumber))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ChargePoint{" + "firmwareCollection=" + firmwareCollection + ", chargeBoxSerialNumber=" + chargeBoxSerialNumber + ", chargePointSerialNumber=" + chargePointSerialNumber + ", chargePointVendor=" + chargePointVendor + ", chargePointModel=" + chargePointModel + ", firmwareVersion=" + firmwareVersion + ", iccid=" + iccid + ", imsi=" + imsi + ", meterType=" + meterType + ", meterSerialNumber=" + meterSerialNumber + ", status=" + status + ", sessionId=" + sessionId + ", lastUpdate=" + lastUpdate + '}';
    }

    @XmlTransient
    public Collection<Firmware> getFirmwareCollection() {
        return firmwareCollection;
    }

    public void setFirmwareCollection(Collection<Firmware> firmwareCollection) {
        this.firmwareCollection = firmwareCollection;
    }
    
}
