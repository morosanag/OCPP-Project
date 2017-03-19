/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.offnet.ocpp.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.offnet.ocpp.general.Constants;
import com.offnet.ocpp.request.Request;
import com.offnet.ocpp.response.Response;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author gabi
 */
public abstract class Controller implements Serializable{
    protected String content;
    protected String session_id;
    protected String serial_number;
    
    //[ 2 , callID , procedureName, request ]
    protected int callType;
    protected String callID;
    protected String procedureName;
    
    //[ 4 , callID , errorName , errorDesc , errorDetails ]
    protected String errorName = null;
    protected String errorDesc = null;
    protected String errorDetails;

    

    public String getErrorName() {
        return errorName;
    }

    public String getContent() {
        return content;
    }

    public String getSerial_number() {
        return serial_number;
    }

    public void setSerial_number(String serial_number) {
        this.serial_number = serial_number;
    }

    public void setContent(String content) {
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

    public String getErrorDetails() {
        return errorDetails;
    }

    public void setErrorDetails(String errorDetails) {
        this.errorDetails = errorDetails;
    }
    
    public Controller(List<String> array, String session_id) {
        
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
            content = parser.parse(array.get(3)).getAsJsonObject().toString();
        } else {
            content = parser.parse(array.get(2)).getAsJsonObject().toString();
        }    
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
    
    
   
    public abstract Response processRequest();
}
