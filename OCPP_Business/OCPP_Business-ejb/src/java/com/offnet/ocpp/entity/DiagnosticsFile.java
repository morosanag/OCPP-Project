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
@Table(name = "DiagnosticsFile")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DiagnosticsFile.findAll", query = "SELECT d FROM DiagnosticsFile d"),
    @NamedQuery(name = "DiagnosticsFile.findByDiagnosticsFileId", query = "SELECT d FROM DiagnosticsFile d WHERE d.diagnosticsFileId = :diagnosticsFileId"),
    @NamedQuery(name = "DiagnosticsFile.findByLocation", query = "SELECT d FROM DiagnosticsFile d WHERE d.location = :location"),
    @NamedQuery(name = "DiagnosticsFile.findByFilename", query = "SELECT d FROM DiagnosticsFile d WHERE d.filename = :filename"),
    @NamedQuery(name = "DiagnosticsFile.findByStartTime", query = "SELECT d FROM DiagnosticsFile d WHERE d.startTime = :startTime"),
    @NamedQuery(name = "DiagnosticsFile.findByStopTime", query = "SELECT d FROM DiagnosticsFile d WHERE d.stopTime = :stopTime"),
    @NamedQuery(name = "DiagnosticsFile.findByRetries", query = "SELECT d FROM DiagnosticsFile d WHERE d.retries = :retries"),
    @NamedQuery(name = "DiagnosticsFile.findByRetryInterval", query = "SELECT d FROM DiagnosticsFile d WHERE d.retryInterval = :retryInterval"),
    @NamedQuery(name = "DiagnosticsFile.findByStatus", query = "SELECT d FROM DiagnosticsFile d WHERE d.status = :status")})
public class DiagnosticsFile implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "diagnosticsFileId")
    private Integer diagnosticsFileId;
    @Basic(optional = false)
    @Column(name = "location")
    private String location;
    @Column(name = "filename")
    private String filename;
    @Basic(optional = false)
    @Column(name = "startTime")
    @Temporal(TemporalType.TIMESTAMP)
    private Date startTime;
    @Basic(optional = false)
    @Column(name = "stopTime")
    @Temporal(TemporalType.TIMESTAMP)
    private Date stopTime;
    @Column(name = "retries")
    private Integer retries;
    @Column(name = "retryInterval")
    private Integer retryInterval;
    @Basic(optional = false)
    @Column(name = "status")
    private String status;
    @JoinColumn(name = "chargeBoxSerialNumber", referencedColumnName = "chargeBoxSerialNumber")
    @ManyToOne(optional = false)
    private ChargePoint chargeBoxSerialNumber;

    public DiagnosticsFile() {
    }

    public DiagnosticsFile(Integer diagnosticsFileId) {
        this.diagnosticsFileId = diagnosticsFileId;
    }

    public DiagnosticsFile(Integer diagnosticsFileId, String location, Date startTime, Date stopTime, String status) {
        this.diagnosticsFileId = diagnosticsFileId;
        this.location = location;
        this.startTime = startTime;
        this.stopTime = stopTime;
        this.status = status;
    }

    public Integer getDiagnosticsFileId() {
        return diagnosticsFileId;
    }

    public void setDiagnosticsFileId(Integer diagnosticsFileId) {
        this.diagnosticsFileId = diagnosticsFileId;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getStopTime() {
        return stopTime;
    }

    public void setStopTime(Date stopTime) {
        this.stopTime = stopTime;
    }

    public Integer getRetries() {
        return retries;
    }

    public void setRetries(Integer retries) {
        this.retries = retries;
    }

    public Integer getRetryInterval() {
        return retryInterval;
    }

    public void setRetryInterval(Integer retryInterval) {
        this.retryInterval = retryInterval;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
        hash += (diagnosticsFileId != null ? diagnosticsFileId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DiagnosticsFile)) {
            return false;
        }
        DiagnosticsFile other = (DiagnosticsFile) object;
        if ((this.diagnosticsFileId == null && other.diagnosticsFileId != null) || (this.diagnosticsFileId != null && !this.diagnosticsFileId.equals(other.diagnosticsFileId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DiagnosticsFile{" + "diagnosticsFileId=" + diagnosticsFileId + ", location=" + location + ", filename=" + filename + ", startTime=" + startTime + ", stopTime=" + stopTime + ", retries=" + retries + ", retryInterval=" + retryInterval + ", status=" + status + ", chargeBoxSerialNumber=" + chargeBoxSerialNumber + '}';
    }
    
}
