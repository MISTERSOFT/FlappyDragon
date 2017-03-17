package me.sofianehamadi.flyingbird.common;

import android.content.Context;
import android.graphics.Typeface;

import java.util.HashMap;

/**
 * Created by MISTERSOFT on 25/02/2017.
 */

/**
 * Store fonts in memory
 */
public class FontCache {

    /**
     * Font size
     */
    public static final Integer TINY = 8;
    public static final Integer SMALL = 10;

    /**
     * Fonts available
     */
    public static final String PixelOperatorMono8 = "PixelOperatorMono8.ttf";

    /**
     * Cache
     */
    private static HashMap<String, Typeface> cache = new HashMap<>();

    /**
     * Get a font
     * @param context Context
     * @param fontName Name of the font
     * @return Typeface
     */
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
