
package com.offnet.ocpp.request;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.offnet.ocpp.general.Utilities_Websocket;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import org.json.JSONException;
import org.json.JSONObject;


/**
 * Defines the SendLocalList.req PDU
 * 
 * <p>Java class for SendLocalListRequest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SendLocalListRequest">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="updateType" type="{urn://Ocpp/Cp/2012/06/}UpdateType"/>
 *         &lt;element name="listVersion" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="localAuthorisationList" type="{urn://Ocpp/Cp/2012/06/}AuthorisationData" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="hash" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SendLocalListRequest", propOrder = {
    "updateType",
    "listVersion",
    "localAuthorisationList",
    "hash"
})
public class SendLocalListRequest extends Request {

    @XmlElement(required = true)
    @XmlSchemaType(name = "string")
    protected String updateType;
    protected int listVersion;
    protected List<AuthorisationData> localAuthorisationList;
    protected String hash;

    /**
     * Gets the value of the updateType property.
     * 
     * @return
     *     possible object is
     *     {@link UpdateType }
     *     
     */
    public String getUpdateType() {
        return updateType;
    }

    /**
     * Sets the value of the updateType property.
     * 
     * @param value
     *     allowed object is
     *     {@link UpdateType }
     *     
     */
    public void setUpdateType(String value) {
        this.updateType = value;
    }

    /**
     * Gets the value of the listVersion property.
     * 
     */
    public int getListVersion() {
        return listVersion;
    }

    /**
     * Sets the value of the listVersion property.
     * 
     */
    public void setListVersion(int value) {
        this.listVersion = value;
    }

    /**
     * Gets the value of the localAuthorisationList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the localAuthorisationList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getLocalAuthorisationList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link AuthorisationData }
     * 
     * 
     */
    public List<AuthorisationData> getLocalAuthorisationList() {
        if (localAuthorisationList == null) {
            localAuthorisationList = new ArrayList<AuthorisationData>();
        }
        return this.localAuthorisationList;
    }
    
    public void setLocalAuthorisationList(List<AuthorisationData> localAuthorisationList) {
        this.localAuthorisationList = localAuthorisationList;
    }

    /**
     * Gets the value of the hash property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHash() {
        return hash;
    }

    /**
     * Sets the value of the hash property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHash(String value) {
        this.hash = value;
    }
    
    public SendLocalListRequest() {
        super.setProcedureName("SendLocalList");
    }

    public SendLocalListRequest (JsonObject json) {
        setProcedureName("SendLocalList");
        setDeviceSerial(json.get("chargePointSerialNumber").getAsString());
        setUpdateType(json.get("updateType").getAsString());
        
        List<AuthorisationData> authorisationDatas = new ArrayList<AuthorisationData>();
        
        JsonArray authList = json.get("localAuthorisationList").getAsJsonArray();
        for(int i = 0; i < authList.size(); i++) {
            AuthorisationData authorisationData = new AuthorisationData();
            authorisationData.setIdTag(authList.get(i).getAsJsonObject().get("idTag").getAsString());
            IdTagInfo idTagInfo = new IdTagInfo();
            idTagInfo.setStatus(new AuthorizationStatus(authList.get(i).getAsJsonObject().get("idTagInfo").getAsJsonObject().get("status").getAsString()));
            idTagInfo.setParentIdTag(authList.get(i).getAsJsonObject().get("idTagInfo").getAsJsonObject().get("parentIdTag").getAsString());
            idTagInfo.setExpiryDate(Utilities_Websocket.formatCalendar(authList.get(i).getAsJsonObject().get("idTagInfo").getAsJsonObject().get("expiryDate").getAsString()));
            authorisationData.setIdTagInfo(idTagInfo);
            authorisationDatas.add(authorisationData);
            
        }
            
        setLocalAuthorisationList(authorisationDatas);
            
        if(json.has("listVersion")) {
            setListVersion(json.get("listVersion").getAsInt());
        }
            
        if(json.has("hash")) {
            setHash(json.get("hash").getAsString());
        }    
        
    }

    @Override
    public String toJson() {
        JSONObject obj = new JSONObject();
        
        Utilities_Websocket.addToJSON(obj, "updateType", getUpdateType(), true);
        Utilities_Websocket.addToJSON(obj, "localAuthorisationList", getLocalAuthorisationList(), true);
        Utilities_Websocket.addToJSON(obj, "listVersion", getListVersion(), false);
        Utilities_Websocket.addToJSON(obj, "hash", getHash(), false);
        */
        
        ObjectMapper mapper = new ObjectMapper();
        String jsonInString =null;
        try {
            jsonInString = mapper.writeValueAsString(this);
        } catch (JsonProcessingException ex) {
            Logger.getLogger(SendLocalListRequest.class.getName()).log(Level.SEVERE, null, ex);
        }
        JSONObject json = new JSONObject();
        try {
            json = new JSONObject(jsonInString);
        } catch (JSONException ex) {
            Logger.getLogger(SendLocalListRequest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            Long date = json.getJSONArray("localAuthorisationList").getJSONObject(0).getJSONObject("idTagInfo").getLong("expiryDate");
            Date date2 = new Date(date);
            String convertedDate = Utilities_Websocket.formatData(Utilities_Websocket.fromDateToCalendar(date2));
            json.getJSONArray("localAuthorisationList").getJSONObject(0).getJSONObject("idTagInfo").put("expiryDate", convertedDate);
            
        } catch(NullPointerException ex) {
            
        } catch (JSONException ex) {
            Logger.getLogger(SendLocalListRequest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return json.toString();
    }
    
    @Override
    public String toString() {
        return toJson();
    }

}
