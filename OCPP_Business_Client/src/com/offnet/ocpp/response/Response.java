/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.offnet.ocpp.response;

import java.io.Serializable;

/**
 *
 * @author gabi
 */
public interface Response extends Serializable{
    public String toJson();
    public String toJsonSoapVersion();
}
