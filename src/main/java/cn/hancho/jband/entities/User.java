package cn.hancho.jband.entities;

public class User {
    private String userKey;
    private String profileImageUrl;
    private String name;
    private boolean isAppMember;
    private boolean messageAllowed;

    public User(String name, String userKey){
        this.setName(name);
        this.setUserKey(userKey);
    }

    public String getName() {
        return name;
    }

    public String getUserKey() {
        return userKey;
    }

    public void setUserKey(String userKey) {
        this.userKey = userKey;
    }

    public String getProfileImageUrl() {
        return profileImageUrl;
    }

    public void setProfileImageUrl(String profileImageUrl) {
        this.profileImageUrl = profileImageUrl;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isAppMember() {
        return isAppMember;
    }

    public void setIsAppMember(boolean appMember) {
        isAppMember = appMember;
    }

    public boolean isMessageAllowed() {
        return messageAllowed;
    }

    public void setIsMessageAllowed(boolean messageAllowed) {
        this.messageAllowed = messageAllowed;
    }
}
