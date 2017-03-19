
package com.offnet.ocpp.response;

import com.google.gson.JsonObject;
import com.offnet.ocpp.general.Utilities_Business;
import com.offnet.ocpp.request.IdTagInfo;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * Defines the StartTransaction.conf PDU
 * 
 * <p>Java class for StartTransactionResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="StartTransactionResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="transactionId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="idTagInfo" type="{urn://Ocpp/Cs/2012/06/}IdTagInfo"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "StartTransactionResponse", propOrder = {
    "transactionId",
    "idTagInfo"
})
public class StartTransactionResponse implements Response{

    protected int transactionId;
    @XmlElement(required = true)
    protected IdTagInfo idTagInfo;

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
        
        json.addProperty("transactionId", getTransactionId());
        json.add("idTagInfo", getIdTagInfo().toJson());
        
        return json.toString();
    }
    
   

    @Override
    public String toJsonSoapVersion() {
        JsonObject json = new JsonObject();
        
        json.addProperty("transactionId", getTransactionId());
        json.add("idTagInfo", getIdTagInfo().toJsonSoapVersion());
        
        return json.toString();
    }

}
