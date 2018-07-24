package com.tts.newsclient_projectone.managers;

import android.content.Context;

import com.tts.newsclient_projectone.beans.NewsInfo;
import com.tts.newsclient_projectone.enumeration.SourceType;
import com.tts.newsclient_projectone.sourceop.sqlutils.NewsSqlOP;
import com.tts.newsclient_projectone.sourceprovider.ISource;
import com.tts.newsclient_projectone.sourceprovider.SourceFactory;
import com.tts.newsclient_projectone.utils.NewsXmlParser;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 37444 on 2018/3/22.
 */

public class NewsManager {
    public List<NewsInfo> getNewsListFromSource(final Context context, SourceType type, String path) {
        List<NewsInfo> newsList = new ArrayList<NewsInfo>();
        switch (type) {
            case SOURCE_FROM_ASSETS:
            case SOURCE_FROM_SERVER:
                newsList = getNewsListFromInputStream(context, type, path);
                break;
            case SOURCE_FROM_DB:
                newsList = new NewsSqlOP(context).queryNews();
                break;
        }
        return newsList;
    }

    private List<NewsInfo> getNewsListFromInputStream(final Context context, SourceType type, String path) {
        List<NewsInfo> newsList = new ArrayList<NewsInfo>();
        InputStream in = null;
        ISource iSource = SourceFactory.sourceCreate(context, type);
        try {
            in = iSource.getInputStream(path);
            if (in != null) {
                newsList = NewsXmlParser.parse(in);
                final List<NewsInfo> finalNewsList = newsList;

                new Thread() {
                    @Override
                    public void run() {
                        NewsSqlOP newsSqlOP = new NewsSqlOP(context);
                        while (!newsSqlOP.insert(finalNewsList)) {
                            System.out.println("------------------失败----------------");
                        }
                        System.out.println("---------------------成功----------------------");
                    }
                }.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return newsList;
    }

}
/**
 * public List<NewsInfo> getNewsListFromInputStream(Context context, SourceType type, String path){
 * List<NewsInfo> newsList = new ArrayList<NewsInfo>();
 * InputStream in = null;
 * switch (type){
 * case SOURCE_FROM_SERVER:
 * HttpStreamOP httpStreamOP = new HttpStreamOP();
 * in = httpStreamOP.getInputStream(path);
 * break;
 * case SOURCE_FROM_ASSETS:
 * in = new AssetsStreamOP(context).getInputStream(path);
 * break;
 * case SOURCE_FROM_DB:
 * break;
 * }
 * <p>
 * <p>
 * if(in !=null){
 * newsList = NewsXmlParser.parse(in);
 * try {
 * in.close();
 * } catch (IOException e) {
 * e.printStackTrace();
 * }
 * }
 * return newsList;
 * }
 */
