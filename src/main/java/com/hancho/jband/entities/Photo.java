package com.hancho.jband.entities;

import lombok.Getter;
import lombok.Setter;
import org.json.simple.JSONObject;

@Getter
@Setter
public class Photo {
    private String photoAlbumKey, url;
    private User author;
    private long width, height, commentCount, emotionCount, createdAt;
    private boolean isVideoThumbnail;

    public Photo(JSONObject jsonObject) {
        if (jsonObject.get("width") != null) {
            this.setWidth((Long) jsonObject.get("width"));
            this.setHeight((Long) jsonObject.get("height"));
        }
        if (jsonObject.get("comment_count") != null) {
            this.setCommentCount((Long) jsonObject.get("comment_count"));
        }
        if (jsonObject.get("emotion_count") != null) {
            this.setEmotionCount((Long) jsonObject.get("emotion_count"));
        }
        if (jsonObject.get("created_at") != null) {
            this.setCreatedAt((Long) jsonObject.get("created_at"));
        }
        if (jsonObject.get("is_video_thumbnail") != null) {
            this.setVideoThumbnail((Boolean) jsonObject.get("is_video_thumbnail"));
        }
        this.setAuthor(new User((JSONObject) jsonObject.get("author")));
        this.setPhotoAlbumKey((String) jsonObject.get("photo_album_key"));  //!
        this.setUrl((String) jsonObject.get("url"));    //!
    }
}
