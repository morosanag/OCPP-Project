/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.offnet.ocpp.validator;
/*
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.everit.json.schema.Schema;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;
*/
/**
 *
 * @author gabi
 */
public class SchemaValidator {
    /*
    public static String validate (String response, String procedureName) {
        
        
        
        String procedure = procedureName.replaceAll("Request", "");
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream("/var/www/html/schema/" + procedure + ".json");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(SchemaValidator.class.getName()).log(Level.SEVERE, null, ex);
            return "ERROR ";
        }
            
        JSONObject rawSchema = null;
        try {
            rawSchema = new JSONObject(new JSONTokener(inputStream));
        } catch (JSONException ex) {
            Logger.getLogger(SchemaValidator.class.getName()).log(Level.SEVERE, null, ex);
            return "ERROR";
        }
        Schema schema = SchemaLoader.load(rawSchema);
        try {
            schema.validate(new JSONObject("{\"connectorI\": 2, \"status\": \"Available\", \"errorCode\": \"NoError\", \"info\": \"\", \"timestamp\": \"2013-02-01T15:09:18Z\", \"vendorId\": \"\", \"vendorErrorCode\": \"\" }")); // throws a ValidationException if this object is invalid
        } catch (JSONException ex) {
            return "FALSE";
        }
            
        try {
            inputStream.close();
        } catch (IOException ex) {
            Logger.getLogger(SchemaValidator.class.getName()).log(Level.SEVERE, null, ex);
            return "ERROR";
        }
        
        return "TRUE";
    }
 */   
}
