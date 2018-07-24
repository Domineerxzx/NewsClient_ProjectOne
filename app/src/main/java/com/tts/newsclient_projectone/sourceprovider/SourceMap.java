package com.tts.newsclient_projectone.sourceprovider;

import android.content.Context;

import com.tts.newsclient_projectone.enumeration.SourceType;
import com.tts.newsclient_projectone.sourceop.AssetsStreamOP;
import com.tts.newsclient_projectone.sourceop.HttpStreamOP;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by 37444 on 2018/4/2.
 */

public class SourceMap {

    public static Map<SourceType,ISource> generateSource(Context context){
        Map<SourceType, ISource> sourceMap = new HashMap<>();
        sourceMap.put(SourceType.SOURCE_FROM_SERVER,new HttpStreamOP());
        sourceMap.put(SourceType.SOURCE_FROM_ASSETS,new AssetsStreamOP(context));
        return sourceMap;
    }
}
