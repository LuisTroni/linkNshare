package com.wordpress.deizsite.linknshare;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.Profile;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.wordpress.deizsite.linknshare.dataSources.Facebook;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

/**
 *
 * Code2care.org
 *
 * Author : Code2care
 *
 * Date : 20150506
 *
 * Note : All you need to do is configure your
 * Facebook APP ID in String XML before running this
 * APP
 *
 *
 */

public class LoginActivity extends AppCompatActivity implements Facebook.OnSyncCallBack {

    private CallbackManager callbackManager;
    private LoginButton fbLoginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FacebookSdk.sdkInitialize(getApplicationContext());
        callbackManager = CallbackManager.Factory.create();



        setContentView(R.layout.main_activity);

        //You need this method to be used only once to configure
        //your key hash in your App Console at
        // developers.facebook.com/apps




        fbLoginButton = (LoginButton)findViewById(R.id.fb_login_button);

        fbLoginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {

                System.out.println("Facebook Login Successful!");
                System.out.println("Logged in user Details : ");
                System.out.println("--------------------------");
                System.out.println("User ID  : " + loginResult.getAccessToken().getUserId());
                System.out.println("Authentication Token : " + loginResult.getAccessToken().getToken());
                Toast.makeText(LoginActivity.this, "Login Successful!", Toast.LENGTH_LONG).show();
                Facebook.getInstance().setSyncCallback(LoginActivity.this);
                Facebook.getInstance().syncData();
            }

            @Override
            public void onCancel() {
                Toast.makeText(LoginActivity.this, "Login cancelled by user!", Toast.LENGTH_LONG).show();
                System.out.println("Facebook Login failed!!");

            }

            @Override
            public void onError(FacebookException e) {
                Toast.makeText(LoginActivity.this, "Login unsuccessful!", Toast.LENGTH_LONG).show();
                System.out.println("Facebook Login failed!!");
            }
        });
        fbLoginButton.setReadPermissions(Arrays.asList("user_about_me","user_friends","user_likes"));
    }

    @Override
    protected void onActivityResult(int reqCode, int resCode, Intent i) {
        callbackManager.onActivityResult(reqCode, resCode, i);
    }

    @Override
    public void onSyncCompleted() {
        Facebook faceBook = Facebook.getInstance();
        System.out.println(faceBook.getFriends());
        System.out.println(faceBook.getShares());
    }
}