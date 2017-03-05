package me.sofianehamadi.flyingbird.common;

import android.content.Context;
import android.media.MediaPlayer;

/**
 * Created by MISTERSOFT on 05/03/2017.
 */

public class AudioGame implements MediaPlayer.OnErrorListener {

    private Context context;
//    private Thread thread;
    private MediaPlayer mediaPlayer;
    private int resourceAudioID;

    public AudioGame(Context _context, int _resourceAudioID) {
        context = _context;
        resourceAudioID = _resourceAudioID;
    }

    public void play() {
        stop();
//        thread = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                mediaPlayer = MediaPlayer.create(context, resourceAudioID);
//                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
//                    @Override
//                    public void onCompletion(MediaPlayer mp) {
//                        stop();
//                    }
//                });
//                mediaPlayer.setOnErrorListener(AudioGame.this);
//                mediaPlayer.start();
//            }
//        });
//        thread.start();

        mediaPlayer = MediaPlayer.create(context, resourceAudioID);
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                stop();
            }
        });
        mediaPlayer.setOnErrorListener(this);
        mediaPlayer.start();
    }

    private void stop() {
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
//            stopAudioThread();
        }
    }

//    private void stopAudioThread() {
//        boolean retry = true;
//        while (retry) {
//            try {
//                thread.join();
//                retry = false;
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//    }

    // TODO - Remove ?
    @Override
    public boolean onError(MediaPlayer mp, int what, int extra) {
        if (what == 100) {
            stop();
        }
        return false;
    }
}
