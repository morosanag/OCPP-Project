/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.offnet.ocpp.controllerS;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.offnet.ocpp.client.ChargePointService;
import com.offnet.ocpp.client.ChargePointService_Service;
import com.offnet.ocpp.general.Constants;
import com.offnet.ocpp.response.Response;
import com.offnet.ocpp.soap.JaxWsHandlerResolver;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.ws.BindingProvider;

/**
 *
 * @author gabi
 */
public abstract class ControllerS {
    protected JsonObject content;
    protected String session_id;
    protected String serial_number;
    
    //[ 2 , callID , procedureName, request ]
    protected int callType;
    protected String callID;
    protected String procedureName;
    
    //[ 4 , callID , errorName , errorDesc , errorDetails ]
    protected String errorName = null;
    protected String errorDesc = null;
    protected JsonNode errorDetails;

    protected ChargePointService_Service service = new ChargePointService_Service();
    protected final ChargePointService port;

    public ChargePointService getPort() {
        return port;
    }
    
    public ChargePointService_Service getService() {
        return service;
    }

    public void setService(ChargePointService_Service service) {
        this.service = service;
    }
    
    public String getErrorName() {
        return errorName;
    }

    public JsonObject getContent() {
        return content;
    }

    public String getSerial_number() {
        return serial_number;
    }

    public void setSerial_number(String serial_number) {
        this.serial_number = serial_number;
    }

    public void setContent(JsonObject content) {
        this.content = content;
    }

    public String getSession_id() {
        return session_id;
    }

    public void setSession_id(String session_id) {
        this.session_id = session_id;
    }

    public int getCallType() {
        return callType;
    }

    public void setCallType(int callType) {
        this.callType = callType;
    }

    public String getCallID() {
        return callID;
    }

    public void setCallID(String callID) {
        this.callID = callID;
    }

    public String getProcedureName() {
        return procedureName;
    }

    public void setProcedureName(String procedureName) {
        this.procedureName = procedureName;
    }

    public void setErrorName(String errorName) {
        this.errorName = errorName;
    }

    public String getErrorDesc() {
        return errorDesc;
    }

    public void setErrorDesc(String errorDesc) {
        this.errorDesc = errorDesc;
    }

    public JsonNode getErrorDetails() {
        return errorDetails;
    }

    public void setErrorDetails(JsonNode errorDetails) {
        this.errorDetails = errorDetails;
    }
    
    public ControllerS(List<String> array, String session_id) {
        callType = Integer.parseInt(array.get(0));
        
        callID = array.get(1);
        
        if(array.size() == 4) {
            procedureName = array.get(2);
        } else {
            procedureName = "";
        }
        
        this.session_id = session_id;
        
        JsonParser parser = new JsonParser();
        if(array.size() == 4) {
            content = parser.parse(array.get(3)).getAsJsonObject();
        } else {
            content = parser.parse(array.get(2)).getAsJsonObject();
        }    
        if(content.has("chargePointSerialNumber")) {
            this.serial_number = content.get("chargePointSerialNumber").getAsString();
        }
        
        service.setHandlerResolver(new JaxWsHandlerResolver(serial_number));
        port = service.getChargePointServiceSoap12();
    }
    
    public void setEndPointLocation(String endpointURL) {
        /* Set NEW Endpoint Location */
        //String endpointURL = "http://" + stationIp + ":8080/" + stationId + "/ChargePointService";
        BindingProvider bp = (BindingProvider)port;
        bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, endpointURL);
    }
    
    public void catchError(int errorId) {
        setErrorName(Constants.ERROR_NAME[errorId]);
        setErrorDesc(Constants.ERROR_DESC[errorId]);
        //setErrorDetails(new JsonElement() {});
    }
    
    public String encapsulateResponse(Response response) {
        ArrayList response_list = new ArrayList();
        
        if(errorName == null) {
            //[ 2 , callID , procedureName, request ]
            response_list.add(3);
            response_list.add("\"" + callID + "\"");
            //response_list.add("\"" + Utilities.toggleType(procedureName) + "\"");
            response_list.add(response.toJson());
        } else {
            //[ 4 , callID , errorName , errorDesc , errorDetails ]
            response_list.add(4);
            response_list.add("\"" + callID + "\"");
            response_list.add("\"" + errorName + "\"");
            response_list.add("\"" + errorDesc + "\"");
            response_list.add("\"" + errorDetails.toString() + "\"");
        }
        return response_list.toString();
        
    }
   
    public abstract String processRequest();
    
    public String encapsulateResponse(Object obj, String callID) {
        ObjectMapper mapper = new ObjectMapper();
        
        String jsonInString = null;
        try {
            jsonInString = mapper.writeValueAsString(obj);
        } catch (JsonProcessingException ex) {
            Logger.getLogger(ResetController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        ArrayList response_list = new ArrayList();
        
        response_list.add(3);
        response_list.add("\"" + callID + "\"");
        response_list.add(jsonInString);
        
        return response_list.toString();
    }
}
