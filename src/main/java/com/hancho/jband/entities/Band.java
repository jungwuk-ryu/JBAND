package com.hancho.jband.entities;

import com.hancho.jband.JBAND;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
public class Band {
    private JBAND jband;
    private String bandKey;
    private String name;
    private String coverUrl;
    private long memberCount;

    private ArrayList<Post> posts;

    public Band(JBAND jband, String name, String bandKey, String coverUrl, long memberCount) {
        this.jband = jband;
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
    public String writePost(String content) {
        return this.writePost(content, true);
    }

    /**
     * @return String post_key
     */
    public String writePost(String content, boolean doPush) {
        return this.jband.api.writePost(this.bandKey, content, doPush);
    }

    public PostList getPostList(Locale locale) {
        return this.getPostList(locale, null, "20");
    }

    public PostList getPostList(Locale locale, String currentPage, String limit) {
        return this.jband.api.getPostList(this, locale, currentPage, limit);
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
