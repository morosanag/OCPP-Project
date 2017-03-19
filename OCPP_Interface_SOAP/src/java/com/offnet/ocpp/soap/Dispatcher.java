/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.offnet.ocpp.soap;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.offnet.ocpp.cdi.RequestCDI;
import com.offnet.ocpp.websocket.Device;
import com.offnet.ocpp.websocket.DeviceSessionHandler;
import java.io.IOException;
import java.util.Date;
import javax.annotation.Resource;

import javax.jws.WebService;
import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.JAXBException;
import javax.xml.bind.PropertyException;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.soap.SOAPException;
import javax.xml.ws.BindingType;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;
import ocpp.cs._2012._06.DataTransferStatus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author gabi
 */
@WebService(serviceName = "CentralSystemService", portName = "CentralSystemServiceSoap12", endpointInterface = "ocpp.cs._2012._06.CentralSystemService", targetNamespace = "urn://Ocpp/Cs/2012/06/", wsdlLocation = "WEB-INF/wsdl/Dispatcher/ocpp_centralsystemservice_1.5_final.wsdl")
@BindingType(value = "http://java.sun.com/xml/ns/jaxws/2003/05/soap/bindings/HTTP/")
public class Dispatcher {
    
    @Resource
    private WebServiceContext ctx;
    
    public ocpp.cs._2012._06.AuthorizeResponse authorize(ocpp.cs._2012._06.AuthorizeRequest parameters) throws IOException, SOAPException {
        
        RequestCDI requestCDI = new RequestCDI(ctx);
        
        requestCDI.printParametersFromHeaders();
        
        ocpp.cs._2012._06.AuthorizeResponse response = new ocpp.cs._2012._06.AuthorizeResponse();
        response = requestCDI.process(parameters, response, response.getClass());
        
        return response;
        
    }
    
    public ocpp.cs._2012._06.StartTransactionResponse startTransaction(ocpp.cs._2012._06.StartTransactionRequest parameters) throws IOException, SOAPException {
        
        RequestCDI requestCDI = new RequestCDI(ctx);
        
        requestCDI.printParametersFromHeaders();
        
        ocpp.cs._2012._06.StartTransactionResponse response = new ocpp.cs._2012._06.StartTransactionResponse();
        response = requestCDI.process(parameters, response, response.getClass());
        
        return response;
        
    }
    
    public ocpp.cs._2012._06.StopTransactionResponse stopTransaction(ocpp.cs._2012._06.StopTransactionRequest parameters) throws IOException, SOAPException {
        
        RequestCDI requestCDI = new RequestCDI(ctx);
        
        requestCDI.printParametersFromHeaders();
        
        ocpp.cs._2012._06.StopTransactionResponse response = new ocpp.cs._2012._06.StopTransactionResponse();
        response = requestCDI.process(parameters, response, response.getClass());
        
        return response;
        
    }
    
    public ocpp.cs._2012._06.HeartbeatResponse heartbeat(ocpp.cs._2012._06.HeartbeatRequest parameters) throws DatatypeConfigurationException, SOAPException, PropertyException, JAXBException, JsonProcessingException, IOException {
        
        RequestCDI requestCDI = new RequestCDI(ctx);
        
        requestCDI.printParametersFromHeaders();
        
        ocpp.cs._2012._06.HeartbeatResponse response = new ocpp.cs._2012._06.HeartbeatResponse();
        response = requestCDI.process(parameters, response, response.getClass());
        
        return response;
        
    }
    
    public ocpp.cs._2012._06.MeterValuesResponse meterValues(ocpp.cs._2012._06.MeterValuesRequest parameters) throws IOException, SOAPException {
        
        RequestCDI requestCDI = new RequestCDI(ctx);
        
        requestCDI.printParametersFromHeaders();
        
        ocpp.cs._2012._06.MeterValuesResponse response = new ocpp.cs._2012._06.MeterValuesResponse();
        response = requestCDI.process(parameters, response, response.getClass());
        
        return response;
        
    }
    
    public ocpp.cs._2012._06.BootNotificationResponse bootNotification(ocpp.cs._2012._06.BootNotificationRequest parameters) throws IOException, SOAPException {
        
        RequestCDI requestCDI = new RequestCDI(ctx);
        
        requestCDI.sendMessageToServers(parameters, false);
        
        requestCDI.printParametersFromHeaders();
        
        if(parameters.getChargeBoxSerialNumber() == null || parameters.getChargeBoxSerialNumber().equals("")) {
            parameters.setChargeBoxSerialNumber(requestCDI.getChargeBoxIdentity());
        }
        if(parameters.getChargePointSerialNumber() == null || parameters.getChargePointSerialNumber().equals("")) {
            parameters.setChargePointSerialNumber(requestCDI.getChargeBoxIdentity());
        }
        
        ocpp.cs._2012._06.BootNotificationResponse response = new ocpp.cs._2012._06.BootNotificationResponse();
        response = requestCDI.process(parameters, response, response.getClass());
        
        Device device = new Device();
        
        MessageContext mc = ctx.getMessageContext();
        HttpServletRequest req = (HttpServletRequest)mc.get(MessageContext.SERVLET_REQUEST); 
        
        device.setAddress(requestCDI.getWsAddress());
        device.setSerial(parameters.getChargePointSerialNumber());
        device.setLastUpdate(new Date());
        device.setStatus("On");
        DeviceSessionHandler.getInstance().addDevice(device);
        
        requestCDI.sendMessageToServers(response, true);
        
        return response;
        
    }
    
    public ocpp.cs._2012._06.StatusNotificationResponse statusNotification(ocpp.cs._2012._06.StatusNotificationRequest parameters) throws IOException, SOAPException {
        
        RequestCDI requestCDI = new RequestCDI(ctx);
        
        requestCDI.printParametersFromHeaders();
        
        ocpp.cs._2012._06.StatusNotificationResponse response = new ocpp.cs._2012._06.StatusNotificationResponse();
        response = requestCDI.process(parameters, response, response.getClass());
        
        return response;
        
    }
    
    public ocpp.cs._2012._06.FirmwareStatusNotificationResponse firmwareStatusNotification(ocpp.cs._2012._06.FirmwareStatusNotificationRequest parameters) throws IOException, SOAPException {
        
        RequestCDI requestCDI = new RequestCDI(ctx);
        
        requestCDI.printParametersFromHeaders();
        
        ocpp.cs._2012._06.FirmwareStatusNotificationResponse response = new ocpp.cs._2012._06.FirmwareStatusNotificationResponse();
        response = requestCDI.process(parameters, response, response.getClass());
        
        return response;
        
    }
    
    public ocpp.cs._2012._06.DiagnosticsStatusNotificationResponse diagnosticsStatusNotification(ocpp.cs._2012._06.DiagnosticsStatusNotificationRequest parameters) throws IOException, SOAPException {
        
        RequestCDI requestCDI = new RequestCDI(ctx);
        
        requestCDI.printParametersFromHeaders();
        
        ocpp.cs._2012._06.DiagnosticsStatusNotificationResponse response = new ocpp.cs._2012._06.DiagnosticsStatusNotificationResponse();
        response = requestCDI.process(parameters, response, response.getClass());
        
        return response;
        
    }
    
    public ocpp.cs._2012._06.DataTransferResponse dataTransfer(ocpp.cs._2012._06.DataTransferRequest parameters) {
        
        ocpp.cs._2012._06.DataTransferResponse response = new ocpp.cs._2012._06.DataTransferResponse();
        response.setStatus(DataTransferStatus.ACCEPTED);
        response.setData("");
        return response;
    }
    
}
