package com.tts.newsclient_projectone.enumeration;

/**
 * Created by 37444 on 2018/4/2.
 */

public enum SourceType {

    SOURCE_FROM_SERVER("SOURCE_FROM_SERVER"),
    SOURCE_FROM_ASSETS("SOURCE_FROM_ASSETS"),
    SOURCE_FROM_DB("SOURCE_FROM_DB");

    private String type;

    SourceType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
