/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.offnet.ocpp.cdi;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.offnet.ocpp.business.OCPPBusinessRemote;
import com.offnet.ocpp.websocket.ServerSessionHandler;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.xml.soap.SOAPException;
import javax.xml.ws.WebServiceContext;
import org.apache.logging.log4j.LogManager;

/**
 *
 * @author gabi
 */
public class RequestCDI {
    
    private static final org.apache.logging.log4j.Logger logger = LogManager.getLogger(RequestCDI.class);
    
    OCPPBusinessRemote oCPPBusiness = lookupOCPPBusinessRemote();
    
    private static final String HEADERLIST_CLASS = "com.sun.xml.ws.api.message.HeaderList";
    private static final String WS_ADDRESS_PARAMETER = "com.sun.xml.ws.api.addressing.from";
    
    private String getParameterFromHeaders(String headerParameter) {
        
        Map<String, Object> map = ctx.getMessageContext();
        
        for (Map.Entry<String, Object> entry : map.entrySet())  {
            if(entry.getKey().equals(HEADERLIST_CLASS)) {
                com.sun.xml.ws.api.message.HeaderList headersList = (com.sun.xml.ws.api.message.HeaderList) entry.getValue();
                
                Iterator iterator = headersList.getHeaders();
                while(iterator.hasNext()) {
                    com.sun.xml.ws.api.message.Header header = (com.sun.xml.ws.api.message.Header)iterator.next();
                    if(header.getLocalPart().equalsIgnoreCase(headerParameter)) {
                        return header.getStringContent();
                    }
                }
            }
        }
        
        return null;
    }
    
    public void printParametersFromHeaders() throws SOAPException {
        
        Map<String, Object> map = ctx.getMessageContext();
        
        for (Map.Entry<String, Object> entry : map.entrySet())  {
            if(entry.getKey().equals(HEADERLIST_CLASS)) {
                com.sun.xml.ws.api.message.HeaderList headersList = (com.sun.xml.ws.api.message.HeaderList) entry.getValue();
                
                Iterator iterator = headersList.getHeaders();
                while(iterator.hasNext()) {
                    com.sun.xml.ws.api.message.Header header = (com.sun.xml.ws.api.message.Header)iterator.next();
                }
            }
        }
    }
    
    
    public String getWsAddress() {
        Iterator iterator =  ctx.getMessageContext().entrySet().iterator();
        while (iterator.hasNext())
        {
            Map.Entry entry = (Map.Entry)iterator.next();
            if(entry.getKey().equals(WS_ADDRESS_PARAMETER)) {
                
                String xml = entry.getValue().toString();
                return getWSAddress(xml, "wsa:Address");
            }
        }
        return "";
    }
    
    public static String getWSAddress(String xml, String xmlField) {
        int indexStartField = xml.indexOf(xmlField);
        if(indexStartField == -1) {
            return "";
        }
        xml = xml.substring(indexStartField + 1, xml.length());
        int squareBracketIndex = xml.indexOf(">");
        if(squareBracketIndex == -1) {
            return "";
        }
        xml = xml.substring(squareBracketIndex + 1, xml.length());
        int indexStopField = xml.indexOf("<");
        if(indexStopField == -1) {
            return "";
        }
        return xml.substring(0, indexStopField);
    }
    
    private WebServiceContext ctx;
    
    public RequestCDI(WebServiceContext ctx) {
        this.ctx = ctx;
    }
    
    public String getChargeBoxIdentity() {
        
        return getParameterFromHeaders("chargeBoxIdentity");
        
    }
    
    public String getMessageId() {
        
        String messageId = getParameterFromHeaders("MessageID");
        
        return messageId.substring(5, messageId.length());
        
    }
    
    public String getAction() {
        
        String actionString = getParameterFromHeaders("Action");
        
        return actionString.substring(1, actionString.length());
        
    }
    
    public String processRequest(List<String> array, String session_id, String stationId) {
        return oCPPBusiness.processRequest(array, session_id, stationId);
    }
    
    private OCPPBusinessRemote lookupOCPPBusinessRemote() {
        try {
            Context c = new InitialContext();
            return (OCPPBusinessRemote) c.lookup("java:global/OCPP_Business/OCPP_Business-ejb/OCPPBusiness!com.offnet.ocpp.business.OCPPBusinessRemote");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
    
    public <T,R> R process(T request, R response, Class classResponse) throws JsonProcessingException, IOException {
        
        
        ObjectMapper mapper = new ObjectMapper();
        
        //Object to JSON in String
        String jsonInString = mapper.writeValueAsString(request);
        
        ArrayList<String> soapParameters = new ArrayList<>();
        soapParameters.add("2");
        soapParameters.add(getMessageId());
        soapParameters.add(getAction());
        soapParameters.add(jsonInString);
        
        
        logger.info(getChargeBoxIdentity() + " -> " + "SERVER : " + soapParameters);
        
        String responses = processRequest(soapParameters, getMessageId(), getChargeBoxIdentity());
        
        
        R heartbeatResponse = (R) mapper.readValue(responses, classResponse);
        
        logger.info("SERVER -> " + getChargeBoxIdentity() + " : " + "[ 3, " + getMessageId() + ", " + responses + "]");
        
        return heartbeatResponse;
        
    }
    
    public <T> void sendMessageToServers(T message, boolean toStation) {
        
        String stationId = getChargeBoxIdentity();
        
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = null;
        try {
            json = ow.writeValueAsString(message);
        } catch (JsonProcessingException ex) {
            Logger.getLogger(RequestCDI.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        String encapsulatedResponse;
        
        if(toStation) {
            encapsulatedResponse = "[3,\"" + getMessageId() + "\", " + json + "]"; 
            ServerSessionHandler.getInstance().sendMessageToServers("ToStation " +  stationId + " :" + encapsulatedResponse);
        } else {
            encapsulatedResponse = "[2,\"" + getMessageId() + "\", \"" + getAction() + "\", "+ json + "]"; 
            ServerSessionHandler.getInstance().sendMessageToServers("Station " + stationId + " :" + encapsulatedResponse);
        }
    }
    
    
    
}
