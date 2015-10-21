package com.wordpress.deizsite.linknshare.dataTypes;

import java.net.URL;

/**
 * Created by Cliente on 20/10/2015.
 */
public class Picture {
    private boolean mSilluette;
    private URL mURL;

    public boolean isSilluette() {
        return mSilluette;
    }

    public void setSilluette(boolean mSilluette) {
        this.mSilluette = mSilluette;
    }

    public URL getURL() {
        return mURL;
    }

    public void setURL(URL mURL) {
        this.mURL = mURL;
    }
}
