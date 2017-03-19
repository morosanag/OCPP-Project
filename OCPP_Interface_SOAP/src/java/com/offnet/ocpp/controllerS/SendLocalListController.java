/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.offnet.ocpp.controllerS;

import com.google.gson.JsonArray;
import com.offnet.ocpp.client.AuthorisationData;
import com.offnet.ocpp.client.AuthorizationStatus;
import com.offnet.ocpp.client.IdTagInfo;
import com.offnet.ocpp.client.SendLocalListRequest;
import com.offnet.ocpp.client.SendLocalListResponse;
import com.offnet.ocpp.client.UpdateType;
import com.offnet.ocpp.general.Utilities;
import com.offnet.ocpp.websocket.Device;
import com.offnet.ocpp.websocket.DeviceSessionHandler;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author gabi
 */
public class SendLocalListController extends ControllerS{
    
    public SendLocalListController(List<String> array, String session_id) {
        super(array, session_id);
        super.setProcedureName("SendLocalList");
    }
    
    @Override
    public String processRequest() {
        
        SendLocalListRequest sendLocalListRequest = new SendLocalListRequest();
        
        Device station = DeviceSessionHandler.getInstance().getDeviceBySerial(super.content.get("chargePointSerialNumber").getAsString());
        
        sendLocalListRequest.setUpdateType(UpdateType.fromValue(super.content.get("updateType").getAsString()));
        sendLocalListRequest.setListVersion(super.content.get("listVersion").getAsInt());
        
        if(super.content.has("hash")) {
            sendLocalListRequest.setHash(super.content.get("hash").getAsString());
        }
        
        if(super.content.has("localAuthorisationList")) {
            JsonArray authList = super.content.get("localAuthorisationList").getAsJsonArray();
            
            for(int i = 0; i < authList.size(); i++) {
                AuthorisationData authorisationData = new AuthorisationData();
                authorisationData.setIdTag(authList.get(i).getAsJsonObject().get("idTag").getAsString());
                IdTagInfo idTagInfo = new IdTagInfo();
                idTagInfo.setStatus(AuthorizationStatus.fromValue(authList.get(i).getAsJsonObject().get("idTagInfo").getAsJsonObject().get("status").getAsString()));
                idTagInfo.setParentIdTag(authList.get(i).getAsJsonObject().get("idTagInfo").getAsJsonObject().get("parentIdTag").getAsString());
                idTagInfo.setExpiryDate(Utilities.formatXMLCalendar(authList.get(i).getAsJsonObject().get("idTagInfo").getAsJsonObject().get("expiryDate").getAsString()));
                authorisationData.setIdTagInfo(idTagInfo);
                sendLocalListRequest.getLocalAuthorisationList().add(authorisationData);
                
            }
        }
        
        setEndPointLocation(station.getAddress());
        
        SendLocalListResponse response = port.sendLocalList(sendLocalListRequest);
        
        return encapsulateResponse(response, callID);
    }
    
}
