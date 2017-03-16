package me.sofianehamadi.flyingbird;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;

import me.sofianehamadi.flyingbird.core.GameApplicationConfigurations;

public class OptionsActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener, ImageButton.OnClickListener {
    private SharedPreferences sp;
    private float ambiantVolume;
    private float fxVolume;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);

        this.sp = this.getSharedPreferences(GameApplicationConfigurations.VOLUMES_PREFERENCES, Context.MODE_PRIVATE);

        SeekBar ambiantVolumeSeekBar = (SeekBar) findViewById(R.id.seekBarAmbiantVolume);
        ambiantVolumeSeekBar.setProgress(this.convertVolumeForProgress(sp.getFloat(GameApplicationConfigurations.AMBIANT_VOLUME, GameApplicationConfigurations.DEFAULT_VOLUME)));
        ambiantVolumeSeekBar.setOnSeekBarChangeListener(this);

        SeekBar fxVolumeSeekBar = (SeekBar) findViewById(R.id.seekBarFXVolume);
        fxVolumeSeekBar.setProgress(this.convertVolumeForProgress(sp.getFloat(GameApplicationConfigurations.FX_VOLUME, GameApplicationConfigurations.DEFAULT_VOLUME)));
        fxVolumeSeekBar.setOnSeekBarChangeListener(this);

        ImageButton saveBtn = (ImageButton) findViewById(R.id.save_button);
        saveBtn.setOnClickListener(this);
    }

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

    @Override
    public void onClick(View v) {
        SharedPreferences.Editor editor = sp.edit();
        editor.putFloat(GameApplicationConfigurations.AMBIANT_VOLUME, this.ambiantVolume);
        editor.putFloat(GameApplicationConfigurations.FX_VOLUME, this.fxVolume);
        editor.commit();
        startActivity(new Intent(this, MenuActivity.class));
    }

    private float convertProgressForVolume(int progress) {
        return progress / 100f;
    }

    private int convertVolumeForProgress(float volume) {
        return (int)(volume * 100);
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) { }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {  }
}
