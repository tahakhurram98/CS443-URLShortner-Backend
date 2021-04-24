package com.urlShortner.Application.Requests;

import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.util.UUID;

@Table("Request")
public class Request {
    @PrimaryKey
    private UUID id;

    private UUID urlID;
    private String requestIP;
//    private String countryCode;
    private String requestReferrer;
    private long createdAt;

    public Request(){

    }

    //public Request(UUID id, UUID urlID, String requestIP, String countryCode, String requestReferrer, long createdAt) {
    public Request(UUID id, UUID urlID, String requestIP, String requestReferrer, long createdAt) {
        this.id = id;
        this.urlID = urlID;
        this.requestIP = requestIP;
//        this.countryCode = countryCode;
        this.requestReferrer = requestReferrer;
        this.createdAt = createdAt;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getUrlID() {
        return urlID;
    }

    public void setUrlID(UUID urlID) {
        this.urlID = urlID;
    }

    public String getRequestIP() {
        return requestIP;
    }

    public void setRequestIP(String requestIP) {
        this.requestIP = requestIP;
    }

    public String getRequestReferrer() {
        return requestReferrer;
    }

    public void setRequestReferrer(String requestReferrer) {
        this.requestReferrer = requestReferrer;
    }

    public long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(long createdAt) {
        this.createdAt = createdAt;
    }
}
