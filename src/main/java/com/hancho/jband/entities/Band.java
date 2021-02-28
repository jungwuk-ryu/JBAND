package com.hancho.jband.entities;

import com.hancho.jband.JbandClient;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
public class Band {
    private JbandClient client;
    private String bandKey;
    private String name;
    private String coverUrl;
    private long memberCount;

    private ArrayList<Post> posts;

    public Band(JbandClient jbandClient, String name, String bandKey, String coverUrl, long memberCount) {
        this.client = jbandClient;
        this.name = name;
        this.bandKey = bandKey;
        this.coverUrl = coverUrl;
        this.memberCount = memberCount;
    }

    /**
     * <p>writePost with push notification</p>
     *
     * @return String post_key
     */
    public ResponseInfo<String> writePost(String content) {
        return this.writePost(content, true);
    }

    /**
     * @return String post_key
     */
    public ResponseInfo<String> writePost(String content, boolean doPush) {
        return this.client.getBandApi().writePost(this.bandKey, content, doPush);
    }

    public ResponseInfo<PostList> getPostList(Locale locale) {
        return this.getPostList(locale, null, "20");
    }

    public ResponseInfo<PostList> getPostList(Locale locale, String currentPage, String limit) {
        return this.client.getBandApi().getPostList(this, locale, currentPage, limit);
    }

    public ArrayList<Post> getPostsFromCache() {
        return this.posts;
    }

    public void getPostDetail() {

    }

    public void getPermissions() {

    }

    public void getAlbums() {

    }


    public void removePost() {

    }

    public boolean canWritePost() {
        return false;
    }

    public boolean canWriteComment() {
        return false;
    }

    public boolean canDelete() {
        return false;
    }

    public enum Locale {
        ko_KR("ko_KR"),
        en_US("en_US");

        Locale(String locale) {

        }
    }

}
