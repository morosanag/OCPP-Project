
package com.offnet.ocpp.response;

import com.google.gson.JsonObject;
import com.offnet.ocpp.entity.AuthorizationStatus;
import com.offnet.ocpp.general.Utilities_Business;
import java.util.GregorianCalendar;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * Defines the Authorize.conf PDU
 * 
 * <p>Java class for AuthorizeResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AuthorizeResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="idTagInfo" type="{urn://Ocpp/Cs/2012/06/}IdTagInfo"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */

public class AuthorizeResponse implements Response{

    protected AuthorizationStatus status;
    protected GregorianCalendar expiryDate;
    protected String parentIdTag;

    public AuthorizationStatus getStatus() {
        return status;
    }

    public void setStatus(AuthorizationStatus status) {
        this.status = status;
    }

    public GregorianCalendar getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(GregorianCalendar expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getParentIdTag() {
        return parentIdTag;
    }

    public void setParentIdTag(String parentIdTag) {
        this.parentIdTag = parentIdTag;
    }

    /**
     * Gets the value of the idTagInfo property.
     * 
     * @return
     *     possible object is
     *     {@link IdTagInfo }
     *     
     */
    
    @Override
    public String toJson() {
        
        JsonObject json = new JsonObject();
        JsonObject idTagInfo = new JsonObject();
        idTagInfo.addProperty("status", status.getValue());
        if(expiryDate != null) {
            idTagInfo.addProperty("expiryDate", Utilities_Business.formatData(expiryDate));
        }
        if(parentIdTag != null) {
            idTagInfo.addProperty("parentIdTag", parentIdTag);
        }
        json.add("idTagInfo", idTagInfo);
        
        return json.toString();
    }

    @Override
    public String toJsonSoapVersion() {
        JsonObject json = new JsonObject();
        JsonObject idTagInfo = new JsonObject();
        idTagInfo.addProperty("status", status.getValue().toUpperCase());
        if(expiryDate != null) {
            idTagInfo.addProperty("expiryDate", Utilities_Business.formatDataToLong(expiryDate));
        }
        if(parentIdTag != null) {
            idTagInfo.addProperty("parentIdTag", parentIdTag);
        }
        json.add("idTagInfo", idTagInfo);
        
        return json.toString();
    }

}
