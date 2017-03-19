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
@Table(name = "AuthorizeLog")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AuthorizeLog.findAll", query = "SELECT a FROM AuthorizeLog a"),
    @NamedQuery(name = "AuthorizeLog.findByChargeBoxSerialNumber", query = "SELECT a FROM AuthorizeLog a WHERE a.chargeBoxSerialNumber = :chargeBoxSerialNumber"),
    @NamedQuery(name = "AuthorizeLog.findByIdTag", query = "SELECT a FROM AuthorizeLog a WHERE a.idTag = :idTag"),
    @NamedQuery(name = "AuthorizeLog.findByStatus", query = "SELECT a FROM AuthorizeLog a WHERE a.status = :status"),
    @NamedQuery(name = "AuthorizeLog.findByTimestamp", query = "SELECT a FROM AuthorizeLog a WHERE a.timestamp = :timestamp"),
    @NamedQuery(name = "AuthorizeLog.findById", query = "SELECT a FROM AuthorizeLog a WHERE a.id = :id")})
public class AuthorizeLog implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "chargeBoxSerialNumber")
    private String chargeBoxSerialNumber;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "idTag")
    private String idTag;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 12)
    @Column(name = "status")
    private String status;
    @Basic(optional = false)
    @NotNull
    @Column(name = "timestamp")
    @Temporal(TemporalType.TIMESTAMP)
    private Date timestamp;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;

    public AuthorizeLog() {
    }

    public AuthorizeLog(Integer id) {
        this.id = id;
    }

    public AuthorizeLog(Integer id, String chargeBoxSerialNumber, String idTag, String status, Date timestamp) {
        this.id = id;
        this.chargeBoxSerialNumber = chargeBoxSerialNumber;
        this.idTag = idTag;
        this.status = status;
        this.timestamp = timestamp;
    }

    public String getChargeBoxSerialNumber() {
        return chargeBoxSerialNumber;
    }

    public void setChargeBoxSerialNumber(String chargeBoxSerialNumber) {
        this.chargeBoxSerialNumber = chargeBoxSerialNumber;
    }

    public String getIdTag() {
        return idTag;
    }

    public void setIdTag(String idTag) {
        this.idTag = idTag;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
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
        if (!(object instanceof AuthorizeLog)) {
            return false;
        }
        AuthorizeLog other = (AuthorizeLog) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.offnet.ocpp.entity.AuthorizeLog[ id=" + id + " ]";
    }
    
}
