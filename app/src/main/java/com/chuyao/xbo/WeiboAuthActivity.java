package com.chuyao.xbo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.sina.weibo.sdk.auth.AuthInfo;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.auth.WeiboAuthListener;
import com.sina.weibo.sdk.auth.sso.SsoHandler;
import com.sina.weibo.sdk.exception.WeiboException;

public class WeiboAuthActivity extends AppCompatActivity implements WeiboAuthListener{

    private static final String TAG = "WeiboAuthActivity";

    private SsoHandler mSsoHandler;
    private AuthInfo mAuthInfo;
    private Oauth2AccessToken mAccessToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuthInfo = new AuthInfo(this, Constants.APP_KEY, Constants.REDIRECT_URL, Constants.SCOPE);
        mSsoHandler = new SsoHandler(this, mAuthInfo);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mSsoHandler.authorizeWeb(this);
    }

    @Override
    public void onComplete(Bundle bundle) {
        mAccessToken = Oauth2AccessToken.parseAccessToken(bundle);
        Log.v(TAG, mAccessToken.toString());
    }

    @Override
    public void onWeiboException(WeiboException e) {

    }

    @Override
    public void onCancel() {

    }
}
