package me.sofianehamadi.flyingbird.common;

import android.content.Context;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.util.Log;
import android.util.SparseIntArray;

import java.util.HashMap;

import me.sofianehamadi.flyingbird.GameActivity;
import me.sofianehamadi.flyingbird.R;
import me.sofianehamadi.flyingbird.core.GameApplicationConfigurations;

/**
 * Created by MISTERSOFT on 05/03/2017.
 */

public class AudioGame implements SoundPool.OnLoadCompleteListener, MediaPlayer.OnErrorListener {

    // Short sounds
    public static int PLAYER_JUMP = 1;
    public static int PICKUP_COIN = 2;
    public static int BUY_BIRD = 3;
    public static int LOSE = 4;

    private Context context;
    private MediaPlayer mediaPlayer; // Ambiant music
    private SparseIntArray soundPoolIds;
    private SoundPool sound;
    private float fxVolume;
    private float ambiantVolume;
    private boolean isFXPlayable = false;
    private int FXPlayableCount = 0;

    public AudioGame(Context _context) {
        context = _context;

        SharedPreferences sp = ((GameActivity)_context).getSharedPreferences(GameApplicationConfigurations.VOLUMES_PREFERENCES, Context.MODE_PRIVATE);
        Log.d("volumes", "AudioGame: fx = " + sp.getFloat(GameApplicationConfigurations.FX_VOLUME, GameApplicationConfigurations.DEFAULT_VOLUME) + " | ambiant = " + sp.getFloat(GameApplicationConfigurations.AMBIANT_VOLUME, GameApplicationConfigurations.DEFAULT_VOLUME));
        fxVolume = sp.getFloat(GameApplicationConfigurations.FX_VOLUME, GameApplicationConfigurations.DEFAULT_VOLUME);
        ambiantVolume = sp.getFloat(GameApplicationConfigurations.AMBIANT_VOLUME, GameApplicationConfigurations.DEFAULT_VOLUME);

        soundPoolIds = new SparseIntArray();
        sound = new SoundPool(4, AudioManager.STREAM_MUSIC, 0);
        soundPoolIds.put(PLAYER_JUMP, sound.load(_context, R.raw.jump_sound, 1));
        soundPoolIds.put(PICKUP_COIN, sound.load(_context, R.raw.pickup_coin_sound, 1));
        soundPoolIds.put(BUY_BIRD, sound.load(_context, R.raw.bought_sound, 1));
        soundPoolIds.put(LOSE, sound.load(_context, R.raw.lose_sound, 1));
        sound.setOnLoadCompleteListener(this);

        mediaPlayer = this.MediaBuilder(R.raw.jumping_bat_nes);
    }

    private MediaPlayer MediaBuilder(int resId) {
        MediaPlayer mp = MediaPlayer.create(context, resId);
        mp.setVolume(this.ambiantVolume, this.ambiantVolume);
        mp.setLooping(true);
        return mp;
    }

    /**
     * Play ambiant music
     */
    public void playLoopAmbiantFX() {
        if (this.mediaPlayer != null) {
            this.mediaPlayer.release();
            this.mediaPlayer = this.MediaBuilder(R.raw.jumping_bat_nes);
        }
        this.mediaPlayer.start();
    }

    /**
     * Stop MediaPlayer sound
     */
    public void stopAmbiantFX() {
        this.mediaPlayer.stop();
    }

    /**
     * Release memory
     */
    public void release() {
        this.mediaPlayer.stop();
        this.mediaPlayer.release();
        for (int i = 0; i < soundPoolIds.size(); i++) {
            this.sound.stop(i);
            this.sound.release();
        }
    }

    /**
     * Play SoundPool audio
     * @param type Type of sound
     */
    public void playFX(Integer type) {
        if (isFXPlayable) {
            this.stopFX(type);
            sound.play(type, this.fxVolume, this.fxVolume, 1, 0, 1.0f);
        }
    }

    /**
     * Stop SoundPool audio
     * @param type SoundPool ID
     */
    public void stopFX(Integer type) {
        sound.stop(type);
    }

    @Override
    public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
        if (status == 0) {
            Log.i("", "onLoadComplete: successfully loaded");
            this.FXPlayableCount++;
            Log.i("COUNT", "onLoadComplete: " + sampleId + " ids = " + soundPoolIds.size() + " ; playable = " + FXPlayableCount);
            if (this.soundPoolIds.size() == this.FXPlayableCount) {
                isFXPlayable = true;
            }
        }
    }

    @Override
    public boolean onError(MediaPlayer mp, int what, int extra) {
        if (what == 100) {
            mp.release();
        }
        return false;
    }
}
