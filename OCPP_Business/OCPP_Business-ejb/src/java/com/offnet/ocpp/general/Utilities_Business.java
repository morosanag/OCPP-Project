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
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author gabi
 */
public class Utilities_Business {
    
    public static Hashtable<String,Request> pendingRequests = new Hashtable<String,Request>();
    
    /*
    private static SecureRandom random = new SecureRandom();
    
    
    public static String getCallID() {
        return new BigInteger(130, random).toString(32);
    }
    */
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
        try {
            return formatData(calendar, Constants_Business.DATE_FORMAT);
        } catch (NumberFormatException ex) {
            return formatData(calendar, Constants_Business.DATE_FORMAT_MILIS);
        }
    }
    
    public static String formatData(GregorianCalendar calendar, String dataFormat) {
        //"2013-02-01T15:09:18Z"
        SimpleDateFormat fmt = new SimpleDateFormat(dataFormat);
        //{"status": "Accepted", "currentTime": "2013-02-01T15:09:18Z", "heartbeatInterval": 1200 }
        //SimpleDateFormat fmt = new SimpleDateFormat("dd-MMM-yyyy");
        fmt.setCalendar(calendar);
        String dateFormatted = fmt.format(calendar.getTime());
        return dateFormatted;
    }
    
    public static Long formatDataToLong(GregorianCalendar calendar) {
        return calendar.getTimeInMillis();
    }
    
    public static GregorianCalendar formatCalendar(String input) {
        Date       date = null;
        if (input.matches("[0-9]+") && input.length() > 2) {
            date = new Date(Long.parseLong(input)); 
        } else {
        
            DateFormat format = new SimpleDateFormat(Constants_Business.DATE_FORMAT);

            try {
                date = format.parse(input);  
            } catch (NumberFormatException | ParseException ex) {
                format = new SimpleDateFormat(Constants_Business.DATE_FORMAT_MILIS);
                try {
                    date = format.parse(input);

                } catch (ParseException ex1) {
                    format = new SimpleDateFormat(Constants_Business.DATE_FORMAT_MILIS_2);
                    //DATE_FORMAT_MILIS_2
                    try {
                                                
                        date = format.parse(input);

                    } catch (ParseException ex2) {
                    
                        Logger.getLogger(Utilities_Business.class.getName()).log(Level.SEVERE, null, ex2);
                    }
                }
            }
        }
        GregorianCalendar   calendar = new GregorianCalendar();
        calendar.setTime( date );
        return calendar;
    }

    public static GregorianCalendar fromLongToDate(Long date) {
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTimeInMillis(date);
        return cal;
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
            Logger.getLogger(Utilities_Business.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public static JsonObject fromStringToJsonObject(String jsonStr) {
        JsonParser parser = new JsonParser();
        JsonObject o = parser.parse(jsonStr).getAsJsonObject();
        return o;
    }
    
    public static boolean checkNull(JsonObject json, String param) {
            
        return json.has(param) && !json.get(param).toString().equals("null");
    
    }
}
