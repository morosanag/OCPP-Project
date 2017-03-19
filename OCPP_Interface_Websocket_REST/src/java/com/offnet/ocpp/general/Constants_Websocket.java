/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.offnet.ocpp.general;

/**
 *
 * @author gabi
 */
public class Constants_Websocket {
    
    public static int HEARTBEAT_INTERVAL = 300;
    
    public static String DATE_FORMAT = "yyyy-MM-dd'T'hh:mm:ss'Z'";
    public static String DATE_FORMAT_MILIS = "yyyy-MM-dd'T'hh:mm:ss.SSS'Z'";
    //[ 4 , callID , errorName , errorDesc , errorDetails ]
    public static String[] ERROR_NAME = {
        "NotImplemented",
        "NotSupported",
        "InternalError",
        "ProtocolError",
        "SecurityError",
        "FormationViolation",
        "PropertyConstraintViolation",
        "OccurenceConstraintViolation",
        "TypeConstraintViolation",
        "GenericError"
    };
    
    public static String[] ERROR_DESC = {
        "Method name is not recognized.",
        "Method name is recognized but not supported by the receiver.",
        "An internal error occured and the receiver is not able to complete the operation.",
        "Sender's message does not comply with protocol specification.",
        "Sender failed authentication or is not authorized to use the requested operation.",
        "Sender's message is not well-formed.",
        "A property name in sender's message is not conform to the PDU structure.",
        "Sender's message violates occurence constraints.",
        "Sender's message violates data type constraints.",
        "Other error."
    };
    
    public static enum CHARGE_POINT_STATUS {
        Available,
        Occupied,
        Faulted,
        Unavailable,
        Reserved
    };
    
    public static enum TOKEN_STATUS {
        Accepted,
        Blocked,
        Expired,
        Invalid,
        ConcurrentTx
    };
    
    public static enum CHANGE_STATUS {
        Accepted,
        Rejected,
        Scheduled
    };
   
    public static int INVALID_ID = -1;
    public static String INVALID_ID_STR = "-1";
}
