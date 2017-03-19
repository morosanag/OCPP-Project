package com.offnet.ocpp.websocket;

import com.offnet.ocpp.business.OCPPBusinessRemote;
import com.offnet.ocpp.controllerS.ChangeAvailabilityController;
import com.offnet.ocpp.controllerS.ControllerS;
import com.offnet.ocpp.controllerS.ControllerSFactory;
import com.offnet.ocpp.controllerS.GetDiagnosticsController;
import com.offnet.ocpp.controllerS.NotImplementedControllerS;
import com.offnet.ocpp.controllerS.UpdateFirmwareController;
import com.offnet.ocpp.general.Utilities_Websocket;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.ApplicationScoped;
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
import org.json.JSONObject;
 
/** 
 * @ServerEndpoint gives the relative name for the end point
 * This will be accessed via ws://localhost:8080/EchoChamber/echo
 * Where "localhost" is the address of the host,
 * "EchoChamber" is the name of the package
 * and "echo" is the address to access this class from the server
 */


@ApplicationScoped
@ServerEndpoint("/offnet/ocpp/server/{serverId}") 
public class ServerDispatcher {

    private static final org.apache.logging.log4j.Logger logger = LogManager.getLogger(ServerDispatcher.class);
    
    OCPPBusinessRemote oCPPBusiness = lookupOCPPBusinessRemote();

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
    public void onOpen(Session session, @PathParam("serverId") String serverId){
        ServerSessionHandler.getInstance().addSession(session);
        Device device = new Device();
        device.setSerial(serverId);
        device.setSession(session);
        device.setStatus("On");
        ServerSessionHandler.getInstance().addServer(device);
        
        try {
            session.getBasicRemote().sendText("Connection Established - " + serverId);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
 
    /**
     * When a user sends a message to the server, this method will intercept the message
     * and allow us to react to it. For now the message is read as a String.
     */
    @OnMessage
    public void onMessage(String message, Session session, @PathParam("stationId") String stationId){
        List<String> myList = null;
        try{
            myList = Utilities_Websocket.splitMessage(message);
            for(String lst : myList) {
                if(myList.indexOf(lst) == 3) {
                    JSONObject json = new JSONObject(lst);
                    if(json.has("chargePointSerialNumber")) {
                        stationId = json.getString("chargePointSerialNumber");
                    }
                }
            }
            
            logger.info("SERVER -> " + (stationId != null ? stationId : "SERVER") + " : " + message);
            
            String resp = "";
            
            ControllerS controllerS = ControllerSFactory.getControllerS(myList, session.getId());
            
            if((controllerS instanceof NotImplementedControllerS) || 
               (controllerS instanceof GetDiagnosticsController) ||
               (controllerS instanceof ChangeAvailabilityController) ||
               (controllerS instanceof UpdateFirmwareController)) {
                resp = oCPPBusiness.processComplexRequestS(myList, session.getId(), stationId);
            }
            
            if(!(controllerS instanceof NotImplementedControllerS)) {
                resp = controllerS.processRequest();
            } 
            
            if((controllerS instanceof GetDiagnosticsController) ||
               (controllerS instanceof ChangeAvailabilityController)) {
                List<String> response = Utilities_Websocket.splitMessage(resp);
                response.add(2, myList.get(2));
                oCPPBusiness.processComplexRequest(response, session.getId(), stationId);
            }
            
            logger.info((stationId != null ? stationId : "SERVER") + " -> " + "SERVER : " + resp);
            
            if(resp != null) {
                try {
                    session.getBasicRemote().sendText(resp);
                } catch (IOException ex) {
                    Logger.getLogger(ServerDispatcher.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } catch (Exception ex) {
            try {
                session.getBasicRemote().sendText("[4,\"" + myList.get(1) + "\",\"InternalError\",\"An internal error occured and the receiver is not able to complete the operation.\",{}]");
            } catch (IOException ex1) {
                Logger.getLogger(Dispatcher.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
        
    }
 
    /**
     * The user closes the connection.
     * 
     * Note: you can't send messages to the client from this method
     */
    @OnClose
    public void onClose(Session session, @PathParam("stationId") String serverId){
        
        Device device = ServerSessionHandler.getInstance().getServerBySession(session);
        ServerSessionHandler.getInstance().removeServer(device.getId());
        ServerSessionHandler.getInstance().removeSession(session);
        
    }
    
    @OnError
    public void onError(Throwable error) {
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
    
    
}