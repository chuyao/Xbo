package com.chuyao.xbo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;

import com.sina.weibo.sdk.auth.AuthInfo;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.auth.WeiboAuthListener;
import com.sina.weibo.sdk.auth.sso.SsoHandler;
import com.sina.weibo.sdk.exception.WeiboException;
import com.sina.weibo.sdk.net.RequestListener;
import com.sina.weibo.sdk.openapi.UsersAPI;
import com.sina.weibo.sdk.openapi.models.User;

public class WeiboAuthActivity extends AppCompatActivity implements WeiboAuthListener, RequestListener{

    private static final String TAG = "WeiboAuthActivity";

    private SsoHandler mSsoHandler;
    private AuthInfo mAuthInfo;
    protected Oauth2AccessToken mAccessToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuthInfo = new AuthInfo(this, Constants.APP_KEY, Constants.REDIRECT_URL, Constants.SCOPE);
        mSsoHandler = new SsoHandler(this, mAuthInfo);
    }

    @Override
    protected void onResume() {
        mAccessToken = AccessTokenKeeper.readAccessToken(this);
        if(mAccessToken.isSessionValid()){
            long expired_in = mAccessToken.getExpiresTime();
            long now_time = System.currentTimeMillis();
            if(now_time > expired_in)
                mSsoHandler.authorizeWeb(this);
        }else{
            mSsoHandler.authorizeWeb(this);
        }
        super.onResume();
    }

    /**
     * 认证信息回调
     * @param bundle
     */
    @Override
    public void onComplete(Bundle bundle) {
        mAccessToken = Oauth2AccessToken.parseAccessToken(bundle);
        if(mAccessToken.isSessionValid()) {
            AccessTokenKeeper.writeAccessToken(this, mAccessToken);
            Log.v(TAG, mAccessToken.toString());
            getUserInfo(mAccessToken);
        }
    }

    protected void getUserInfo(Oauth2AccessToken token){
        UsersAPI usersAPI = new UsersAPI(this, Constants.APP_KEY, token);
        usersAPI.show(Long.parseLong(token.getUid()), this);
    }

    /**
     * 认证用户信息回调
     * @param s
     */
    @Override
    public void onComplete(String s) {
        Log.d(TAG, "onComplete: " + s);
        if(!TextUtils.isEmpty(s)) {
            User user = User.parse(s);
        }
    }

    @Override
    public void onWeiboException(WeiboException e) {
        Log.d(TAG, "onWeiboException: " + e.getMessage());
    }

    /**
     * 登录授权web页左上角关闭按钮或系统返回键动作回调
     */
    @Override
    public void onCancel() {
        Log.d(TAG, "onCancel");
        super.finish();
    }
}
