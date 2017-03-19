
package com.offnet.ocpp.request;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.offnet.ocpp.general.Utilities_Business;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import org.json.JSONObject;


/**
 * Defines the GetConfiguration.req PDU
 * 
 * <p>Java class for GetConfigurationRequest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="GetConfigurationRequest">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="key" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GetConfigurationRequest", propOrder = {
    "key"
})
public class GetConfigurationRequest extends Request{

    protected List<String> key;

    /**
     * Gets the value of the key property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the key property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getKey().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getKey() {
        if (key == null) {
            key = new ArrayList<String>();
        }
        return this.key;
    }
    
    public void setKey(JsonArray key) {
        this.key = new ArrayList<String>();
        for(int i = 0; i < key.size(); i++) {
            this.key.add(key.get(i).getAsString());
        }
    }

    public GetConfigurationRequest (JsonObject json) {
        
        setProcedureName("GetConfiguration");
        setDeviceSerial(json.get("chargePointSerialNumber").getAsString());
        
        if(json.has("key")) {
            setKey(json.get("key").getAsJsonArray());
        }
        
    }
    
    @Override
    public String toJson() {
        
        JSONObject obj = new JSONObject();
        
        Utilities_Business.addToJSON(obj, "key", getKey(), true);
        
        return obj.toString();
    
    }
    
    

}
