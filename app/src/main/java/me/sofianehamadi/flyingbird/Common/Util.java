package me.sofianehamadi.flyingbird.common;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class Util {
    private static final int DEFAULT_DENSITY = 1024;

    private static BitmapFactory.Options getBitmapFactoryOptions(Context context) {
        BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
        bitmapOptions.inPreferredConfig = Bitmap.Config.ALPHA_8;
        bitmapOptions.inScaled = true;
        bitmapOptions.inDensity = DEFAULT_DENSITY;
        bitmapOptions.inTargetDensity = (int)(getScaleFactor(context) * DEFAULT_DENSITY);
        return bitmapOptions;
    }

    public static Bitmap getScaledBitmapAlpha8(Context context, int bitmapId) {
//        BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
//        bitmapOptions.inPreferredConfig = Bitmap.Config.ALPHA_8;
//        bitmapOptions.inScaled = true;
//        bitmapOptions.inDensity = DEFAULT_DENSITY;
//        bitmapOptions.inTargetDensity = (int)(getScaleFactor(context) * DEFAULT_DENSITY);
        Bitmap b = BitmapFactory.decodeResource(context.getResources(), bitmapId, getBitmapFactoryOptions(context));
        b.setDensity(context.getResources().getDisplayMetrics().densityDpi);
        return b;
    }

    public static Bitmap getScaledBitmapAlpha8(Context context, int bitmapId, int height, int width) {
//        BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
//        bitmapOptions.inPreferredConfig = Bitmap.Config.ALPHA_8;
//        bitmapOptions.inScaled = true;
//        bitmapOptions.inDensity = DEFAULT_DENSITY;
//        bitmapOptions.inTargetDensity = (int)(getScaleFactor(context) * DEFAULT_DENSITY);
        Bitmap b = BitmapFactory.decodeResource(context.getResources(), bitmapId, getBitmapFactoryOptions(context));
        b = Bitmap.createScaledBitmap(b, width, height, false);
        b.setDensity(context.getResources().getDisplayMetrics().densityDpi);
        return b;
    }

    public static float getScaleFactor(Context context){
        return context.getResources().getDisplayMetrics().heightPixels / 1066f;
    }
}
