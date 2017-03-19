
package com.offnet.ocpp.response;

import com.offnet.ocpp.entity.RegistrationStatus;
import com.google.gson.JsonObject;
import com.offnet.ocpp.general.Utilities_Business;
import java.util.GregorianCalendar;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * Defines the BootNotification.conf PDU
 * 
 * <p>Java class for BootNotificationResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="BootNotificationResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="status" type="{urn://Ocpp/Cs/2012/06/}RegistrationStatus"/>
 *         &lt;element name="currentTime" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="heartbeatInterval" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */

public class BootNotificationResponse implements Response{

    protected RegistrationStatus status;
    protected GregorianCalendar currentTime;
    protected int heartbeatInterval;

    /**
     * Gets the value of the status property.
     * 
     * @return
     *     possible object is
     *     {@link RegistrationStatus }
     *     
     */
    public RegistrationStatus getStatus() {
        return status;
    }

    /**
     * Sets the value of the status property.
     * 
     * @param value
     *     allowed object is
     *     {@link RegistrationStatus }
     *     
     */
    public void setStatus(RegistrationStatus value) {
        this.status = value;
    }

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

    /**
     * Gets the value of the heartbeatInterval property.
     * 
     */
    public int getHeartbeatInterval() {
        return heartbeatInterval;
    }

    /**
     * Sets the value of the heartbeatInterval property.
     * 
     */
    public void setHeartbeatInterval(int value) {
        this.heartbeatInterval = value;
    }

    
    
    public String toString() {
        JsonObject json = new JsonObject();
        json.addProperty("status", status.getValue());
        json.addProperty("currentTime", Utilities_Business.formatData(currentTime));
        json.addProperty("heartbeatInterval", heartbeatInterval);
        return json.toString();
    }

    @Override
    public String toJson() {
        return toString();
    }

    @Override
    public String toJsonSoapVersion() {
        JsonObject json = new JsonObject();
        json.addProperty("status", status.getValue().toUpperCase());
        json.addProperty("currentTime", Utilities_Business.formatDataToLong(currentTime));
        json.addProperty("heartbeatInterval", heartbeatInterval);
        return json.toString();
    }

}
