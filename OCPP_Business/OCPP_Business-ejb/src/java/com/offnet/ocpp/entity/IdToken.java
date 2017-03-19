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
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "IdToken")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "IdToken.findAll", query = "SELECT i FROM IdToken i"),
    @NamedQuery(name = "IdToken.findByValue", query = "SELECT i FROM IdToken i WHERE i.value = :value"),
    @NamedQuery(name = "IdToken.findByStatus", query = "SELECT i FROM IdToken i WHERE i.status = :status"),
    @NamedQuery(name = "IdToken.findByExpiryDate", query = "SELECT i FROM IdToken i WHERE i.expiryDate = :expiryDate")})
public class IdToken implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "value")
    private String value;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 12)
    @Column(name = "status")
    private String status;
    @Column(name = "expiryDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date expiryDate;
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @ManyToOne
    private Users userId;
    @OneToMany(mappedBy = "parentIdTag")
    private Collection<IdToken> idTokenCollection;
    @JoinColumn(name = "parentIdTag", referencedColumnName = "value")
    @ManyToOne
    private IdToken parentIdTag;

    public IdToken() {
    }

    public IdToken(String value) {
        this.value = value;
    }

    public IdToken(String value, String status) {
        this.value = value;
        this.status = status;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    public Users getUserId() {
        return userId;
    }

    public void setUserId(Users userId) {
        this.userId = userId;
    }

    @XmlTransient
    public Collection<IdToken> getIdTokenCollection() {
        return idTokenCollection;
    }

    public void setIdTokenCollection(Collection<IdToken> idTokenCollection) {
        this.idTokenCollection = idTokenCollection;
    }

    public IdToken getParentIdTag() {
        return parentIdTag;
    }

    public void setParentIdTag(IdToken parentIdTag) {
        this.parentIdTag = parentIdTag;
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
        if (!(object instanceof IdToken)) {
            return false;
        }
        IdToken other = (IdToken) object;
        if ((this.value == null && other.value != null) || (this.value != null && !this.value.equals(other.value))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "IdToken{" + "value=" + value + ", status=" + status + ", expiryDate=" + expiryDate + ", userId=" + userId + ", idTokenCollection=" + idTokenCollection + ", parentIdTag=" + parentIdTag + '}';
    }
}
