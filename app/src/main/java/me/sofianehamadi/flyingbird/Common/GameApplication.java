package me.sofianehamadi.flyingbird.common;

import android.app.Application;
import android.content.Context;

/**
 * Created by MISTERSOFT on 13/03/2017.
 */

public class GameApplication extends Application {
    private static GameApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    public static GameApplication getInstance() {
        return instance;
    }
}
