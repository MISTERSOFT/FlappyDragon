package me.sofianehamadi.flyingbird.common;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

/**
 * Contain tools
 */
public class Util {
    private static final int DEFAULT_DENSITY = 1024;

    /**
     * Get BitmapFactory options
     * @param context Context
     * @return BitmapFactory.Options
     */
    private static BitmapFactory.Options getBitmapFactoryOptions(Context context) {
        BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
        bitmapOptions.inPreferredConfig = Bitmap.Config.ALPHA_8;
        bitmapOptions.inScaled = true;
        bitmapOptions.inDensity = DEFAULT_DENSITY;
        bitmapOptions.inTargetDensity = (int)(getScaleFactor(context) * DEFAULT_DENSITY);
        return bitmapOptions;
    }

    /**
     * Scale bitmap in Alpha 8
     * @param context Context
     * @param bitmapId Resource Id of the bitmap
     * @return Bitmap
     */
    public static Bitmap getScaledBitmapAlpha8(Context context, int bitmapId) {
        Bitmap b = BitmapFactory.decodeResource(context.getResources(), bitmapId, getBitmapFactoryOptions(context));
        b.setDensity(context.getResources().getDisplayMetrics().densityDpi);
        return b;
    }

    /**
     * Scale bitmap in Alpha 8
     * @param context Context
     * @param bitmapId Resource Id of the bitmap
     * @param height Height of the new bitmap
     * @param width Width of the new bitmap
     * @return Bitmap
     */
    public static Bitmap getScaledBitmapAlpha8(Context context, int bitmapId, int height, int width) {
        Bitmap b = BitmapFactory.decodeResource(context.getResources(), bitmapId, getBitmapFactoryOptions(context));
        b = Bitmap.createScaledBitmap(b, width, height, false);
        b.setDensity(context.getResources().getDisplayMetrics().densityDpi);
        return b;
    }

    /**
     * Scale bitmap depending of view size
     * @param context Context
     * @param bitmapId Resource Id of the bitmap
     * @param viewHeight View height
     * @param viewWidth View width
     * @param scale Scale ratio
     * @return Bitmap
     */
    public static Bitmap getAutoScaledBitmapAlpha8(Context context, int bitmapId, int viewHeight, int viewWidth, int scale) {
        Bitmap b = BitmapFactory.decodeResource(context.getResources(), bitmapId, getBitmapFactoryOptions(context));
        int newWidth = viewWidth / scale;
        int newHeight = (viewHeight / viewWidth) * newWidth;
        b = Bitmap.createScaledBitmap(b, newWidth, newHeight, false);
        b.setDensity(context.getResources().getDisplayMetrics().densityDpi);
        return b;
    }

    /**
     * Get scale factor
     * @param context Context
     * @return float
     */
    public static float getScaleFactor(Context context){
        return context.getResources().getDisplayMetrics().heightPixels / 1066f;
    }
}