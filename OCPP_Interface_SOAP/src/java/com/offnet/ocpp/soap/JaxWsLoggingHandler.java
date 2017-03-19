package com.offnet.ocpp.soap;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
 
import javax.xml.namespace.QName;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPHeader;
import javax.xml.soap.SOAPMessage;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
 
public class JaxWsLoggingHandler implements SOAPHandler<SOAPMessageContext> {

    private String chargeBoxIdentity;
    
    public JaxWsLoggingHandler(String chargeBoxIdentity) {
        this.chargeBoxIdentity = chargeBoxIdentity;
    }
    
    @Override
    public void close(MessageContext arg0) {
    }
 
    @Override
    public boolean handleFault(SOAPMessageContext arg0) {
        SOAPMessage message = arg0.getMessage();
        
        try {
            message.writeTo(System.out);
        } catch (SOAPException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }
 
    private void setPrefixRecursively(Node node, String prefix) {
        if(node.getChildNodes() == null || node.getChildNodes().getLength() == 0) {
            return ;
        }
        node.setPrefix(prefix);
        
        for(int i = 0; i < node.getChildNodes().getLength(); i++) {
            setPrefixRecursively(node.getChildNodes().item(i), prefix);
        }
    }
    
    private void processHeader(Node header) {
        NodeList childs = header.getChildNodes();
        List<Node> nodesToBeDeleted = new ArrayList<>();
        List<String> attributes = new ArrayList<>();

        for(int i = 0; i < childs.getLength(); i++) {
            if(childs.item(i).getNodeName().equalsIgnoreCase("Action") || childs.item(i).getNodeName().equalsIgnoreCase("MessageID")) {
                childs.item(i).setPrefix("wsa");
                    attributes.clear();
                    for(int j = 0; j < childs.item(i).getAttributes().getLength(); j++) {
                        attributes.add(childs.item(i).getAttributes().item(j).getNodeName());
                    }
                    for(String attribute : attributes) {
                        childs.item(i).getAttributes().removeNamedItem(attribute);
                    }
            } else {
                nodesToBeDeleted.add(childs.item(i));
            }
        }
        
        for(Node nodeToBeDeleted : nodesToBeDeleted) {
            header.removeChild(nodeToBeDeleted);
        }
    }
    
    
    @Override
    public boolean handleMessage(SOAPMessageContext arg0) {
        SOAPMessage message = arg0.getMessage();
        boolean isOutboundMessage = (Boolean) arg0.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);
        if (isOutboundMessage) {
            try{
                //get the soap message and envelope
                SOAPMessage soapMsg = arg0.getMessage();
                SOAPHeader header = soapMsg.getSOAPHeader();
                SOAPBody body = soapMsg.getSOAPBody();
                
                // process ENVELOPE
                SOAPEnvelope env = soapMsg.getSOAPPart().getEnvelope();
                if(body.hasChildNodes() && body.getFirstChild().hasAttributes()) {
                    env.addNamespaceDeclaration("ns", body.getFirstChild().getNamespaceURI());
                }
                env.removeNamespaceDeclaration("S");
                env.removeNamespaceDeclaration("env");
                env.setPrefix("soap");        
                
                // process HEADER
                header.addNamespaceDeclaration("wsa", "http://www.w3.org/2005/08/addressing");
                header.setPrefix("soap");
                if(body.hasChildNodes() && body.getFirstChild().hasAttributes()) {
                    header.addNamespaceDeclaration("ns", body.getFirstChild().getNamespaceURI());
                }
                
                processHeader(header);
                
                SOAPElement new_header = header.addChildElement("chargeBoxIdentity", "ns");
                new_header.setValue(chargeBoxIdentity);
                
                // process BODY
                body.setPrefix("soap");               
                body.removeNamespaceDeclaration("");
                body.getFirstChild().getAttributes().removeNamedItem("xmlns");
                setPrefixRecursively(body.getFirstChild(), "ns");
                
                soapMsg.saveChanges();
            } 
            catch (SOAPException e) {
                e.printStackTrace();
            }
        } else {
        }
        try {
            message.writeTo(System.out);
        } catch (SOAPException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }
 
    @Override
    public Set<QName> getHeaders() {
        return null;
    }
 
}