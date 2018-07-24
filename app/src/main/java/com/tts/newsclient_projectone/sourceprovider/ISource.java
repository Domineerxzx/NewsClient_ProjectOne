package com.tts.newsclient_projectone.sourceprovider;

import java.io.InputStream;

/**
 * Created by 37444 on 2018/4/2.
 */

public interface ISource {

    InputStream getInputStream(String path) throws Exception;

}
