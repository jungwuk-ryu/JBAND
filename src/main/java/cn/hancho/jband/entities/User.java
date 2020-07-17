package cn.hancho.jband.entities;

public class User {
    private String userKey;
    private String profileImageUrl;
    private String name;
    private boolean isAppMember;
    private boolean messageAllowed;

    public User(String name, String userKey){
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
