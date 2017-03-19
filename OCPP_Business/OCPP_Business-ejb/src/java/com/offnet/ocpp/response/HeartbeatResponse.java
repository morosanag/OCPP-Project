
package com.offnet.ocpp.response;

import com.google.gson.JsonObject;
import com.offnet.ocpp.general.Utilities_Business;
import java.io.Serializable;
import java.util.GregorianCalendar;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * Defines the Heartbeat.conf PDU
 * 
 * <p>Java class for HeartbeatResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="HeartbeatResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="currentTime" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */

public class HeartbeatResponse implements Response{

    protected GregorianCalendar currentTime;

    /**
     * Gets the value of the currentTime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public GregorianCalendar getCurrentTime() {
        return currentTime;
    }

    /**
     * Sets the value of the currentTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setCurrentTime(GregorianCalendar value) {
        this.currentTime = value;
    }

    @Override
    public String toJson() {
        JsonObject json = new JsonObject();
        json.addProperty("currentTime", Utilities_Business.formatData(currentTime));
        return json.toString();
    }

    @Override
    public String toJsonSoapVersion() {
        JsonObject json = new JsonObject();
        json.addProperty("currentTime", Utilities_Business.formatDataToLong(currentTime));
        return json.toString();
    }

}
