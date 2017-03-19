package com.offnet.ocpp.websocket;

import java.util.Date;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.websocket.Session;
import org.json.JSONException;
import org.json.JSONObject;

public class Device {

    private int id;
    private Session session;
    private String serial;
    private String status;
    private Date lastUpdate;
    private String adress;
            
    public Device() {
    }

    public String getAddress() {
        return adress;
    }

    public void setAddress(String adress) {
        this.adress = adress;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    
    
    public JSONObject toJSONObject () {
        JSONObject json = new JSONObject();

        try {
            json.put("id", id);
            if(session != null) {
                json.put("session", session.getId());
            }
            if(adress != null) {
                json.put("address", getAddress());
            }
            json.put("serial", serial);
            json.put("status", status);
        } catch (JSONException ex) {
            Logger.getLogger(Device.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return json;
    }

    /**
     * @return the lastUpdate
     */
    public Date getLastUpdate() {
        return lastUpdate;
    }

    /**
     * @param lastUpdate the lastUpdate to set
     */
    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + this.id;
        hash = 59 * hash + Objects.hashCode(this.session);
        hash = 59 * hash + Objects.hashCode(this.serial);
        hash = 59 * hash + Objects.hashCode(this.status);
        hash = 59 * hash + Objects.hashCode(this.lastUpdate);
        hash = 59 * hash + Objects.hashCode(this.adress);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Device other = (Device) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.serial, other.serial)) {
            return false;
        }
        if (!Objects.equals(this.status, other.status)) {
            return false;
        }
        if (!Objects.equals(this.adress, other.adress)) {
            return false;
        }
        if (!Objects.equals(this.session, other.session)) {
            return false;
        }
        if (!Objects.equals(this.lastUpdate, other.lastUpdate)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Device{" + "id=" + id + ", session=" + session + ", serial=" + serial + ", status=" + status + ", lastUpdate=" + lastUpdate + ", adress=" + adress + '}';
    }

    
    
}
