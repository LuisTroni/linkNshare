package com.wordpress.deizsite.linknshare.dataSources;

import android.hardware.camera2.params.Face;
import android.util.Log;

import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphRequestAsyncTask;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.wordpress.deizsite.linknshare.dataTypes.Friend;
import com.wordpress.deizsite.linknshare.dataTypes.Picture;
import com.wordpress.deizsite.linknshare.dataTypes.Share;

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Cliente on 20/10/2015.
 */
public class Facebook {
    private static final String TAG = Facebook.class.getSimpleName();
    private static Facebook mInstance = null;

    private ArrayList<Friend> mFriendList = new ArrayList<Friend>();
    private ArrayList<Share> mShareList = new ArrayList<Share>();
    private OnSyncCallBack mCallback;

    public static interface OnSyncCallBack {
        void onSyncCompleted();
    }

    public static Facebook getInstance() {
        if (mInstance == null) {
            mInstance = new Facebook();
        }
        return mInstance;
    }

    public void syncData() {
        GraphRequest.executeBatchAsync(syncFriends(), syncShares());

    }
    public OnSyncCallBack getSyncCallback() {
        return mCallback;
    }

    public void setSyncCallback(OnSyncCallBack mCallback) {
        this.mCallback = mCallback;
    }

    private GraphRequest syncFriends() {
        return new GraphRequest(
                AccessToken.getCurrentAccessToken(),
                "/me/friends?fields=name,picture",
                null,
                HttpMethod.GET,
                new GraphRequest.Callback() {
                    public void onCompleted(GraphResponse response) {
                        try {
                            JSONObject json = response.getJSONObject();

                            JSONArray friendsList = json.getJSONArray("data");
                            for(int i = 0; i < friendsList.length(); i++) {
                                JSONObject friendObject = friendsList.getJSONObject(i);
                                Friend friend = new Friend();
                                friend.setId(friendObject.getString("id"));
                                friend.setName(friendObject.getString("name"));

                                JSONObject pictureObject = friendObject.getJSONObject("picture").getJSONObject("data");
                                Picture picture = new Picture();
                                picture.setSilluette(pictureObject.getBoolean("is_silhouette"));
                                picture.setURL(new URL(pictureObject.getString("url")));

                                friend.setPicture(picture);
                                mFriendList.add(friend);
                            }
                        } catch (Exception e) {
                            Log.e(TAG, "", e);
                        }

                    }
                }
        );
    }

    private GraphRequest syncShares() {
        return new GraphRequest(
                AccessToken.getCurrentAccessToken(),
                "/me/likes?fields=about,name,picture,website,category_list,category",
                null,
                HttpMethod.GET,
                new GraphRequest.Callback() {
                    public void onCompleted(GraphResponse response) {
                        try {
                            JSONObject json = response.getJSONObject();

                            JSONArray friendsList = json.getJSONArray("data");
                            for(int i = 0; i < friendsList.length(); i++) {
                                JSONObject friendObject = friendsList.getJSONObject(i);
                                Share share = new Share();
                                share.setId(friendObject.getString("id"));
                                share.setName(friendObject.getString("name"));
                                if (friendObject.has("website")) {
                                    share.setUrl(new URL(friendObject.getString("website")));
                                }
                                JSONObject pictureObject = friendObject.getJSONObject("picture").getJSONObject("data");
                                Picture picture = new Picture();
                                picture.setSilluette(pictureObject.getBoolean("is_silhouette"));
                                picture.setURL(new URL(pictureObject.getString("url")));

                                share.setPicture(picture);
                                mShareList.add(share);
                                mCallback.onSyncCompleted();
                            }
                        } catch (Exception e) {
                            Log.e(TAG, "", e);
                        }

                    }
                }
        );
    }

    public ArrayList<Friend> getFriends() {
        return mFriendList;
    }

    public ArrayList<Share> getShares() {
        return mShareList;
    }


}
