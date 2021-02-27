package com.hancho.jband.entities;

import com.hancho.jband.MainLogger;

import java.util.ArrayList;

public class PostList {
    public Band band;
    public ArrayList<Post> posts;
    public String nextPage, previousPage, limit;
    public Band.Locale locale;

    public PostList(Band band, ArrayList<Post> posts, String nextPage, String previousPage, Band.Locale locale, String limit) {
        this.band = band;
        this.posts = posts;
        this.nextPage = nextPage;
        this.previousPage = previousPage;
        this.locale = locale;
        this.limit = limit;
    }

    public ArrayList<Post> getPosts() {
        return this.posts;
    }

    public boolean isFirstPage() {
        return this.previousPage.equals("no_previous_params");
    }

    public PostList getPreviousPage() {
        return this.getPreviousPage(this.locale);
    }

    public PostList getPreviousPage(Band.Locale locale) {
        if (this.isFirstPage()) {
            MainLogger.warn("This page is the first page. Cannot get previous page!");
            return null;
        }
        return this.band.getPostList(locale, this.previousPage, this.limit);
    }

    public PostList getNextPage() {
        return this.getNextPage(this.locale);
    }

    public PostList getNextPage(Band.Locale locale) {
        if (this.nextPage == null || this.nextPage.isEmpty()) {
            MainLogger.warn("The next page does not exist.");
            return null;
        }
        return this.band.getPostList(locale, this.nextPage, this.limit);
    }
}
