
package com.offnet.ocpp.request;

import com.google.gson.JsonObject;
import com.offnet.ocpp.general.Utilities_Business;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import org.json.JSONObject;


/**
 * Defines the FirmwareStatusNotification.req PDU
 * 
 * <p>Java class for FirmwareStatusNotificationRequest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="FirmwareStatusNotificationRequest">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="status" type="{urn://Ocpp/Cs/2012/06/}FirmwareStatus"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FirmwareStatusNotificationRequest", propOrder = {
    "status"
})
public class FirmwareStatusNotificationRequest extends Request {

    @XmlElement(required = true)
    @XmlSchemaType(name = "string")
    protected FirmwareStatus status;

    /**
     * Gets the value of the status property.
     * 
     * @return
     *     possible object is
     *     {@link FirmwareStatus }
     *     
     */
    public FirmwareStatus getStatus() {
        return status;
    }

    /**
     * Sets the value of the status property.
     * 
     * @param value
     *     allowed object is
     *     {@link FirmwareStatus }
     *     
     */
    public void setStatus(FirmwareStatus value) {
        this.status = value;
    }

    public FirmwareStatusNotificationRequest (JsonObject json) {
        
        setProcedureName("FirmwareStatusNotificationRequest");
        setStatus(FirmwareStatus.valueOf(json.get("status").getAsString().toUpperCase()));
        
    }

    @Override
    public String toJson() {
        
        JSONObject response = new JSONObject();
        
        Utilities_Business.addToJSON(response, "status", getStatus().value(), false);
        
        return response.toString();
    
    }

}
