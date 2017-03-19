
package com.offnet.ocpp.request;

import com.google.gson.JsonObject;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import org.json.JSONObject;


/**
 * Defines the ClearCache.req PDU
 * 
 * <p>Java class for ClearCacheRequest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ClearCacheRequest">
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
@XmlType(name = "ClearCacheRequest")
public class ClearCacheRequest extends Request{

    public ClearCacheRequest() {
        super.setProcedureName("ClearCache");
    }
    
    public ClearCacheRequest(JsonObject json) {
        setProcedureName("ClearCache");
        setDeviceSerial(json.get("chargePointSerialNumber").getAsString());
    }
    
    @Override
    public String toJson() {
        JSONObject response = new JSONObject();
        
        return response.toString();
    }

}
