
package com.offnet.ocpp.request;

import com.google.gson.JsonObject;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import org.json.JSONException;
import org.json.JSONObject;


/**
 * Defines the Reset.req PDU
 * 
 * <p>Java class for ResetRequest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ResetRequest">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="type" type="{urn://Ocpp/Cp/2012/06/}ResetType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ResetRequest", propOrder = {
    "type"
})
public class ResetRequest extends Request {

    @XmlElement(required = true)
    @XmlSchemaType(name = "string")
    protected String type;

    
    /**
     * Gets the value of the type property.
     * 
     * @return
     *     possible object is
     *     {@link ResetType }
     *     
     */
    public String getType() {
        return type;
    }

    /**
     * Sets the value of the type property.
     * 
     * @param value
     *     allowed object is
     *     {@link ResetType }
     *     
     */
    public void setType(String value) {
        this.type = value;
    }

    @Override
    public String toJson() {
        JSONObject response = new JSONObject();
        try {
            response.put("type", getType());
        } catch (JSONException ex) {
            Logger.getLogger(ResetRequest.class.getName()).log(Level.SEVERE, null, ex);
        }
        return response.toString();
    }
    
    public ResetRequest (JsonObject json) {
        setProcedureName("Reset");
        setDeviceSerial(json.get("chargePointSerialNumber").getAsString());
        setType(json.get("type").getAsString());
        
    }

}
