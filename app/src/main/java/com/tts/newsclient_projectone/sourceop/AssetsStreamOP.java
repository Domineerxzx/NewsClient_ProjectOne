package com.tts.newsclient_projectone.sourceop;

import android.content.Context;

import com.tts.newsclient_projectone.sourceprovider.ISource;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by 37444 on 2018/3/29.
 */

public class AssetsStreamOP implements ISource{
    private Context context;

    public AssetsStreamOP(Context context) {
        this.context = context;
    }

    @Override
    public InputStream getInputStream(String path) throws Exception {
            return context.getAssets().open(path);
    }
}
