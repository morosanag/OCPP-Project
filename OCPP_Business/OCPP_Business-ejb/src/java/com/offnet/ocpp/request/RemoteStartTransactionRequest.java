
package com.offnet.ocpp.request;

import com.google.gson.JsonObject;
import com.offnet.ocpp.general.Utilities_Business;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import org.json.JSONObject;


/**
 * Defines the RemoteStartTransaction.req PDU
 * 
 * <p>Java class for RemoteStartTransactionRequest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="RemoteStartTransactionRequest">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="idTag" type="{urn://Ocpp/Cp/2012/06/}IdToken"/>
 *         &lt;element name="connectorId" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RemoteStartTransactionRequest", propOrder = {
    "idTag",
    "connectorId"
})
public class RemoteStartTransactionRequest extends Request {

    @XmlElement(required = true)
    protected String idTag;
    protected Integer connectorId;

    /**
     * Gets the value of the idTag property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdTag() {
        return idTag;
    }

    /**
     * Sets the value of the idTag property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdTag(String value) {
        this.idTag = value;
    }

    /**
     * Gets the value of the connectorId property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getConnectorId() {
        return connectorId;
    }

    /**
     * Sets the value of the connectorId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setConnectorId(Integer value) {
        this.connectorId = value;
    }
  
    
    public RemoteStartTransactionRequest (JsonObject json) {
        
        setProcedureName("RemoteStartTransaction");
        setDeviceSerial(json.get("chargePointSerialNumber").getAsString());
        setIdTag(json.get("idTag").getAsString());
        if(json.has("connectorId")) {
            setConnectorId(json.get("connectorId").getAsInt());
        }
        
    }

    @Override
    public String toJson() {
        
        JSONObject response = new JSONObject();
        
        Utilities_Business.addToJSON(response, "idTag", getIdTag(), false);
        Utilities_Business.addToJSON(response, "connectorId", getConnectorId(), true);
        
        return response.toString();
    
    }

}
