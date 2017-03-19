package com.offnet.ocpp.websocket;

import com.offnet.ocpp.general.Utilities_Websocket;

import java.io.IOException;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.SessionScoped;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
 
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import org.apache.logging.log4j.LogManager;
 
/** 
 * @ServerEndpoint gives the relative name for the end point
 * This will be accessed via ws://localhost:8080/EchoChamber/echo
 * Where "localhost" is the address of the host,
 * "EchoChamber" is the name of the package
 * and "echo" is the address to access this class from the server
 */  


@ApplicationScoped
@ServerEndpoint("/offnet/ocpp/{stationId}") 
public class Dispatcher implements Serializable{

    private static final org.apache.logging.log4j.Logger logger = LogManager.getLogger(Dispatcher.class);
    
    com.offnet.ocpp.business.OCPPBusinessRemote oCPPBusiness1 = lookupOCPPBusinessRemote1();

    //private Session session;
    /*@Inject
    public static DeviceSessionHandler sessionHandler;
    */
    
    //static org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(Dispatcher.class.getName());
    /**
     * @OnOpen allows us to intercept the creation of a new session.
     * The session class allows us to send data to the user.
     * In the method onOpen, we'll let the user know that the handshake was 
     * successful.
     */
    @OnOpen
    public void onOpen(Session session, @PathParam("stationId") String stationId){
        
        
        DeviceSessionHandler.getInstance().addSession(session);
        Device device = new Device();
        device.setSerial(stationId);
        device.setSession(session);
        device.setLastUpdate(new Date());
        //this.session = session;
        device.setStatus("On");
        DeviceSessionHandler.getInstance().addDevice(device);
        
        try {
            session.getBasicRemote().sendText("Connection Established - " + stationId);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
 
    /**
     * When a user sends a message to the server, this method will intercept the message
     * and allow us to react to it. For now the message is read as a String.
     */
    @OnMessage
    public void onMessage(String message, Session session, @PathParam("stationId") String stationId_path){
        
        
        
        String stationId = DeviceSessionHandler.getInstance().getDeviceBySession(session).getSerial();
        
        
        DeviceSessionHandler.getInstance().getDeviceBySerial(stationId).setLastUpdate(new Date());
        
        logger.info(stationId+ " -> " + "SERVER : " + message);
        
        //this.session = session;
        List<String> myList = null;
        try{
            ServerSessionHandler.getInstance().sendMessageToServers("Station " + stationId + " :" + message);
            
            myList = Utilities_Websocket.splitMessage(message);
            for(String lst : myList) {
            }
           
            String response = oCPPBusiness1.processComplexRequest(myList, session.getId(), stationId);
            
            if(response == null) {
                return;
            }
       
            try {
                session.getBasicRemote().sendText(response);
                
                logger.info("SERVER -> " + stationId+ " : " + response);
                
                ServerSessionHandler.getInstance().sendMessageToServers("ToStation " +  stationId + " :" + response);
            } catch (IOException ex) {
                Logger.getLogger(Dispatcher.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (Exception ex) {
            Logger.getLogger(Exception.class.getName()).log(Level.SEVERE, null, ex);
            try {
                session.getBasicRemote().sendText("[4,\"" + myList.get(1) + "\",\"InternalError\",\"An internal error occured and the receiver is not able to complete the operation.\",{}]");
                logger.info("SERVER -> " + stationId+ " : " + "[4,\"" + myList.get(1) + "\",\"InternalError\",\"An internal error occured and the receiver is not able to complete the operation.\",{}]");
                ServerSessionHandler.getInstance().sendMessageToServers("TOERROR:" + stationId + ":" + "[4,\"" +  myList.get(1) + "\",\"InternalError\",\"An internal error occured and the receiver is not able to complete the operation.\",{}]");
            } catch (IOException ex1) {
                Logger.getLogger(Dispatcher.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
    }
 
    /**
     * The user closes the connection.
     * 
     * Note: you can't send messages to the client from this method
     * @param session
     * @param stationId
     */
    @OnClose
    public void onClose(Session session, @PathParam("stationId") String stationId){

        
        Device device = DeviceSessionHandler.getInstance().getDeviceBySession(session);
        DeviceSessionHandler.getInstance().removeDevice(device.getId());
        DeviceSessionHandler.getInstance().removeSession(session);
        
    }
    
    @OnError
    public void onError(Throwable error) {
        
   //     Device device = DeviceSessionHandler.getInstance().getDeviceBySession(session);
   //     DeviceSessionHandler.getInstance().removeDevice(device.getId());
 //       DeviceSessionHandler.getInstance().removeSession(session);
        
    }


    private com.offnet.ocpp.business.OCPPBusinessRemote lookupOCPPBusinessRemote1() {
        try {
            Context c = new InitialContext();
            return (com.offnet.ocpp.business.OCPPBusinessRemote) c.lookup("java:global/OCPP_Business/OCPP_Business-ejb/OCPPBusiness!com.offnet.ocpp.business.OCPPBusinessRemote");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
}