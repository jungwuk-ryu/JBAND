package cn.hancho.jband;

import cn.hancho.jband.entities.Band;
import cn.hancho.jband.entities.User;
import org.apache.log4j.Logger;
import org.json.simple.JSONObject;

import java.util.ArrayList;

public class JBAND {
    Class clazz;
    private static final Logger LOGGER = Logger.getLogger(JBAND.class);
    private String clientId;
    private String accessToken;
    private String token;

    public JBAND(){
    }

    public JBAND(String accessToken){
        this.setAccessToken(accessToken);
    }

    public JBAND(String accessToken, String token){
        this.setAccessToken(accessToken);
        this.setToken(token);
    }

    public User getProfile(){
        return this.getProfile(null);
    }

    public User getProfile(String bandKey){
        LOGGER.warn("starting");
        if(this.accessToken == null){
            LOGGER.error("accessToken is null");
            return null;
        }
        APIRequester requester = new APIRequester(LOGGER);
        String api = "v2/profile?";
        api += "access_token=" + this.accessToken;
        if(bandKey != null && !bandKey.isEmpty()) api += "&band_key=" + bandKey;
        JSONObject jsonObj = requester.getRequest(api);
        if(!jsonObj.get("result_code").equals("1")) return null;
        System.out.println(jsonObj.get("result_data"));
        User user = new User("이게 뭔데", "ㅠㅜㅜ");
        return user;
    }

    public ArrayList<Band> getBandList(){
        return new ArrayList<Band>();
    }

    public String getToken(){
        return this.token;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setToken(String token){
        this.token = token;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}
