
package com.offnet.ocpp.request;

import com.google.gson.JsonObject;
import com.offnet.ocpp.general.Utilities_Websocket;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;
import org.json.JSONException;
import org.json.JSONObject;


/**
 * <p>Java class for IdTagInfo complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="IdTagInfo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="status" type="{urn://Ocpp/Cs/2012/06/}AuthorizationStatus"/>
 *         &lt;element name="expiryDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="parentIdTag" type="{urn://Ocpp/Cs/2012/06/}IdToken" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "IdTagInfo", propOrder = {
    "status",
    "expiryDate",
    "parentIdTag"
})
public class IdTagInfo {

    @XmlElement(required = true)
    @XmlSchemaType(name = "string")
    protected AuthorizationStatus status;
    @XmlSchemaType(name = "dateTime")
    protected GregorianCalendar expiryDate;
    protected String parentIdTag;

    /**
     * Gets the value of the status property.
     * 
     * @return
     *     possible object is
     *     {@link AuthorizationStatus }
     *     
     */
    public AuthorizationStatus getStatus() {
        return status;
    }

    /**
     * Sets the value of the status property.
     * 
     * @param value
     *     allowed object is
     *     {@link AuthorizationStatus }
     *     
     */
    public void setStatus(AuthorizationStatus value) {
        this.status = value;
    }

    /**
     * Gets the value of the expiryDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public GregorianCalendar getExpiryDate() {
        return expiryDate;
    }

    /**
     * Sets the value of the expiryDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setExpiryDate(GregorianCalendar value) {
        this.expiryDate = value;
    }

    /**
     * Gets the value of the parentIdTag property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getParentIdTag() {
        return parentIdTag;
    }

    /**
     * Sets the value of the parentIdTag property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setParentIdTag(String value) {
        this.parentIdTag = value;
    }
    
    /*
     protected AuthorizationStatus status;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar expiryDate;
    protected String parentIdTag;
    */
    
    public String toString() {
        return toJson().toString();
    }

    public JsonObject toJson() {
        JsonObject json = new JsonObject();
        json.addProperty("status",getStatus().getValue());
        json.addProperty("expiryDate", Utilities_Websocket.formatData(getExpiryDate()));
        json.addProperty("parentIdTag",getParentIdTag());
        return json;
    }
    
    private String getSOAPStatus(String status) {
        if(status.equalsIgnoreCase("ConcurrentTx")) {
            return "CONCURRENT_TX";
        }
        return status.toUpperCase();
    }
    
    public JsonObject toJsonSoapVersion() {
        JsonObject json = new JsonObject();
        json.addProperty("status",getSOAPStatus(getStatus().getValue()));
        json.addProperty("expiryDate", Utilities_Websocket.formatDataToLong(getExpiryDate()));
        json.addProperty("parentIdTag",getParentIdTag());
        return json;
    }
}
