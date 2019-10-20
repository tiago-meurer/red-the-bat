package com.gilvanstudios.redthebat;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;

public class SoundPlayer {

    private static SoundPool soundPool;
    private static int flySound;
    private static int screamSound;


    public SoundPlayer (Context context){


        soundPool = new SoundPool(2, AudioManager.STREAM_MUSIC,0);

        flySound = soundPool.load(context, R.raw.batefx,1);
        screamSound = soundPool.load(context, R.raw.scream,1);

    }

    public void playFlySound(){

        soundPool.play(flySound, 1.0f, 1.0f,1,0,1.0f);
    }

    public void playScreamSound(){

        soundPool.play(screamSound, 1.0f, 1.0f,1,0,1.0f);
    }


}
