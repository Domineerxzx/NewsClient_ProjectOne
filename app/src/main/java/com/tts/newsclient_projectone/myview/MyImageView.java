package com.tts.newsclient_projectone.myview;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.tts.newsclient_projectone.R;
import com.tts.newsclient_projectone.cache.ImageCacheOP;

public class MyImageView extends ImageView {
    private Context context;

    public MyImageView(Context context) {
        super(context);
        this.context = context;
    }

    public MyImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }

    public MyImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
    }
    public void setImageUrl(final String path){
        new Thread(){
            @Override
            public void run() {
                final Bitmap bitmap = new ImageCacheOP(context).getBitmapFromURL(path);
                ((Activity)context).runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if(bitmap == null){
                            MyImageView.this.setImageResource(R.mipmap.ic_launcher_round);
                        }else{
                            MyImageView.this.setImageBitmap(bitmap);
                        }
                    }
                });
            }
        }.start();
    }
}
