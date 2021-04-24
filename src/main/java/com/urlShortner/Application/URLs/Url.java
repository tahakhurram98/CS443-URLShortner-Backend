package com.urlShortner.Application.URLs;

import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.util.UUID;

@Table("Url")
public class Url {
    @PrimaryKey
    private UUID id;
    private Integer userID;
    private String origURL;
    private String shortURL;
    private long createdAt;
//    private long expiresAt = 0;
//    private String creatorIP;
//    private int visitorCount;
//    private int visitorLimit = 0;
//    private Boolean isCustom = false;

    public Url() {

    }

    public Url(UUID id, Integer userID, String origURL, String shortURL, long createdAt, long expiresAt, String creatorIP,
            int visitorCount, int visitorLimit, Boolean isCustom) {
        this.id = id;
        this.userID = userID;
        this.origURL = origURL;
        this.shortURL = shortURL;
        this.createdAt = createdAt;
//        this.expiresAt = expiresAt;
//        this.creatorIP = creatorIP;
//        this.visitorCount = visitorCount;
//        this.visitorLimit = visitorLimit;
//        this.isCustom = isCustom;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public String getOrigURL() {
        return origURL;
    }

    public void setOrigURL(String origURL) {
        this.origURL = origURL;
    }

    public String getShortURL() {
        return shortURL;
    }

    public void setShortURL(String shortURL) {
        this.shortURL = shortURL;
    }

    public long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(long createdAt) {
        this.createdAt = createdAt;
    }

//    public long getExpiresAt() {
//        return expiresAt;
//    }
//
//    public void setExpiresAt(long expiresAt) {
//        this.expiresAt = expiresAt;
//    }
//
//    public String getCreatorIP() {
//        return creatorIP;
//    }
//
//    public void setCreatorIP(String creatorIP) {
//        this.creatorIP = creatorIP;
//    }
//
//    public int getVisitorCount() {
//        return visitorCount;
//    }
//
//    public void setVisitorCount(int visitorCount) {
//        this.visitorCount = visitorCount;
//    }
//
//    public int getVisitorLimit() { return visitorLimit; }
//
//    public void setVisitorLimit(int visitorLimit) { this.visitorLimit = visitorLimit; }
//
//    public Boolean getCustom() { return isCustom; }
//
//    public void setCustom(Boolean custom) { isCustom = custom; }

    @Override
    public String toString() {
        return "URL{" +
                "id=" + id +
                ", userID=" + userID +
                ", origURL='" + origURL + '\'' +
                ", shortURL='" + shortURL + '\'' +
                ", createdAt=" + createdAt +
//                ", expiresAt=" + expiresAt +
//                ", creatorIP='" + creatorIP + '\'' +
//                ", visitorCount=" + visitorCount +
//                ", visitorLimit=" + visitorLimit +
//                ", isCustom=" + isCustom +
                '}';
    }
}
