
package com.offnet.ocpp.request;

import com.google.gson.JsonObject;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import org.json.JSONObject;


/**
 * Defines the GetLocalListVersion.req PDU
 * 
 * <p>Java class for GetLocalListVersionRequest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="GetLocalListVersionRequest">
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
@XmlType(name = "GetLocalListVersionRequest")
public class GetLocalListVersionRequest extends Request{

    public GetLocalListVersionRequest() {
        super.setProcedureName("GetLocalListVersion");
    }

    public GetLocalListVersionRequest (JsonObject json) {
        setProcedureName("GetLocalListVersion");
        setDeviceSerial(json.get("chargePointSerialNumber").getAsString());
    }

    @Override
    public String toJson() {
        
        JSONObject obj = new JSONObject();
        return obj.toString();
        
    }

}
