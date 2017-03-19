
package com.offnet.ocpp.request;

import com.google.gson.JsonObject;
import com.offnet.ocpp.general.Utilities_Business;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import org.json.JSONObject;


/**
 * Defines the UnlockConnector.req PDU
 * 
 * <p>Java class for UnlockConnectorRequest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="UnlockConnectorRequest">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="connectorId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "UnlockConnectorRequest", propOrder = {
    "connectorId"
})
public class UnlockConnectorRequest extends Request{

    protected int connectorId;

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

    
    public UnlockConnectorRequest (JsonObject json) {

        setProcedureName("UnlockConnector");
        setDeviceSerial(json.get("chargePointSerialNumber").getAsString());
        setConnectorId(json.get("connectorId").getAsInt());

    }
    
    @Override
    public String toJson() {
        
        JSONObject response = new JSONObject();
        
        Utilities_Business.addToJSON(response, "connectorId", getConnectorId(), false);
    
        return response.toString();
    }

}
