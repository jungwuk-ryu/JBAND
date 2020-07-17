package cn.hancho.jband.entities;

import cn.hancho.jband.JBAND;

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

    public void writePost(){

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
