package com.hancho.jband;

import com.hancho.jband.entities.Band;

import java.util.ArrayList;

public class JBAND {
    public static String ACCESS_TOKEN;
    public static JBAND INSTANCE;
    public API api;

    private String clientId;

    public JBAND(){
        INSTANCE = this;
        this.api = new API(this);
    }

    public JBAND(String ACCESS_TOKEN){
        this();
        this.setAccessToken(ACCESS_TOKEN);
    }

    public String getAccessToken() {
        return ACCESS_TOKEN;
    }

    public void setAccessToken(String accessToken) {
        ACCESS_TOKEN = accessToken;
    }

    public API getApi(){
        return api;
    }

    public ArrayList<Band> getBandList(){
        return this.api.getBandList();
    }
}
