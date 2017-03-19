
package com.offnet.ocpp.request;

import com.google.gson.JsonObject;
import com.offnet.ocpp.general.Utilities_Websocket;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import org.json.JSONObject;


/**
 * Defines the RemoteStopTransaction.req PDU
 * 
 * <p>Java class for RemoteStopTransactionRequest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="RemoteStopTransactionRequest">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="transactionId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RemoteStopTransactionRequest", propOrder = {
    "transactionId"
})
public class RemoteStopTransactionRequest extends Request {

    protected int transactionId;

    /**
     * Gets the value of the transactionId property.
     * 
     */
    public int getTransactionId() {
        return transactionId;
    }

    /**
     * Sets the value of the transactionId property.
     * 
     */
    public void setTransactionId(int value) {
        this.transactionId = value;
    }

    public RemoteStopTransactionRequest (JsonObject json) {
        
        setProcedureName("RemoteStopTransaction");
        setDeviceSerial(json.get("chargePointSerialNumber").getAsString());
        setTransactionId(json.get("transactionId").getAsInt());
        
    }

    @Override
    public String toJson() {
        
        JSONObject response = new JSONObject();
        
        Utilities_Websocket.addToJSON(response, "transactionId", getTransactionId(), false);
        
        return response.toString();
    
    }

}
