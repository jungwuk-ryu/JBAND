package cn.hancho.jband;

import cn.hancho.jband.entities.Band;
import cn.hancho.jband.entities.User;

import java.util.ArrayList;

public class JBAND {
    private String access_token;
    private String token;

    public JBAND(){
        super();
    }

    public JBAND(String access_token){
        this.setAccess_token(access_token);
    }

    public JBAND(String access_token, String token){
        this.setAccess_token(access_token);
        this.setToken(token);
    }

    public ArrayList<User> getProfile(){
        return new ArrayList<User>();
    }

    public ArrayList<Band> getBandList(){
        return new ArrayList<Band>();
    }

    public String getToken(){
        return this.token;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setToken(String token){
        this.token = token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }
}
