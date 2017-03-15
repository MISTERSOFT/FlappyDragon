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

public class AudioGame implements SoundPool.OnLoadCompleteListener, MediaPlayer.OnErrorListener, MediaPlayer.OnCompletionListener {

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
    private boolean isAmbiantePlayable = false;
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
        mediaPlayers.put(AMBIANCE, MediaPlayer.create(context, R.raw.jumping_bat_nes));
    }

    public void stop(Integer type) {
        sound.stop(type);
    }

    public void playLoopAmbiantFX(Integer type) {
        if (isAmbiantePlayable) {
            if (this.mediaPlayers.get(type).isPlaying()) {
                this.mediaPlayers.get(type).stop();
            }
            this.mediaPlayers.get(type).start();
        }
    }

    public void playFX(Integer type) {
        if (isFXPlayable) {
            this.stop(type);
            sound.play(type, volume, volume, 1, 0, 1.0f);
        }
//        mediaPlayer = MediaPlayer.create(context, resourceAudioID);
//        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
//            @Override
//            public void onCompletion(MediaPlayer mp) {
//                stop();
//            }
//        });
//        mediaPlayer.setOnErrorListener(this);
//        mediaPlayer.start();
    }

//    private void stop() {
//        if (mediaPlayer != null) {
//            mediaPlayer.release();
//            mediaPlayer = null;
//        }
//    }

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

    // TODO - Remove ?
    @Override
    public boolean onError(MediaPlayer mp, int what, int extra) {
        if (what == 100) {
            mp.release();
        }
        return false;
    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        Log.i("", "onCompletion: ambiant sound playable");
        mp.setVolume(this.volume, this.volume);
        mp.setLooping(true);
        isAmbiantePlayable = true;
    }
}
