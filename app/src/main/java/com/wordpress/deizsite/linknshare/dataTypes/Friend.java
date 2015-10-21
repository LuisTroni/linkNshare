package com.wordpress.deizsite.linknshare.dataTypes;

/**
 * Created by Cliente on 20/10/2015.
 */
public class Friend {
    private String mName;
    private String mId;
    private Picture mPicture;

    public String getName() {
        return mName;
    }

    public void setName(String mName) {
        this.mName = mName;
    }

    public String getId() {
        return mId;
    }

    public void setId(String mId) {
        this.mId = mId;
    }

    public Picture getPicture() {
        return mPicture;
    }

    public void setPicture(Picture mPicture) {
        this.mPicture = mPicture;
    }
}
