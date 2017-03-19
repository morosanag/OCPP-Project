
package com.offnet.ocpp.response;

import com.google.gson.JsonObject;
import com.offnet.ocpp.request.IdTagInfo;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * Defines the StopTransaction.conf PDU
 * 
 * <p>Java class for StopTransactionResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="StopTransactionResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="idTagInfo" type="{urn://Ocpp/Cs/2012/06/}IdTagInfo" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "StopTransactionResponse", propOrder = {
    "idTagInfo"
})
public class StopTransactionResponse implements Response{

    protected IdTagInfo idTagInfo;

    /**
     * Gets the value of the idTagInfo property.
     * 
     * @return
     *     possible object is
     *     {@link IdTagInfo }
     *     
     */
    public IdTagInfo getIdTagInfo() {
        return idTagInfo;
    }

    /**
     * Sets the value of the idTagInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link IdTagInfo }
     *     
     */
    public void setIdTagInfo(IdTagInfo value) {
        this.idTagInfo = value;
    }

    @Override
    public String toJson() {
        JsonObject json = new JsonObject();
        
        json.add("idTagInfo", getIdTagInfo().toJson());
        
        return json.toString();
    }

    @Override
    public String toJsonSoapVersion() {
        JsonObject json = new JsonObject();
        
        json.add("idTagInfo", getIdTagInfo().toJsonSoapVersion());
        
        return json.toString();
    }
    
}
