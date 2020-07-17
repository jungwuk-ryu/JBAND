package cn.hancho.jband;

import cn.hancho.jband.entities.Band;
import cn.hancho.jband.entities.User;
import org.json.simple.JSONObject;

import java.util.ArrayList;

public class JBAND {
    public MainLogger logger;

    private String clientId;
    private String accessToken;

    public JBAND(){
        this.logger = new MainLogger();
    }

    public JBAND(String accessToken){
        this.setAccessToken(accessToken);
        this.logger = new MainLogger();
    }

    public MainLogger getLogger(){
        return this.logger;
    }

    public User getProfile(){
        return this.getProfile(null);
    }

    public User getProfile(String bandKey){
        if(this.accessToken == null){
            this.logger.error("accessToken is null");
            return null;
        }

        APIRequester requester = new APIRequester(this.logger);
        String api = "v2/profile?";

        api += "access_token=" + this.accessToken;
        if(bandKey != null && !bandKey.isEmpty()) api += "&band_key=" + bandKey;
        JSONObject jsonObj = requester.getRequest(api);
        if((long) jsonObj.get("result_code") != 1) {
            this.logger.error("result code : " + jsonObj.get("result_code"));
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
        return new ArrayList<Band>();
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}
