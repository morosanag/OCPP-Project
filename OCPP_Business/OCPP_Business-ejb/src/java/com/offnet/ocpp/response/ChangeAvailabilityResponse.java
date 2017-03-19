
package com.offnet.ocpp.response;

import com.google.gson.JsonObject;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * Defines the ChangeAvailability.conf PDU
 * 
 * <p>Java class for ChangeAvailabilityResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ChangeAvailabilityResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="status" type="{urn://Ocpp/Cp/2012/06/}AvailabilityStatus"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ChangeAvailabilityResponse", propOrder = {
    "status"
})
public class ChangeAvailabilityResponse implements Response {

    @XmlElement(required = true)
    @XmlSchemaType(name = "string")
    protected String status;

    /**
     * Gets the value of the status property.
     * 
     * @return
     *     possible object is
     *     {@link AvailabilityStatus }
     *     
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets the value of the status property.
     * 
     * @param value
     *     allowed object is
     *     {@link AvailabilityStatus }
     *     
     */
    public void setStatus(String value) {
        this.status = value;
    }

    @Override
    public String toJson() {
        
        JsonObject json = new JsonObject();
        
        json.addProperty("status", getStatus());
        
        return json.toString();
    
    }

    
    public ChangeAvailabilityResponse (JsonObject json) {
        
        setStatus(json.get("status").getAsString());
      
    }

    @Override
    public String toJsonSoapVersion() {
        return "ChangeAvailabilityResponse";
    }

    @Override
    public String toString() {
        return "ChangeAvailabilityResponse{" + "status=" + status + '}';
    }
    
    
}
