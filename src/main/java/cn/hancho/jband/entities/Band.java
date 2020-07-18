package cn.hancho.jband.entities;

import cn.hancho.jband.APIRequester;
import cn.hancho.jband.JBAND;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;

public class Band {
    private JBAND jband;
    private String bandKey;
    private String name;
    private String coverUrl;
    private long memberCount;

    private ArrayList<Post> posts;

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
        return this.jband.writePostDirect(this.bandKey, content, doPush);
    }

    public PostList getPostList(Locale locale){
        return this.getPostList(locale,null,"20");
    }

    public PostList getPostList(Locale locale, String currentPage, String limit){
        APIRequester requester = new APIRequester(this.jband.getLogger(), this.jband.getAccessToken());
        String parameters = "&band_key=" + this.bandKey + "&locale" + locale;
        if(currentPage != null && !currentPage.isEmpty()){
            parameters += "&after=" + currentPage + "&limit=" + limit;
        }
        JSONObject main = requester.getRequest("v2/band/posts", parameters);
        if((long) main.get("result_code") != 1) {
            this.jband.getLogger().error("result code : " + main.get("result_code"));
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
        this.posts = posts;

        JSONObject jsonPaging = (JSONObject) body.get("paging");
        JSONObject jsonNextParams = (JSONObject) jsonPaging.get("next_params");
        String nextPage = (String) jsonNextParams.get("after");
        String previousPage = "no_previous_params";
        String newLimit = "20";
        if(jsonNextParams.get("limit") != null) {
            newLimit = (String) jsonNextParams.get("limit");
        }
        return new PostList(this, posts, nextPage, previousPage, locale, newLimit);
    }

    public ArrayList<Post> getPostsFromCache(){
        return this.posts;
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

    public enum Locale{
        ko_KR("ko_KR"),
        en_US("en_US");

        Locale(String locale) {

        }
    }

}
