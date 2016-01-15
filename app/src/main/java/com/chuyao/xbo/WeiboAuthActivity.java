package com.chuyao.xbo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.chuyao.xbo.util.AccessTokenKeeper;
import com.sina.weibo.sdk.auth.AuthInfo;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.auth.WeiboAuthListener;
import com.sina.weibo.sdk.auth.sso.SsoHandler;
import com.sina.weibo.sdk.exception.WeiboException;
import com.sina.weibo.sdk.net.RequestListener;
import com.sina.weibo.sdk.openapi.StatusesAPI;
import com.sina.weibo.sdk.openapi.UsersAPI;

public class WeiboAuthActivity extends AppCompatActivity implements WeiboAuthListener, RequestListener{

    private static final String TAG = "WeiboAuthActivity";

    private SsoHandler mSsoHandler;
    private AuthInfo mAuthInfo;
    protected Oauth2AccessToken mAccessToken;
    private StatusesAPI statusAPI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuthInfo = new AuthInfo(this, Constants.APP_KEY, Constants.REDIRECT_URL, Constants.SCOPE);
        mSsoHandler = new SsoHandler(this, mAuthInfo);
    }

    @Override
    protected void onResume() {
        checkWeiboBaseStatus();
        super.onResume();
    }

    private void checkWeiboBaseStatus(){
        if(!isTokenValid()){
            mSsoHandler.authorizeWeb(this);
        }else{
            getUserInfo();
        }
    }

    private boolean isTokenValid(){
        mAccessToken = AccessTokenKeeper.readAccessToken(this);
        if(mAccessToken.isSessionValid()){
            long expired_in = mAccessToken.getExpiresTime();
            long now_time = System.currentTimeMillis();
            return expired_in > now_time;
        }
        return false;
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
            getUserInfo();
        }
    }

    private void getUserInfo(){
        UsersAPI usersAPI = new UsersAPI(this, Constants.APP_KEY, mAccessToken);
        usersAPI.show(Long.parseLong(mAccessToken.getUid()), this);
    }

    protected void getTimeline(long since_id, long max_id, int count, int page, boolean base_app,
                             int featureType, boolean trim_user){
        if(!mAccessToken.isSessionValid())
            return;
        statusAPI = new StatusesAPI(this, Constants.APP_KEY, mAccessToken);
        RequestListener listener = new RequestListener() {
            @Override
            public void onComplete(String s) {
                onTimeLineComplete(s);
            }

            @Override
            public void onWeiboException(WeiboException e) {

            }
        };
        statusAPI.friendsTimeline(since_id, max_id, count, page, base_app, featureType, trim_user, listener);
    }

    protected void onTimeLineComplete(String s){

    }

    /**
     * 认证用户信息回调
     * @param s
     */
    @Override
    public void onComplete(String s) {
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
