package com.god.gl.vaccination.util;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;

import com.god.gl.vaccination.R;

/**
 * @author gl
 * @date 2018/12/15
 * @desc
 */
public class SoundPlayUtils {

    // SoundPool对象
    public static SoundPool mSoundPlayer = new SoundPool(1,
            AudioManager.STREAM_SYSTEM, 5);
    public static SoundPlayUtils soundPlayUtils;
    // 上下文
    static Context mContext;

    /**
     * 初始化
     *
     * @param context
     */
    public static SoundPlayUtils init(Context context) {
        if (soundPlayUtils == null) {
            soundPlayUtils = new SoundPlayUtils();
        }

        // 初始化声音
        mContext = context;
        mSoundPlayer.load(mContext, R.raw.bee, 1);

        return soundPlayUtils;
    }

    /**
     * 播放声音
     *
     * @param soundID
     */
    public static void play(final int soundID) {

        mSoundPlayer.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
            @Override
            public void onLoadComplete(SoundPool soundPool, int i, int i2) {
                soundPool.play(soundID,  //声音id
                        1, //左声道
                        1, //右声道
                        1, //优先级
                        0, // 0表示不循环，-1表示循环播放
                        1);//播放比率，0.5~2，一般为1
            }
        });

    }

}
