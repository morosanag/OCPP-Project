
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
 * Defines the DiagnosticsStatusNotification.req PDU
 * 
 * <p>Java class for DiagnosticsStatusNotificationRequest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DiagnosticsStatusNotificationRequest">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="status" type="{urn://Ocpp/Cs/2012/06/}DiagnosticsStatus"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DiagnosticsStatusNotificationRequest", propOrder = {
    "status"
})
public class DiagnosticsStatusNotificationRequest extends Request{

    @XmlElement(required = true)
    @XmlSchemaType(name = "string")
    protected String status;

    /**
     * Gets the value of the status property.
     * 
     * @return
     *     possible object is
     *     {@link DiagnosticsStatus }
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
     *     {@link DiagnosticsStatus }
     *     
     */
    public void setStatus(String value) {
        this.status = value;
    }

    @Override
    public String toJson() {
        
        JSONObject response = new JSONObject();
        
        Utilities_Business.addToJSON(response, "status", getStatus(), false);
    
        return response.toString();
    }

    public DiagnosticsStatusNotificationRequest (JsonObject json) {
        setProcedureName("DiagnosticsStatusNotification");
        setStatus(json.get("status").getAsString());
    }
}
