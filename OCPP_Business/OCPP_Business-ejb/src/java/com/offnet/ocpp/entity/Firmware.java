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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author gabi
 */
@Entity
@Table(name = "Firmware")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Firmware.findAll", query = "SELECT f FROM Firmware f"),
    @NamedQuery(name = "Firmware.findById", query = "SELECT f FROM Firmware f WHERE f.id = :id"),
    @NamedQuery(name = "Firmware.findByStatus", query = "SELECT f FROM Firmware f WHERE f.status = :status"),
    @NamedQuery(name = "Firmware.findByRetrieveDate", query = "SELECT f FROM Firmware f WHERE f.retrieveDate = :retrieveDate"),
    @NamedQuery(name = "Firmware.findByLastUpdate", query = "SELECT f FROM Firmware f WHERE f.lastUpdate = :lastUpdate"),
    @NamedQuery(name = "Firmware.findByLocation", query = "SELECT f FROM Firmware f WHERE f.location = :location")})
public class Firmware implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 18)
    @Column(name = "status")
    private String status;
    @Basic(optional = false)
    @NotNull
    @Column(name = "retrieveDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date retrieveDate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "lastUpdate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdate;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "location")
    private String location;
    @JoinColumn(name = "chargeBoxSerialNumber", referencedColumnName = "chargeBoxSerialNumber")
    @ManyToOne(optional = false)
    private ChargePoint chargeBoxSerialNumber;

    public Firmware() {
    }

    public Firmware(Integer id) {
        this.id = id;
    }

    public Firmware(Integer id, Date retrieveDate, Date lastUpdate, String location) {
        this.id = id;
        this.retrieveDate = retrieveDate;
        this.lastUpdate = lastUpdate;
        this.location = location;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getRetrieveDate() {
        return retrieveDate;
    }

    public void setRetrieveDate(Date retrieveDate) {
        this.retrieveDate = retrieveDate;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
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
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Firmware)) {
            return false;
        }
        Firmware other = (Firmware) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Firmware{" + "id=" + id + ", status=" + status + ", retrieveDate=" + retrieveDate + ", lastUpdate=" + lastUpdate + ", location=" + location + ", chargeBoxSerialNumber=" + chargeBoxSerialNumber + '}';
    }

}
