package com.tts.newsclient_projectone.activities;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.widget.TextView;

import com.tts.newsclient_projectone.R;
import com.tts.newsclient_projectone.beans.NewsInfo;
import com.tts.newsclient_projectone.myview.MyImageView;

public class NewsReadActivity extends Activity {

    private TextView tv_news_title;
    private TextView tv_news_description;
    private MyImageView iv_news_image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_read);
        tv_news_title = (TextView) findViewById(R.id.tv_news_title);
        tv_news_description = (TextView) findViewById(R.id.tv_news_description);
        iv_news_image = (MyImageView) findViewById(R.id.iv_news_image);
        Intent intent = getIntent();
        NewsInfo newsInfo = (NewsInfo)intent.getParcelableExtra("newsInfo");
        tv_news_title.setText(newsInfo.getTitle());
        tv_news_description.setText(newsInfo.getDescription());
        iv_news_image.setImageUrl(newsInfo.getImage());
    }

}
