package me.sofianehamadi.flyingbird;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;

import me.sofianehamadi.flyingbird.core.GameApplicationConfigurations;

public class OptionsActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener {
    /**
     * SharedPreferences
     */
    private SharedPreferences sp;
    /**
     * Current ambiant volume
     */
    private float ambiantVolume;
    /**
     * Current FX volume
     */
    private float fxVolume;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);

        this.sp = this.getSharedPreferences(GameApplicationConfigurations.VOLUMES_PREFERENCES, Context.MODE_PRIVATE);

        final SeekBar ambiantVolumeSeekBar = (SeekBar) findViewById(R.id.seekBarAmbiantVolume);
        ambiantVolumeSeekBar.setProgress(this.convertVolumeForProgress(sp.getFloat(GameApplicationConfigurations.AMBIANT_VOLUME, GameApplicationConfigurations.DEFAULT_VOLUME)));
        ambiantVolumeSeekBar.setOnSeekBarChangeListener(this);

        final SeekBar fxVolumeSeekBar = (SeekBar) findViewById(R.id.seekBarFXVolume);
        fxVolumeSeekBar.setProgress(this.convertVolumeForProgress(sp.getFloat(GameApplicationConfigurations.FX_VOLUME, GameApplicationConfigurations.DEFAULT_VOLUME)));
        fxVolumeSeekBar.setOnSeekBarChangeListener(this);

        final ImageButton saveBtn = (ImageButton) findViewById(R.id.save_button);
        saveBtn.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        saveBtn.setImageResource(R.drawable.save_pressed);
                        break;
                    case MotionEvent.ACTION_UP:
                        saveBtn.setImageResource(R.drawable.save_unpressed);
                        SharedPreferences.Editor editor = sp.edit();
                        editor.putFloat(GameApplicationConfigurations.AMBIANT_VOLUME, ambiantVolume);
                        editor.putFloat(GameApplicationConfigurations.FX_VOLUME, fxVolume);
                        editor.commit();
                        startActivity(new Intent(OptionsActivity.this, MenuActivity.class));
                        break;
                    default: break;
                }
                return false;
            }
        });
    }

    /**
     * Detect progress changed
     * @param seekBar Seekbar
     * @param progress Progress value
     * @param fromUser
     */
    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        switch (seekBar.getId()) {
            case R.id.seekBarAmbiantVolume:
                this.ambiantVolume = this.convertProgressForVolume(progress);
                break;
            case R.id.seekBarFXVolume:
                this.fxVolume = this.convertProgressForVolume(progress);
            default: break;
        }
    }

    /**
     * Convert the value given by the SeekBar to a float value between 0.0f and 1.0f
     * @param progress Progress valiue
     * @return Volume value
     */
    private float convertProgressForVolume(int progress) {
        return progress / 100f;
    }

    /**
     * Convert the value given by the value saved into SharedPreferences to a int value between 0 and 100
     * @param volume Volume value
     * @return Progress value
     */
    private int convertVolumeForProgress(float volume) {
        return (int)(volume * 100);
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) { }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) { }
}
