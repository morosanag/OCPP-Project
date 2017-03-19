
package com.offnet.ocpp.request;

import com.google.gson.JsonObject;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * Defines the BootNotification.req PDU
 * 
 * <p>Java class for BootNotificationRequest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="BootNotificationRequest">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="chargePointVendor" type="{urn://Ocpp/Cs/2012/06/}ChargePointVendor"/>
 *         &lt;element name="chargePointModel" type="{urn://Ocpp/Cs/2012/06/}ChargePointModel"/>
 *         &lt;element name="chargePointSerialNumber" type="{urn://Ocpp/Cs/2012/06/}ChargePointSerialNumber" minOccurs="0"/>
 *         &lt;element name="chargeBoxSerialNumber" type="{urn://Ocpp/Cs/2012/06/}ChargeBoxSerialNumber" minOccurs="0"/>
 *         &lt;element name="firmwareVersion" type="{urn://Ocpp/Cs/2012/06/}FirmwareVersion" minOccurs="0"/>
 *         &lt;element name="iccid" type="{urn://Ocpp/Cs/2012/06/}IccidString" minOccurs="0"/>
 *         &lt;element name="imsi" type="{urn://Ocpp/Cs/2012/06/}ImsiString" minOccurs="0"/>
 *         &lt;element name="meterType" type="{urn://Ocpp/Cs/2012/06/}MeterType" minOccurs="0"/>
 *         &lt;element name="meterSerialNumber" type="{urn://Ocpp/Cs/2012/06/}MeterSerialNumber" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BootNotificationRequest", propOrder = {
    "chargePointVendor",
    "chargePointModel",
    "chargePointSerialNumber",
    "chargeBoxSerialNumber",
    "firmwareVersion",
    "iccid",
    "imsi",
    "meterType",
    "meterSerialNumber"
})
public class BootNotificationRequest extends Request{

    @XmlElement(required = true)
    protected String chargePointVendor;
    @XmlElement(required = true)
    protected String chargePointModel;
    protected String chargePointSerialNumber;
    protected String chargeBoxSerialNumber;
    protected String firmwareVersion;
    protected String iccid;
    protected String imsi;
    protected String meterType;
    protected String meterSerialNumber;

    /**
     * Gets the value of the chargePointVendor property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getChargePointVendor() {
        return chargePointVendor;
    }

    /**
     * Sets the value of the chargePointVendor property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setChargePointVendor(String value) {
        this.chargePointVendor = value;
    }

    /**
     * Gets the value of the chargePointModel property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getChargePointModel() {
        return chargePointModel;
    }

    /**
     * Sets the value of the chargePointModel property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setChargePointModel(String value) {
        this.chargePointModel = value;
    }

    /**
     * Gets the value of the chargePointSerialNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getChargePointSerialNumber() {
        return chargePointSerialNumber;
    }

    /**
     * Sets the value of the chargePointSerialNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setChargePointSerialNumber(String value) {
        this.chargePointSerialNumber = value;
    }

    /**
     * Gets the value of the chargeBoxSerialNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getChargeBoxSerialNumber() {
        return chargeBoxSerialNumber;
    }

    /**
     * Sets the value of the chargeBoxSerialNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setChargeBoxSerialNumber(String value) {
        this.chargeBoxSerialNumber = value;
    }

    /**
     * Gets the value of the firmwareVersion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFirmwareVersion() {
        return firmwareVersion;
    }

    /**
     * Sets the value of the firmwareVersion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFirmwareVersion(String value) {
        this.firmwareVersion = value;
    }

    /**
     * Gets the value of the iccid property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIccid() {
        return iccid;
    }

    /**
     * Sets the value of the iccid property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIccid(String value) {
        this.iccid = value;
    }

    /**
     * Gets the value of the imsi property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getImsi() {
        return imsi;
    }

    /**
     * Sets the value of the imsi property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setImsi(String value) {
        this.imsi = value;
    }

    /**
     * Gets the value of the meterType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMeterType() {
        return meterType;
    }

    /**
     * Sets the value of the meterType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMeterType(String value) {
        this.meterType = value;
    }

    /**
     * Gets the value of the meterSerialNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMeterSerialNumber() {
        return meterSerialNumber;
    }

    /**
     * Sets the value of the meterSerialNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMeterSerialNumber(String value) {
        this.meterSerialNumber = value;
    }

    public BootNotificationRequest (JsonObject json) {
        this.setChargePointVendor(json.get("chargePointVendor").getAsString());
        this.setChargePointModel(json.get("chargePointModel").getAsString());
        
        if(json.has("chargePointSerialNumber") && !json.get("chargePointSerialNumber").toString().equals("null")) {
            this.setChargeBoxSerialNumber(json.get("chargePointSerialNumber").getAsString());
        }
        if(json.has("chargeBoxSerialNumber") && !json.get("chargeBoxSerialNumber").toString().equals("null")) {
            this.setChargeBoxSerialNumber(json.get("chargeBoxSerialNumber").getAsString());
        }
        
        if(json.has("firmwareVersion") && !json.get("firmwareVersion").toString().equals("null")) {
            this.setFirmwareVersion(json.get("firmwareVersion").getAsString());
        }
        if(json.has("iccid") && !json.get("iccid").toString().equals("null")) {
            this.setIccid(json.get("iccid").getAsString());
        }
        if(json.has("imsi") && !json.get("imsi").toString().equals("null")) {
            this.setImsi(json.get("imsi").getAsString());
        }
        if(json.has("meterType") && !json.get("meterType").toString().equals("null")) {
            this.setMeterType(json.get("meterType").getAsString());
        }
        if(json.has("meterSerialNumber") && !json.get("meterSerialNumber").toString().equals("null")) {
            this.setMeterSerialNumber(json.get("meterSerialNumber").getAsString());
        }

    }

    @Override
    public String toJson() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
