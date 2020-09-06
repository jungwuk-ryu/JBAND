package com.hancho.jband;

import com.hancho.jband.entities.Band;
import com.hancho.jband.entities.Post;
import com.hancho.jband.entities.PostList;
import com.hancho.jband.entities.User;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;

public class API {
    public API(JBAND jband){
        if(jband == null) throw new IllegalArgumentException("jband is not initialized");
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

    public PostList getPostList(Band band, Band.Locale locale, String currentPage, String limit){
        APIRequester requester = new APIRequester();
        String parameters = "&band_key=" + band.getBandKey() + "&locale" + locale;
        if(currentPage != null && !currentPage.isEmpty()){
            parameters += "&after=" + currentPage + "&limit=" + limit;
        }
        JSONObject main = requester.getRequest("v2/band/posts", parameters);
        if((long) main.get("result_code") != 1) {
            MainLogger.error("result code : " + main.get("result_code"));
            return null;
        }
        JSONObject body = (JSONObject) main.get("result_data");
        // TODO : Add Paging
        JSONArray jsonPosts = (JSONArray) body.get("items");
        ArrayList<Post> posts = null;
        if(jsonPosts != null) {
            posts = new ArrayList<>();
            for (Object obj : jsonPosts) {
                JSONObject jsonPost = (JSONObject) obj;
                posts.add(new Post(jsonPost));
            }
        }
        band.setPosts(posts);

        JSONObject jsonPaging = (JSONObject) body.get("paging");
        JSONObject jsonNextParams = (JSONObject) jsonPaging.get("next_params");
        String nextPage = (String) jsonNextParams.get("after");
        String previousPage = "no_previous_params";
        String newLimit = "20";
        if(jsonNextParams.get("limit") != null) {
            newLimit = (String) jsonNextParams.get("limit");
        }
        return new PostList(band, posts, nextPage, previousPage, locale, newLimit);
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
            Band band = new Band(JBAND.INSTANCE, (String) jsonBand.get("name")
                    , (String) jsonBand.get("band_key")
                    , (String) jsonBand.get("cover")
                    , (long) jsonBand.get("member_count"));
            bands.add(band);
        });
        return bands;
    }

    public String writePost(String bandKey, String content, boolean doPush){
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
