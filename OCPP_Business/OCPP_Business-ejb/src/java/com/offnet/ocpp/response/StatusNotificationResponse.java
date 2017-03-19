
package com.offnet.ocpp.response;

import com.google.gson.JsonObject;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * Defines the StatusNotification.conf PDU
 * 
 * <p>Java class for StatusNotificationResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="StatusNotificationResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "StatusNotificationResponse")
public class StatusNotificationResponse implements Response{

    @Override
    public String toJson() {
        JsonObject json = new JsonObject();
        return json.toString();
    }

    @Override
    public String toJsonSoapVersion() {
        return toJson();
    }


}
