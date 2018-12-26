package com.god.gl.vaccination.service;

import android.app.IntentService;
import android.content.Intent;
import android.os.Handler;
import android.support.annotation.Nullable;

import com.god.gl.vaccination.util.LogX;
import com.god.gl.vaccination.util.SoundPlayUtils;

/**
 * @author gl
 * @date 2018/12/15
 * @desc
 */
public class PlayService extends IntentService {

    private Handler mHandler;

    public PlayService() {
        super("PlayService");
    }

    public PlayService(String name) {
        super(name);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mHandler = new Handler(getMainLooper());
        SoundPlayUtils.init(getApplicationContext());

    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                SoundPlayUtils.play(1);
            }
        });


    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        LogX.e("onDestroy","onDestroy");
    }
}
