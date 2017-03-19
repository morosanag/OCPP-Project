
package com.offnet.ocpp.request;

import com.google.gson.JsonObject;
import com.offnet.ocpp.general.Utilities_Business;
import java.util.GregorianCalendar;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;
import org.json.JSONObject;


/**
 * <p>Java class for UpdateFirmwareRequest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="UpdateFirmwareRequest">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="retrieveDate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="location" type="{http://www.w3.org/2001/XMLSchema}anyURI"/>
 *         &lt;element name="retries" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="retryInterval" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "UpdateFirmwareRequest", propOrder = {
    "retrieveDate",
    "location",
    "retries",
    "retryInterval"
})
public class UpdateFirmwareRequest extends Request{

    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    protected GregorianCalendar retrieveDate;
    @XmlElement(required = true)
    @XmlSchemaType(name = "anyURI")
    protected String location;
    protected Integer retries;
    protected Integer retryInterval;

    /**
     * Gets the value of the retrieveDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public GregorianCalendar getRetrieveDate() {
        return retrieveDate;
    }

    /**
     * Sets the value of the retrieveDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setRetrieveDate(GregorianCalendar value) {
        this.retrieveDate = value;
    }

    /**
     * Gets the value of the location property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLocation() {
        return location;
    }

    /**
     * Sets the value of the location property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLocation(String value) {
        this.location = value;
    }

    /**
     * Gets the value of the retries property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getRetries() {
        return retries;
    }

    /**
     * Sets the value of the retries property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setRetries(Integer value) {
        this.retries = value;
    }

    /**
     * Gets the value of the retryInterval property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getRetryInterval() {
        return retryInterval;
    }

    /**
     * Sets the value of the retryInterval property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setRetryInterval(Integer value) {
        this.retryInterval = value;
    }
    
    public UpdateFirmwareRequest() {
        super.setProcedureName("UpdateFirmware");
    }
    
    public UpdateFirmwareRequest(JsonObject json) {
  
        setProcedureName("ChangeAvailability");
        setDeviceSerial(json.get("chargePointSerialNumber").getAsString());
        
        setLocation(json.get("location").getAsString());
        setRetrieveDate(Utilities_Business.formatCalendar(json.get("retrieveDate").getAsString()));
        
        if(Utilities_Business.checkNull(json, "retries")) { 
            setRetries(json.get("retries").getAsInt());
        }
        
        if(Utilities_Business.checkNull(json, "retryInterval")) { 
            setRetryInterval(json.get("retryInterval").getAsInt());
        }
        
    }
    
    @Override
    public String toJson() {
        JSONObject response = new JSONObject();
        
        Utilities_Business.addToJSON(response, "location", getLocation(), false);
        Utilities_Business.addToJSON(response, "retrieveDate",  Utilities_Business.formatData(getRetrieveDate()), false);
        Utilities_Business.addToJSON(response, "retries", getRetries(), true);
        Utilities_Business.addToJSON(response, "retryInterval", getRetryInterval(), true);
        
        return response.toString();
    }

}
