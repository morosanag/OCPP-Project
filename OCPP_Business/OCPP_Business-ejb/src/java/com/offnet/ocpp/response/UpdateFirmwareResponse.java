
package com.offnet.ocpp.response;

import com.google.gson.JsonObject;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for UpdateFirmwareResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="UpdateFirmwareResponse">
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
@XmlType(name = "UpdateFirmwareResponse")
public class UpdateFirmwareResponse implements Response{

    public UpdateFirmwareResponse (JsonObject json) {
    }
    
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
