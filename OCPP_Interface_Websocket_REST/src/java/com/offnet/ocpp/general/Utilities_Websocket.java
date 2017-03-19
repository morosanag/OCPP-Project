/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.offnet.ocpp.general;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.offnet.ocpp.request.Request;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Hashtable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author gabi
 */
public class Utilities_Websocket {
    
    public static Hashtable<String,Request> pendingRequests = new Hashtable<String,Request>();
    
    /*
    private static SecureRandom random = new SecureRandom();
    
    
    public static String getCallID() {
        return new BigInteger(130, random).toString(32);
    }
    */
    /*
    public static ArrayList<String> splitMessage(String message2) {
        
        String message = message2;
        
        message = message.trim();
        message = message.substring(1, message.length() - 1);
        
        
        ArrayList<String> list = new ArrayList<String>();
        //int index_1 = message.indexOf('[');
        //int index_2 = message.lastIndexOf(']');
        //message = message.substring(index_1 + 1, index_2);
        int semicolon_index = message.indexOf('{');
        
        String[] words = message.substring(0, semicolon_index).replaceAll(" ", "").split(",");
        
        for(int i = 0; i < words.length; i++) {
            if(i == 1 || i == 2) {
                list.add(words[i].replaceAll("\"", ""));
            } else {
                list.add(words[i]);
            }
        }
        
        list.add(message.substring(semicolon_index, message.length()));
        return list;
    }*/
    
    public static ArrayList<String> splitMessage(String message2) {
        
        String message = message2;
        
        message = message.trim();
        message = message.substring(1, message.length() - 1);
        
        
        ArrayList<String> list = new ArrayList<String>();
        //int index_1 = message.indexOf('[');
        //int index_2 = message.lastIndexOf(']');
        //message = message.substring(index_1 + 1, index_2);
        int semicolon_index = message.indexOf('{');
        
        String[] words = message.substring(0, semicolon_index).replaceAll(" ", "").split(",");
        
        for(int i = 0; i < words.length; i++) {
            if(i == 1 || i == 2) {
                list.add(words[i].replaceAll("\"", ""));
            } else {
                list.add(words[i]);
            }
        }
        
        list.add(message.substring(semicolon_index, message.length()));
        return list;
    }
    
    public static String toggleType(String request) {
        if(request.contains("Request")) {
            return request.replace("Request", "Response"); 
        } 
        return request.replace("Response", "Request");
    } 
    
    public static String formatData(GregorianCalendar calendar) {
        //"2013-02-01T15:09:18Z"
        SimpleDateFormat fmt = new SimpleDateFormat(Constants_Websocket.DATE_FORMAT);
        //{"status": "Accepted", "currentTime": "2013-02-01T15:09:18Z", "heartbeatInterval": 1200 }
        //SimpleDateFormat fmt = new SimpleDateFormat("dd-MMM-yyyy");
        fmt.setCalendar(calendar);
        String dateFormatted = fmt.format(calendar.getTime());
        return dateFormatted;
    }
    
    public static GregorianCalendar formatCalendar(String input) {
        DateFormat format = new SimpleDateFormat(Constants_Websocket.DATE_FORMAT);
        Date       date = null;
        try {
            date = format.parse(input);  
        } catch (ParseException ex) {
            Logger.getLogger(Utilities_Websocket.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NumberFormatException ex) {
            format = new SimpleDateFormat(Constants_Websocket.DATE_FORMAT_MILIS);
            try {
                
                date = format.parse(input);
                
            } catch (ParseException ex1) {
                Logger.getLogger(Utilities_Websocket.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
        GregorianCalendar   calendar = new GregorianCalendar();

        calendar.setTime( date );
        return calendar;
    }
   
    
    public static GregorianCalendar fromDateToCalendar(Date date) {
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(date);
        return cal;
    }
    
    public static void addToJSON (JSONObject json, String key, Object value, boolean checkNull) {
        
        try {
            if(checkNull) {
                if(value != null) {
                    json.put(key, value);
                }
            } else {
                json.put(key, value);
            } 
        } catch (JSONException ex) {
            Logger.getLogger(Utilities_Websocket.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public static void addToJSON2 (JSONObject json, String key, Object value, boolean checkNull) {
        
        try {
            if(checkNull) {
                if(value != null) {
                    json.put(key, value.toString());
                }
            } else {
                json.put(key, value.toString());
            } 
        } catch (JSONException ex) {
            Logger.getLogger(Utilities_Websocket.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public static JsonObject fromStringToJsonObject(String jsonStr) {
        JsonParser parser = new JsonParser();
        JsonObject o = parser.parse(jsonStr).getAsJsonObject();
        return o;
    }
    
    public static Long formatDataToLong(GregorianCalendar calendar) {
        return calendar.getTimeInMillis();
    }
    
    public static boolean checkNull(JsonObject json, String param) {
            
        return json.has(param) && !json.get(param).toString().equals("null");
    
    }
    
    public static XMLGregorianCalendar formatXMLCalendar(String input) {
        GregorianCalendar c = formatCalendar(input);
        XMLGregorianCalendar date2 = null;
        try {
            date2 = DatatypeFactory.newInstance().newXMLGregorianCalendar(c);
        } catch (DatatypeConfigurationException ex) {
            Logger.getLogger(Utilities_Websocket.class.getName()).log(Level.SEVERE, null, ex);
        }
        return date2;
    }
}
