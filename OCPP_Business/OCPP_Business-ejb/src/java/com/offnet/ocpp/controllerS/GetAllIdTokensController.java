/*
 *  com.offnet.ocpp.controllerS.ControllerS
 *  com.offnet.ocpp.dao.IdTokenDAOImpl
 *  com.offnet.ocpp.entity.IdToken
 *  org.json.JSONArray
 *  org.json.JSONObject
 */
package com.offnet.ocpp.controllerS;

import com.offnet.ocpp.dao.IdTokenDAOImpl;
import com.offnet.ocpp.entity.IdToken;
import java.io.PrintStream;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

public class GetAllIdTokensController
extends ControllerS {
    public GetAllIdTokensController(List<String> array, String session_id) {
        super(array, session_id);
    }

    public String processRequest() {
        JSONArray response = new JSONArray();
        IdTokenDAOImpl idTokenDAO = new IdTokenDAOImpl(this.session_id);
        List<IdToken> idTokens = idTokenDAO.findAll();
        for (IdToken idToken : idTokens) {
            response.put((Object)idTokenDAO.toJSONObject(idToken));
        }
        return response.toString();
    }
}