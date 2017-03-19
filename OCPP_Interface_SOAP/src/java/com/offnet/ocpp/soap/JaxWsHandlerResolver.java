package com.offnet.ocpp.soap;




import java.util.ArrayList;
import java.util.List;
 
import javax.xml.ws.handler.Handler;
import javax.xml.ws.handler.HandlerResolver;
import javax.xml.ws.handler.PortInfo;
 
public class JaxWsHandlerResolver implements HandlerResolver {

    private String chargeBoxIdentity;
    
    public JaxWsHandlerResolver(String chargeBoxIdentity) {
        this.chargeBoxIdentity = chargeBoxIdentity;
    }
    
    @SuppressWarnings("rawtypes")
    @Override
    public List<Handler> getHandlerChain(PortInfo arg0) {
        List<Handler> hchain = new ArrayList<Handler>();
        hchain.add(new JaxWsLoggingHandler(chargeBoxIdentity));
        return hchain;
    }
 
}