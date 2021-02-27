package com.hancho.jband.entities;

import lombok.Getter;
import lombok.Setter;
import org.json.simple.JSONObject;

@Getter
@Setter
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
}
