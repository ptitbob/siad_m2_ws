/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.univ.blois.jee.api.ws;

import javax.jws.WebMethod;
import javax.jws.WebService;

/**
 *
 * @author francois
 */
public class PingWS {
    
    public String ping() {
        return "pong";
    }
    
    public String upperCase(String value) {
        return value.toUpperCase();
    }
}
