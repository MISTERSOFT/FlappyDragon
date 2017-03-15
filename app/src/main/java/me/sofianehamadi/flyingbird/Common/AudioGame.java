package me.sofianehamadi.flyingbird.common;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.util.Log;
import android.util.SparseIntArray;

import java.util.HashMap;

import me.sofianehamadi.flyingbird.R;

/**
 * Created by MISTERSOFT on 05/03/2017.
 */

public class AudioGame implements SoundPool.OnLoadCompleteListener, MediaPlayer.OnErrorListener {//, MediaPlayer.OnCompletionListener {

    // Short sounds
    public static int PLAYER_JUMP = 1;
    public static int PICKUP_COIN = 2;
    public static int BUY_BIRD = 3;
    public static int LOSE = 4;
    // Long sounds
    public static int AMBIANCE = 4;

    private Context context;
    private HashMap<Integer, MediaPlayer> mediaPlayers;
    private SparseIntArray soundPoolIds;
    private SoundPool sound;
    private float volume = 1.0f;
    private boolean isFXPlayable = false;
    private int FXPlayableCount = 0;
//    private boolean isAmbiantePlayable = false;
    //private int AmbiantePlayableCount = 0;

    public AudioGame(Context _context) {
        context = _context;
        soundPoolIds = new SparseIntArray();
        sound = new SoundPool(4, AudioManager.STREAM_MUSIC, 0);
        soundPoolIds.put(PLAYER_JUMP, sound.load(_context, R.raw.jump_sound, 1));
        soundPoolIds.put(PICKUP_COIN, sound.load(_context, R.raw.pickup_coin_sound, 1));
        soundPoolIds.put(BUY_BIRD, sound.load(_context, R.raw.bought_sound, 1));
        soundPoolIds.put(LOSE, sound.load(_context, R.raw.lose_sound, 1));
        sound.setOnLoadCompleteListener(this);

        mediaPlayers = new HashMap<>();
        mediaPlayers.put(AMBIANCE, this.MediaBuilder(R.raw.jumping_bat_nes));
    }

    private MediaPlayer MediaBuilder(int resId) {
        MediaPlayer mp = MediaPlayer.create(context, resId);
        mp.setVolume(this.volume, this.volume);
        mp.setLooping(true);
        return mp;
    }

    /**
     * Play ambiant music
     * @param type Id of ambiant music
     */
    public void playLoopAmbiantFX(Integer type) {
        if (this.mediaPlayers.get(type).isPlaying()) {
            this.mediaPlayers.get(type).stop();
        }
        if (this.mediaPlayers.get(type) != null) {
            this.mediaPlayers.get(type).release();
            this.mediaPlayers.remove(type);
            this.mediaPlayers.put(type, this.MediaBuilder(R.raw.jumping_bat_nes));
        }
        this.mediaPlayers.get(type).start();
    }

    /**
     * Stop MediaPlayer sound
     * @param type Type of sound
     */
    public void stopAmbiantFX(Integer type) {
        this.mediaPlayers.get(type).pause();
        this.mediaPlayers.get(type).seekTo(0);
    }

    /**
     * Release memory
     */
    public void release() {
        this.mediaPlayers.get(0).stop();
        this.mediaPlayers.get(0).release();
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
            sound.play(type, volume, volume, 1, 0, 1.0f);
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
