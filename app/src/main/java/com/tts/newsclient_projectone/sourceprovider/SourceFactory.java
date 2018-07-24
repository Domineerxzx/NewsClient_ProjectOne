package com.tts.newsclient_projectone.sourceprovider;

import android.content.Context;

import com.tts.newsclient_projectone.enumeration.SourceType;

/**
 * Created by 37444 on 2018/4/2.
 */

public class SourceFactory {
    public static ISource sourceCreate(Context context, SourceType type){
        return SourceMap.generateSource(context).get(type);
    }
}
