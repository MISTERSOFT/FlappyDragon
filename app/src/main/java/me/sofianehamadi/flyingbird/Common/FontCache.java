package me.sofianehamadi.flyingbird.common;

import android.content.Context;
import android.graphics.Typeface;

import java.util.HashMap;

/**
 * Created by MISTERSOFT on 25/02/2017.
 */

public class FontCache {

    /**
     * Fonts
     */
    public static final String PixelOperatorMono8 = "PixelOperatorMono8.ttf";

    /**
     * Cache
     */
    private static HashMap<String, Typeface> cache = new HashMap<>();

    public static Typeface getTypeface(Context context, String fontName) {
        Typeface font = cache.get(fontName);
        if (font == null) {
            try {
                font = Typeface.createFromAsset(context.getAssets(), "fonts/" + fontName);
                cache.put(fontName, font);
            } catch (RuntimeException e) {
                font = Typeface.DEFAULT;
            }
        }
        return font;
    }

}
