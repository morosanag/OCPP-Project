/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.offnet.ocpp.soap;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.offnet.ocpp.client.ResetResponse;
import com.offnet.ocpp.client.ResetStatus;

/**
 *
 * @author gabi
 */
public class Test {
    public static void main(String []  args) throws JsonProcessingException {
        ResetResponse resetResponse = new ResetResponse();
        resetResponse.setStatus(ResetStatus.ACCEPTED);
        
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(resetResponse);
        
    }
}
