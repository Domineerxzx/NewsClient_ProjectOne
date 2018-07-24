package com.tts.newsclient_projectone.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Message;
import android.os.Parcelable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import com.tts.newsclient_projectone.R;
import com.tts.newsclient_projectone.beans.NewsInfo;
import com.tts.newsclient_projectone.enumeration.SourceType;
import com.tts.newsclient_projectone.handler.MyHandler;
import com.tts.newsclient_projectone.managers.NewsManager;
import com.tts.newsclient_projectone.properties.AppProperties;

import java.io.SerializablePermission;
import java.util.List;

public class MainActivity extends Activity {

    private ListView lv_news;
    private MyHandler myHandler;
    private NewsManager newsManager;
    private SharedPreferences config;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        config = getSharedPreferences("config", Context.MODE_APPEND);
        String type = config.getString("type", SourceType.SOURCE_FROM_SERVER.getType());
        String path = config.getString("path", AppProperties.SERVER_PATH);
        initView();
        initData();
        showNewsList(SourceType.valueOf(type),path);
    }

    private void initView() {
        lv_news = (ListView) findViewById(R.id.lv_news);
    }

    private void showNewsList(final SourceType sourceType,final String path) {
        new Thread() {
            @Override
            public void run() {
                List<NewsInfo> newsList = newsManager.getNewsListFromSource
                        (MainActivity.this, sourceType, path);
                Message msy = Message.obtain();
                msy.obj = newsList;
                msy.what = AppProperties.NEWSLISTSUCCESS;
                myHandler.sendMessage(msy);
            }
        }.start();

    }

    private void initData() {
        myHandler = new MyHandler(lv_news, this);
        newsManager = new NewsManager();
    }

    public void buttonClick(View v) {
        SharedPreferences.Editor editor = config.edit();
        switch (v.getId()) {
            case R.id.bt_server:
                editor.putString("type",SourceType.SOURCE_FROM_SERVER.getType());
                editor.putString("path",AppProperties.SERVER_PATH);
                serverClick();
                break;
            case R.id.bt_assets:
                editor.putString("type",SourceType.SOURCE_FROM_ASSETS.getType());
                editor.putString("path",AppProperties.ASSETS_PATH);
                assetsClick();
                break;
            case R.id.bt_db:
                editor.putString("type",SourceType.SOURCE_FROM_DB.getType());
                editor.putString("path","");
                dbClick();
                break;
        }
        editor.commit();
    }

    private void dbClick() {
        showNewsList(SourceType.SOURCE_FROM_DB,"");
    }

    private void assetsClick() {
        showNewsList(SourceType.SOURCE_FROM_ASSETS,AppProperties.ASSETS_PATH);
    }

    private void serverClick() {
        showNewsList(SourceType.SOURCE_FROM_SERVER,AppProperties.SERVER_PATH);
    }
}
