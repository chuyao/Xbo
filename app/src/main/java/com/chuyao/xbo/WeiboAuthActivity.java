package com.chuyao.xbo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.sina.weibo.sdk.auth.AuthInfo;
import com.sina.weibo.sdk.auth.WeiboAuthListener;
import com.sina.weibo.sdk.auth.sso.SsoHandler;
import com.sina.weibo.sdk.exception.WeiboException;

public class WeiboAuthActivity extends AppCompatActivity implements WeiboAuthListener{

    private SsoHandler mSsoHandler;
    private AuthInfo mAuthInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    private void initAuth(){
        mAuthInfo = new AuthInfo(this, Config.APP_KEY, Config.REDIRECT_URL, Config.SCOPE);
        mSsoHandler = new SsoHandler(this, mAuthInfo);
    }

    @Override
    public void onComplete(Bundle bundle) {

    }

    @Override
    public void onWeiboException(WeiboException e) {

    }

    @Override
    public void onCancel() {

    }
}
