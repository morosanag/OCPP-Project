
package com.offnet.ocpp.request;

import com.google.gson.JsonObject;
import com.offnet.ocpp.general.Utilities_Websocket;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;

import org.json.JSONException;
import org.json.JSONObject;
import java.util.logging.Level;

/**
 * Defines the ChangeAvailability.req PDU
 * 
 * <p>Java class for ChangeAvailabilityRequest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ChangeAvailabilityRequest">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="connectorId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="type" type="{urn://Ocpp/Cp/2012/06/}AvailabilityType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ChangeAvailabilityRequest", propOrder = {
    "connectorId",
    "type"
})
public class ChangeAvailabilityRequest extends Request {

    protected int connectorId;
    @XmlElement(required = true)
    @XmlSchemaType(name = "string")
    protected String type;

    /**
     * Gets the value of the connectorId property.
     * 
     */
    public int getConnectorId() {
        return connectorId;
    }

    /**
     * Sets the value of the connectorId property.
     * 
     */
    public void setConnectorId(int value) {
        this.connectorId = value;
    }

    /**
     * Gets the value of the type property.
     * 
     * @return
     *     possible object is
     *     {@link AvailabilityType }
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
     *     {@link AvailabilityType }
     *     
     */
    public void setType(String value) {
        this.type = value;
    }
    
    public ChangeAvailabilityRequest() {
        super.setProcedureName("ChangeAvailability");
    }
    
    public ChangeAvailabilityRequest(JsonObject json) {
  
        setProcedureName("ChangeAvailability");
        setDeviceSerial(json.get("chargePointSerialNumber").getAsString());

        setConnectorId(json.get("connectorId").getAsInt());
        setType(json.get("type").getAsString());
    }
    
    @Override
    public String toJson() {
        JSONObject response = new JSONObject();
        
        Utilities_Websocket.addToJSON(response, "connectorId", getConnectorId(), false);
        Utilities_Websocket.addToJSON(response, "type", getType(), false);
        
        return response.toString();
    }

}
