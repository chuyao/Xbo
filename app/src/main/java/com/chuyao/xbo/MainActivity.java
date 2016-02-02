package com.chuyao.xbo;

import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;

import com.chuyao.xbo.adapter.TimeLineLIstAdapter;
import com.chuyao.xbo.model.Status;
import com.chuyao.xbo.model.TimelineData;
import com.chuyao.xbo.view.XSwipeRefreshLayout;
import com.facebook.drawee.view.SimpleDraweeView;
import com.sina.weibo.sdk.openapi.models.User;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends WeiboAuthActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static final String TAG = "MainActivity";
    private XSwipeRefreshLayout mSwipeRefreshLayout;
    private ListView mListView;
    private TimeLineLIstAdapter mAdapter;
    private long since_id;
    private long max_id;
    private int page;

    private List<Status> mData = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        initViews();
    }

    private void initViews(){
        mSwipeRefreshLayout = (XSwipeRefreshLayout) findViewById(R.id.swipeLayout);
        mSwipeRefreshLayout.setOnLoadListener(loadListener);
        mListView = (ListView) findViewById(R.id.listView);
        mAdapter = new TimeLineLIstAdapter(this, mData);
        mListView.setAdapter(mAdapter);
    }

    final XSwipeRefreshLayout.OnLoadListener loadListener = new XSwipeRefreshLayout.OnLoadListener() {
        @Override
        public void onLoad() {
            getTimeline(0, max_id, 50, page, false, 0, false);
        }

        @Override
        public void onRefresh() {
            page = 0;
            getTimeline(0, 0, 50, 1, false, 0, false);
        }
    };

    @Override
    protected void onResume() {
        super.onResume();
        if(mAdapter.isEmpty()){
            mSwipeRefreshLayout.onRefresh();
        }
    }

    private void updateUserInfo(User user){
        ((SimpleDraweeView) findViewById(R.id.ivHeader)).setImageURI(Uri.parse(user.profile_image_url));
        ((TextView) findViewById(R.id.tvName)).setText(user.name);
    }

    @Override
    public void onComplete(String s) {
        super.onComplete(s);
        updateUserInfo(User.parse(s));
    }

    @Override
    protected void onTimeLineComplete(String s) {
        super.onTimeLineComplete(s);
    }

    @Override
    protected void onTimeLineComplete(TimelineData timelineData) {
        super.onTimeLineComplete(timelineData);
        if(timelineData.statuses != null) {
            if (mSwipeRefreshLayout.status == XSwipeRefreshLayout.Status.REFRESH) {
                mData.clear();
                mData.addAll(timelineData.statuses);
            } else if(mSwipeRefreshLayout.status == XSwipeRefreshLayout.Status.LOAD){
                mData.addAll(timelineData.statuses);
                mSwipeRefreshLayout.setLoading(false);
            }
            page++;
        }
        mSwipeRefreshLayout.setRefreshing(false);
        mAdapter.notifyDataSetChanged();
        since_id = timelineData.since_id;
        max_id = timelineData.max_id;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
