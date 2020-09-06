package com.hancho.jband;

import com.hancho.jband.entities.Band;
import com.hancho.jband.entities.User;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;

public class JBAND {
    public static String ACCESS_TOKEN;

    private String clientId;

    public JBAND(){
    }

    public JBAND(String ACCESS_TOKEN){
        this.setAccessToken(ACCESS_TOKEN);
    }

    public String getAccessToken() {
        return ACCESS_TOKEN;
    }

    public void setAccessToken(String accessToken) {
        ACCESS_TOKEN = accessToken;
    }

    public User getProfile(){
        return this.getProfile(null);
    }

    public User getProfile(String bandKey){
        APIRequester requester = new APIRequester();
        String api = "v2/profile";
        JSONObject jsonObj;
        if(bandKey != null && !bandKey.isEmpty()) jsonObj = requester.getRequest(api, "&band_key=" + bandKey);
        else jsonObj = requester.getRequest(api);
        if((long) jsonObj.get("result_code") != 1) {
            MainLogger.error("result code : " + jsonObj.get("result_code"));
            return null;
        }

        JSONObject bodyJsonObj = (JSONObject) jsonObj.get("result_data");
        User user = new User((String) bodyJsonObj.get("name"), (String) bodyJsonObj.get("user_key"));
        user.setProfileImageUrl((String) bodyJsonObj.get("profile_image_url"));
        user.setIsAppMember((boolean) bodyJsonObj.get("is_app_member"));
        user.setIsMessageAllowed((boolean) bodyJsonObj.get("message_allowed"));
        return user;
    }

    public ArrayList<Band> getBandList(){
        APIRequester requester = new APIRequester();
        String api = "v2.1/bands";
        JSONObject jsonObj = requester.getRequest(api);
        if((long) jsonObj.get("result_code") != 1) {
            MainLogger.error("result code : " + jsonObj.get("result_code"));
            return null;
        }
        ArrayList<Band> bands = new ArrayList<>();
        JSONObject bodyJsonObj = (JSONObject) jsonObj.get("result_data");
        JSONArray jsonArray = (JSONArray) bodyJsonObj.get("bands");
        jsonArray.forEach((obj) -> {
            JSONObject jsonBand = (JSONObject) obj;
            Band band = new Band(this, (String) jsonBand.get("name")
                    , (String) jsonBand.get("band_key")
                    , (String) jsonBand.get("cover")
                    , (long) jsonBand.get("member_count"));
            bands.add(band);
        });
        return bands;
    }

    public String writePostDirect(String bandKey, String content, boolean doPush){
        APIRequester requester = new APIRequester();
        String parameters = "";
        try {
            parameters = "&band_key=" + bandKey
                    + "&content=" + URLEncoder.encode(content,"UTF-8")
                    + "&do_push=" + doPush;
        } catch (UnsupportedEncodingException e) {
            MainLogger.error("", e);
            return null;
        }

        JSONObject resultJson = requester.postRequest("v2.2/band/post/create", parameters);
        if((long) resultJson.get("result_code") != 1) {
            MainLogger.error("result code : " + resultJson.get("result_code"));
            return null;
        }

        JSONObject bodyJsonObj = (JSONObject) resultJson.get("result_data");
        return (String) bodyJsonObj.get("post_key");
    }
}
