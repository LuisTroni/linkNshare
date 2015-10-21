package com.wordpress.deizsite.linknshare.dataTypes;

import java.net.URL;
import java.util.ArrayList;

public class Share {
    private String mAbout;
    private String mId;
    private String mName;
    private URL mUrl;
    private Picture mPicture;
    private ArrayList<Category> mCategories = new ArrayList<Category>();

    public String getId() {
        return mId;
    }

    public void setId(String mId) {
        this.mId = mId;
    }

    public String getName() {
        return mName;
    }

    public void setName(String mName) {
        this.mName = mName;
    }

    public URL getUrl() {
        return mUrl;
    }

    public void setUrl(URL mUrl) {
        this.mUrl = mUrl;
    }

    public ArrayList<Category> getCategories() {
        return mCategories;
    }

    public Picture getPicture() {
        return mPicture;
    }

    public void setPicture(Picture mPicture) {
        this.mPicture = mPicture;
    }

    public void setCategories(ArrayList<Category> mCategories) {
        this.mCategories = mCategories;
    }

    public String getAbout() {
        return mAbout;
    }

    public void setAbout(String mAbout) {
        this.mAbout = mAbout;
    }

}
