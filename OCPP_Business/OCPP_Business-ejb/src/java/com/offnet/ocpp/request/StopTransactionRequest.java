
package com.offnet.ocpp.request;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.offnet.ocpp.dao.TransactionDataDAO;
import com.offnet.ocpp.dao.TransactionDataDAOImpl;
import com.offnet.ocpp.entity.TransactionData;
import com.offnet.ocpp.general.Utilities_Business;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * Defines the StopTransaction.req PDU
 * 
 * <p>Java class for StopTransactionRequest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="StopTransactionRequest">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="transactionId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="idTag" type="{urn://Ocpp/Cs/2012/06/}IdToken" minOccurs="0"/>
 *         &lt;element name="timestamp" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="meterStop" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="transactionData" type="{urn://Ocpp/Cs/2012/06/}TransactionData" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "StopTransactionRequest", propOrder = {
    "transactionId",
    "idTag",
    "timestamp",
    "meterStop",
    "transactionData"
})
public class StopTransactionRequest extends Request{

    protected int transactionId;
    protected String idTag;
    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    protected GregorianCalendar timestamp;
    protected int meterStop;
    protected List<TransactionData> transactionData;

    /**
     * Gets the value of the transactionId property.
     * 
     */
    public int getTransactionId() {
        return transactionId;
    }

    /**
     * Sets the value of the transactionId property.
     * 
     */
    public void setTransactionId(int value) {
        this.transactionId = value;
    }

    /**
     * Gets the value of the idTag property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdTag() {
        return idTag;
    }

    /**
     * Sets the value of the idTag property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdTag(String value) {
        this.idTag = value;
    }

    /**
     * Gets the value of the timestamp property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public GregorianCalendar getTimestamp() {
        return timestamp;
    }

    /**
     * Sets the value of the timestamp property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setTimestamp(GregorianCalendar value) {
        this.timestamp = value;
    }

    /**
     * Gets the value of the meterStop property.
     * 
     */
    public int getMeterStop() {
        return meterStop;
    }

    /**
     * Sets the value of the meterStop property.
     * 
     */
    public void setMeterStop(int value) {
        this.meterStop = value;
    }

    /**
     * Gets the value of the transactionData property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the transactionData property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTransactionData().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TransactionData }
     * 
     * 
     */
    public List<TransactionData> getTransactionData() {
        if (transactionData == null) {
            transactionData = new ArrayList<TransactionData>();
        }
        return this.transactionData;
    }

    public void setTransactionData(List<TransactionData> transactionData) {
        this.transactionData = transactionData;
    }
    
    
    
    public StopTransactionRequest (JsonObject json) {
        
        if(json.has("idTag")) {
            setIdTag(json.get("idTag").getAsString());
        }
        setMeterStop(json.get("meterStop").getAsInt());
        setTimestamp(Utilities_Business.formatCalendar(json.get("timestamp").getAsString()));
        if(json.has("transactionId")) {
            setTransactionId(json.get("transactionId").getAsInt());
        }
        TransactionDataDAO transactionDataDAO = new TransactionDataDAOImpl();
        ArrayList<TransactionData> transactionDataList = new ArrayList();
        
        if(json.has("transactionData")) {
            JsonArray transactionArray = json.getAsJsonArray("transactionData");
            for(JsonElement transactionData : transactionArray) {
                JsonArray valuesArray = transactionData.getAsJsonObject().getAsJsonArray("values");
                for(JsonElement valueElement : valuesArray) {
                    String timestampValue = valueElement.getAsJsonObject().get("timestamp").getAsString();
                    JsonArray values = valueElement.getAsJsonObject().getAsJsonArray("values");
                    for(JsonElement value : values) {
                        transactionDataList.add(transactionDataDAO.createTransaction(value.getAsJsonObject(), timestampValue));
                    }
                }
            }
            setTransactionData(transactionDataList);
        } else {
            setTransactionData(new ArrayList<TransactionData>());
        }
    }

    @Override
    public String toJson() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
