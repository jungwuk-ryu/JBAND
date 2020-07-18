package cn.hancho.jband.entities;

import org.json.simple.JSONObject;

public class User {
    private String userKey;
    private String profileImageUrl;
    private String name;
    private String description;
    private String role;
    private boolean isAppMember;
    private boolean messageAllowed;

    public User(String name, String userKey){
        this.setName(name);
        this.setUserKey(userKey);
    }

    public User(JSONObject jsonObject){
        this.setName((String) jsonObject.get("name"));
        this.setUserKey((String) jsonObject.get("user_key"));
        this.setDescription((String) jsonObject.get("description"));
        this.setRole((String) jsonObject.get("role"));
        this.setProfileImageUrl((String) jsonObject.get("profile_image_url"));
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAppMember(boolean appMember) {
        isAppMember = appMember;
    }

    public void setMessageAllowed(boolean messageAllowed) {
        this.messageAllowed = messageAllowed;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
