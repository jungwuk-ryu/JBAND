package com.hancho.jband.entities;

import com.hancho.jband.API;
import com.hancho.jband.APIRequester;
import com.hancho.jband.JBAND;
import com.hancho.jband.MainLogger;
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
        return this.jband.api.writePost(this.bandKey, content, doPush);
    }

    public PostList getPostList(Locale locale){
        return this.getPostList(locale,null,"20");
    }

    public PostList getPostList(Locale locale, String currentPage, String limit){
        return this.jband.api.getPostList(this, locale, currentPage, limit);
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

    public ArrayList<Post> getPosts() {
        return posts;
    }

    public void setPosts(ArrayList<Post> posts) {
        this.posts = posts;
    }

    public enum Locale{
        ko_KR("ko_KR"),
        en_US("en_US");

        Locale(String locale) {

        }
    }

}
