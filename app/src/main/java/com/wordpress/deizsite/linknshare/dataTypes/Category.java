package com.wordpress.deizsite.linknshare.dataTypes;

/**
 * Created by Cliente on 20/10/2015.
 */
public class Category {
    private String mId;
    private String mName;
    private ContentResolver mContentResolver;
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

    public ContentResolver getContentResolver() {
        return mContentResolver;
    }

    public void setContentResolver(ContentResolver mContentResolver) {
        this.mContentResolver = mContentResolver;
    }
}
