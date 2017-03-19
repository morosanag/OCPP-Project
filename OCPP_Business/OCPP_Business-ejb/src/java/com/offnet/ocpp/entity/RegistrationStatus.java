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
@Table(name = "RegistrationStatus")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RegistrationStatus.findAll", query = "SELECT r FROM RegistrationStatus r"),
    @NamedQuery(name = "RegistrationStatus.findByValue", query = "SELECT r FROM RegistrationStatus r WHERE r.value = :value")})
public class RegistrationStatus implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "value")
    private String value;

    public RegistrationStatus() {
    }

    public RegistrationStatus(String value) {
        this.value = value;
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
        hash += (value != null ? value.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RegistrationStatus)) {
            return false;
        }
        RegistrationStatus other = (RegistrationStatus) object;
        if ((this.value == null && other.value != null) || (this.value != null && !this.value.equals(other.value))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.offnet.ocpp.request.RegistrationStatus[ value=" + value + " ]";
    }
    
}
