package com.hancho.jband;

import com.hancho.jband.entities.Band;

import java.util.ArrayList;

public class Jband {
    public static String ACCESS_TOKEN;
    public static Jband INSTANCE;
    public Api api;

    private String clientId;

    public Jband() {
        INSTANCE = this;
        this.api = new Api(this);
    }

    public Jband(String accessToken) {
        this();
        this.setAccessToken(accessToken);
    }

    public String getAccessToken() {
        return ACCESS_TOKEN;
    }

    public void setAccessToken(String accessToken) {
        ACCESS_TOKEN = accessToken;
    }

    public Api getApi() {
        return api;
    }

    public ArrayList<Band> getBandList() {
        return this.api.getBandList();
    }
}
