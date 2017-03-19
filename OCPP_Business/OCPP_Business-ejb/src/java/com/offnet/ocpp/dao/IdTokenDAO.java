/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.offnet.ocpp.dao;

import com.offnet.ocpp.entity.IdToken;
import org.json.JSONObject;

/**
 *
 * @author gabi
 */
public interface IdTokenDAO extends ObjectDAO<IdToken, String>{
    public IdToken findByValue(String value);
    public JSONObject toJSONObject(IdToken idToken);
    public int regularUpdate();
}
