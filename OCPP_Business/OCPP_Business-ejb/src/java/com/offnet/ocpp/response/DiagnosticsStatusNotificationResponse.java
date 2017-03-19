
package com.offnet.ocpp.response;

import com.google.gson.JsonObject;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import org.json.JSONObject;


/**
 * Defines the DiagnosticsStatusNotification.conf PDU
 * 
 * <p>Java class for DiagnosticsStatusNotificationResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DiagnosticsStatusNotificationResponse">
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
@XmlType(name = "DiagnosticsStatusNotificationResponse")
public class DiagnosticsStatusNotificationResponse implements Response{

    @Override
    public String toJson() {
        JSONObject json = new JSONObject();
        return json.toString();
    }
   
    @Override
    public String toJsonSoapVersion() {
        JsonObject json = new JsonObject();
        return json.toString();
    }

    
}
