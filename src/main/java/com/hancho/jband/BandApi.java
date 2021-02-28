package com.hancho.jband;

import com.hancho.jband.entities.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;

public class BandApi {
    private JbandClient client;
    private ApiRequester apiRequester;

    public BandApi(JbandClient client){
        this.client = client;
        this.apiRequester = new ApiRequester(client.getAccessToken());
    }

    public ResponseInfo<User> getProfile(String bandKey) {
        String api = "v2/profile";
        JSONObject jsonObj;

        if (bandKey != null && !bandKey.isEmpty()) {
            jsonObj = this.apiRequester.requestGet(api, "&band_key=" + bandKey);
        } else {
            jsonObj = this.apiRequester.requestGet(api);
        }

        JSONObject bodyJsonObj = (JSONObject) jsonObj.get("result_data");
        User user = new User((String) bodyJsonObj.get("name"), (String) bodyJsonObj.get("user_key"));
        user.setProfileImageUrl((String) bodyJsonObj.get("profile_image_url"));
        user.setAppMember((boolean) bodyJsonObj.get("is_app_member"));
        user.setMessageAllowed((boolean) bodyJsonObj.get("message_allowed"));

        return new ResponseInfo<>((long) jsonObj.get("result_code"), user);
    }

    // TODO : Add Paging
    public ResponseInfo<PostList> getPostList(Band band, Band.Locale locale, String currentPage, String limit) {
        String parameters = "&band_key=" + band.getBandKey() + "&locale" + locale;
        if (currentPage != null && !currentPage.isEmpty()) {
            parameters += "&after=" + currentPage + "&limit=" + limit;
        }

        JSONObject responseJsonObj = this.apiRequester.requestGet("v2/band/posts", parameters);
        JSONObject bodyJsonObj = (JSONObject) responseJsonObj.get("result_data");
        JSONArray postJsonArray = (JSONArray) bodyJsonObj.get("items");

        ArrayList<Post> posts = null;
        if (postJsonArray != null) {
            posts = new ArrayList<>();
            for (Object obj : postJsonArray) {
                JSONObject jsonPost = (JSONObject) obj;
                posts.add(new Post(jsonPost));
            }
        }
        band.setPosts(posts);

        JSONObject jsonPaging = (JSONObject) bodyJsonObj.get("paging");
        JSONObject jsonNextParams = (JSONObject) jsonPaging.get("next_params");
        String nextPage = (String) jsonNextParams.get("after");
        String previousPage = "no_previous_params";
        String newLimit = "20";
        if (jsonNextParams.get("limit") != null) {
            newLimit = (String) jsonNextParams.get("limit");
        }

        PostList postList = new PostList(band, posts, nextPage, previousPage, locale, newLimit);
        return new ResponseInfo<>(responseJsonObj, postList);
    }

    public ResponseInfo<ArrayList<Band>> getBandList() {
        String api = "v2.1/bands";
        JSONObject responseJsonObj = this.apiRequester.requestGet(api);

        JSONObject bodyJsonObj = (JSONObject) responseJsonObj.get("result_data");
        JSONArray jsonArray = (JSONArray) bodyJsonObj.get("bands");
        ArrayList<Band> bandList = new ArrayList<>();

        jsonArray.forEach((obj) -> {
            JSONObject jsonBand = (JSONObject) obj;
            Band band = new Band(client, (String) jsonBand.get("name")
                    , (String) jsonBand.get("band_key")
                    , (String) jsonBand.get("cover")
                    , (long) jsonBand.get("member_count"));
            bandList.add(band);
        });

        return new ResponseInfo<>(responseJsonObj, bandList);
    }

    public ResponseInfo<String> writePost(String bandKey, String content, boolean doPush) {
        String parameters;
        try {
            parameters = "&band_key=" + bandKey
                    + "&content=" + URLEncoder.encode(content, "UTF-8")
                    + "&do_push=" + doPush;
        } catch (UnsupportedEncodingException e) {
            MainLogger.error("", e);
            return null;
        }

        JSONObject resultJson = this.apiRequester.requestPost("v2.2/band/post/create", parameters);
        JSONObject bodyJsonObj = (JSONObject) resultJson.get("result_data");
        return new ResponseInfo<>(resultJson, (String) bodyJsonObj.get("post_key"));
    }
}
