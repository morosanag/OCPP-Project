
package com.offnet.ocpp.request;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.offnet.ocpp.dao.TransactionDataDAO;
import com.offnet.ocpp.dao.TransactionDataDAOImpl;
import com.offnet.ocpp.entity.TransactionData;
import com.offnet.ocpp.general.Utilities_Business;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * Defines the MeterValues.req PDU
 * 
 * <p>Java class for MeterValuesRequest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="MeterValuesRequest">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="connectorId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="transactionId" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="values" type="{urn://Ocpp/Cs/2012/06/}MeterValue" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MeterValuesRequest", propOrder = {
    "connectorId",
    "transactionId",
    "values"
})
public class MeterValuesRequest extends Request{

    protected int connectorId;
    protected Integer transactionId;
    protected List<TransactionData> transactionData;

    /**
     * Gets the value of the connectorId property.
     * 
     */
    public int getConnectorId() {
        return connectorId;
    }

    /**
     * Sets the value of the connectorId property.
     * 
     */
    public void setConnectorId(int value) {
        this.connectorId = value;
    }

    /**
     * Gets the value of the transactionId property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getTransactionId() {
        return transactionId;
    }

    /**
     * Sets the value of the transactionId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setTransactionId(Integer value) {
        this.transactionId = value;
    }

    /**
     * Gets the value of the values property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the values property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getValues().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link MeterValue }
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
    
    public MeterValuesRequest (JsonObject json) {
        
        if(json.has("transactionId")) {
            setTransactionId(json.get("transactionId").getAsInt());
        }
        
        setConnectorId(json.get("connectorId").getAsInt());
        
        //if(Utilities.checkNull(json, "reservationId")) { 
        if(Utilities_Business.checkNull(json, "values")) { 
            TransactionDataDAO transactionDataDAO = new TransactionDataDAOImpl();

            ArrayList<TransactionData> transactionDataList = new ArrayList();
            JsonArray transactionArray = json.getAsJsonArray("values");

         //   try {
            for(JsonElement valueElement : transactionArray) {
                String timestampValue = valueElement.getAsJsonObject().get("timestamp").getAsString();
                //SOAP/HTTP version
                JsonArray values = valueElement.getAsJsonObject().getAsJsonArray("value");
                
                // WebSocket/REST version
                if(values == null) {
                    values = valueElement.getAsJsonObject().getAsJsonArray("values");
                }
                
                for(JsonElement value : values) {
                    transactionDataList.add(transactionDataDAO.createTransaction(value.getAsJsonObject(), timestampValue));
                }
            }
           /* } catch(Exception ex) {
            }*/
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
