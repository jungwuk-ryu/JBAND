package cn.hancho.jband.entities;

import cn.hancho.jband.APIRequester;
import cn.hancho.jband.JBAND;
import org.json.simple.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class Band {
    private JBAND jband;
    private String bandKey;
    private String name;
    private String coverUrl;
    private long memberCount;

    public Band(JBAND jband, String name, String bandKey, String coverUrl, long memberCount){
        this.jband = jband;
        this.name = name;
        this.bandKey = bandKey;
        this.coverUrl = coverUrl;
        this.memberCount = memberCount;
    }

    /**
     * <p>writePost with push notification</p>
     * @return String post_key
     */
    public String writePost(String content){
        return this.writePost(content, true);
    }

    /**
     *
     * @return String post_key
     */
    public String writePost(String content, boolean doPush){
        APIRequester requester = new APIRequester(this.jband.getLogger(), this.jband.getAccessToken());
        String parameters = "";
        try {
            parameters = "&band_key=" + this.bandKey
                    + "&content=" + URLEncoder.encode(content,"UTF-8")
                    + "&do_push=" + doPush;
        } catch (UnsupportedEncodingException e) {
            this.jband.getLogger().error("", e);
            return null;
        }

        JSONObject resultJson = requester.postRequest("v2.2/band/post/create", parameters);
        if((long) resultJson.get("result_code") != 1) {
            this.jband.getLogger().error("result code : " + resultJson.get("result_code"));
            return null;
        }

        JSONObject bodyJsonObj = (JSONObject) resultJson.get("result_data");
        return (String) bodyJsonObj.get("post_key");
    }

    public void getPostList(){

    }

    public void getPostDetail(){

    }

    public void getPermissions(){

    }

    public void getAlbums(){

    }


    public void removePost(){

    }

    public boolean canWritePost(){
        return false;
    }

    public boolean canWriteComment(){
        return false;
    }

    public boolean canDelete(){
        return false;
    }

    public JBAND getJband() {
        return jband;
    }

    public void setJband(JBAND jband) {
        this.jband = jband;
    }

    public String getBandKey() {
        return bandKey;
    }

    public void setBandKey(String bandKey) {
        this.bandKey = bandKey;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCoverUrl() {
        return coverUrl;
    }

    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
    }

    public long getMemberCount() {
        return memberCount;
    }

    public void setMemberCount(long memberCount) {
        this.memberCount = memberCount;
    }

}
