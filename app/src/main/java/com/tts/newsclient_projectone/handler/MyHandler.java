package com.tts.newsclient_projectone.handler;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.tts.newsclient_projectone.activities.MainActivity;
import com.tts.newsclient_projectone.activities.NewsReadActivity;
import com.tts.newsclient_projectone.adapters.NewsAdapter;
import com.tts.newsclient_projectone.beans.NewsInfo;
import com.tts.newsclient_projectone.properties.AppProperties;

import java.util.List;

/**
 * Created by 37444 on 2018/3/26.
 */

public class MyHandler extends Handler {
    View view;
    Context context;
    public MyHandler(View view,Context context) {
        this.view = view;
        this.context =  context;
    }

    @Override
    public void handleMessage(Message msg) {
        switch (msg.what) {
            case AppProperties.NEWSLISTSUCCESS:
                final List<NewsInfo> newsInfoList = (List<NewsInfo>) msg.obj;
                NewsAdapter newsAdapter = new NewsAdapter(context, newsInfoList);
                ListView lv_news = (ListView) this.view;
                lv_news.setAdapter(newsAdapter);
                lv_news.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                Intent intent = new Intent((Activity)context, NewsReadActivity.class);
                                intent.putExtra("newsInfo", (Parcelable) newsInfoList.get(position));
                                context.startActivity(intent);
                    }
                });
                break;
        }
    }
}
