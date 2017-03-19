
package com.offnet.ocpp.request;

import com.google.gson.JsonObject;
import com.offnet.ocpp.general.Utilities_Websocket;
import java.util.GregorianCalendar;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;
import org.json.JSONObject;


/**
 * Defines the GetDiagnostics.req PDU
 * 
 * <p>Java class for GetDiagnosticsRequest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="GetDiagnosticsRequest">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="location" type="{http://www.w3.org/2001/XMLSchema}anyURI"/>
 *         &lt;element name="startTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="stopTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
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
@XmlType(name = "GetDiagnosticsRequest", propOrder = {
    "location",
    "startTime",
    "stopTime",
    "retries",
    "retryInterval"
})
public class GetDiagnosticsRequest extends Request{

    @XmlElement(required = true)
    @XmlSchemaType(name = "anyURI")
    protected String location;
    @XmlSchemaType(name = "dateTime")
    protected GregorianCalendar startTime;
    @XmlSchemaType(name = "dateTime")
    protected GregorianCalendar stopTime;
    protected Integer retries;
    protected Integer retryInterval;

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
     * Gets the value of the startTime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public GregorianCalendar getStartTime() {
        return startTime;
    }

    /**
     * Sets the value of the startTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setStartTime(GregorianCalendar value) {
        this.startTime = value;
    }

    /**
     * Gets the value of the stopTime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public GregorianCalendar getStopTime() {
        return stopTime;
    }

    /**
     * Sets the value of the stopTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setStopTime(GregorianCalendar value) {
        this.stopTime = value;
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
    
    public GetDiagnosticsRequest() {
        super.setProcedureName("GetDiagnostics");
    }

    public GetDiagnosticsRequest (JsonObject json) {
        
        setProcedureName("GetDiagnostics");
        setDeviceSerial(json.get("chargePointSerialNumber").getAsString());
        setLocation(json.get("location").getAsString());

        if(json.has("retries")) {
            setRetries(json.get("retries").getAsInt());
        }
        if(json.has("retryInterval")) {
            setRetryInterval(json.get("retryInterval").getAsInt());
        }
        if(json.has("startTime")) {
            setStartTime(Utilities_Websocket.formatCalendar(json.get("startTime").getAsString()));
        }
        if(json.has("stopTime")) {
            setStopTime(Utilities_Websocket.formatCalendar(json.get("stopTime").getAsString()));
        }
        
    }

    @Override
    public String toJson() {
        
        JSONObject obj = new JSONObject();
        
        Utilities_Websocket.addToJSON(obj, "location", getLocation(), false);
        Utilities_Websocket.addToJSON(obj, "retries", getRetries(), true);
        Utilities_Websocket.addToJSON(obj, "retryInterval", getRetryInterval(), true);
        Utilities_Websocket.addToJSON(obj, "startTime", Utilities_Websocket.formatData(getStartTime()), true);
        Utilities_Websocket.addToJSON(obj, "stopTime", Utilities_Websocket.formatData(getStopTime()), true);
        
        return obj.toString();
    }
    
}
