package com.tts.newsclient_projectone.sourceop;

import com.tts.newsclient_projectone.exceptions.ErrorResponseCodeException;
import com.tts.newsclient_projectone.sourceprovider.ISource;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by 37444 on 2018/3/22.
 */

public class HttpStreamOP implements ISource {

    @Override
    public InputStream getInputStream(String path) throws ErrorResponseCodeException,Exception {

        InputStream in = null;

        URL url = new URL(path);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setConnectTimeout(5000);
        int code = conn.getResponseCode();
        if (code == 200) {
            in = conn.getInputStream();
        }else{
            throw new ErrorResponseCodeException("error response code is"+code);
        }
        return in;
    }
}

