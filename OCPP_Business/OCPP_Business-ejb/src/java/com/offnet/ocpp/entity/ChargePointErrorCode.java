/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.offnet.ocpp.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author gabi
 */
@Entity
@Table(name = "ChargePointErrorCode")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ChargePointErrorCode.findAll", query = "SELECT c FROM ChargePointErrorCode c"),
    @NamedQuery(name = "ChargePointErrorCode.findById", query = "SELECT c FROM ChargePointErrorCode c WHERE c.id = :id"),
    @NamedQuery(name = "ChargePointErrorCode.findByValue", query = "SELECT c FROM ChargePointErrorCode c WHERE c.value = :value")})
public class ChargePointErrorCode implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "value")
    private String value;

    public ChargePointErrorCode() {
    }

    public ChargePointErrorCode(Integer id) {
        this.id = id;
    }

    public ChargePointErrorCode(Integer id, String value) {
        this.id = id;
        this.value = value;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ChargePointErrorCode)) {
            return false;
        }
        ChargePointErrorCode other = (ChargePointErrorCode) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.offnet.ocpp.entity.ChargePointErrorCode[ id=" + id + " ]";
    }
    
}
