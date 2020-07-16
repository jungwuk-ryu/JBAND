package cn.hancho.jband.entities;

import cn.hancho.jband.JBAND;

public class Band {
    private JBAND jband;
    private String bandKey;
    private String name;
    private String coverUrl;
    private int memberCount;

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
}
