package me.sofianehamadi.flyingbird;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import me.sofianehamadi.flyingbird.common.FontCache;

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        TextView tvTitle = (TextView) findViewById(R.id.tvAboutTitle);
        TextView tvMe1 = (TextView) findViewById(R.id.tvAboutMe1);
        TextView tvMe2 = (TextView) findViewById(R.id.tvAboutMe2);
        TextView tvRepo = (TextView) findViewById(R.id.tvAboutRepo);

        tvTitle.setTypeface(FontCache.getTypeface(this, FontCache.PixelOperatorMono8));
        tvMe1.setTypeface(FontCache.getTypeface(this, FontCache.PixelOperatorMono8));
        tvMe2.setTypeface(FontCache.getTypeface(this, FontCache.PixelOperatorMono8));
        tvRepo.setTypeface(FontCache.getTypeface(this, FontCache.PixelOperatorMono8));
    }
}