package me.sofianehamadi.flyingbird.gameobject;

import android.content.Context;
import android.graphics.Bitmap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import me.sofianehamadi.flyingbird.R;
import me.sofianehamadi.flyingbird.common.Util;
import me.sofianehamadi.flyingbird.models.BirdTypeEnum;

/**
 * Created by MISTERSOFT on 27/02/2017.
 */

public class BirdFactory {

    private static HashMap<BirdTypeEnum, List<Bitmap>> birds;

    public static Collection<Bitmap> getBirdSprite(Context context, BirdTypeEnum type) {
        if (birds == null) {
            birds = new HashMap<>();
            /**
             * Angry bird
             */
            birds.put(BirdTypeEnum.ANGRY, Arrays.asList(
                Util.getScaledBitmapAlpha8(context, R.drawable.bird_angry_idle_1),
                Util.getScaledBitmapAlpha8(context, R.drawable.bird_angry_idle_2),
                Util.getScaledBitmapAlpha8(context, R.drawable.bird_angry_idle_3),
                Util.getScaledBitmapAlpha8(context, R.drawable.bird_angry_idle_4)
            ));

            /**
             * Brown bird
             */
            birds.put(BirdTypeEnum.BROWN, Arrays.asList(
                Util.getScaledBitmapAlpha8(context, R.drawable.bird_brown_idle_1),
                Util.getScaledBitmapAlpha8(context, R.drawable.bird_brown_idle_2),
                Util.getScaledBitmapAlpha8(context, R.drawable.bird_brown_idle_3),
                Util.getScaledBitmapAlpha8(context, R.drawable.bird_brown_idle_4),
                Util.getScaledBitmapAlpha8(context, R.drawable.bird_brown_idle_5),
                Util.getScaledBitmapAlpha8(context, R.drawable.bird_brown_idle_6),
                Util.getScaledBitmapAlpha8(context, R.drawable.bird_brown_idle_7),
                Util.getScaledBitmapAlpha8(context, R.drawable.bird_brown_idle_8)
            ));

            /**
             * Brush Cutter bird
             * NOTE: Have hit sprite
             */
            birds.put(BirdTypeEnum.BURSH_CUTTER, Arrays.asList(
                Util.getScaledBitmapAlpha8(context, R.drawable.bird_brush_cutter_idle_1),
                Util.getScaledBitmapAlpha8(context, R.drawable.bird_brush_cutter_idle_2)
            ));

            /**
             * Chicken bird
             * NOTE: Have hit sprite
             */
            birds.put(BirdTypeEnum.CHICKEN, Arrays.asList(
                Util.getScaledBitmapAlpha8(context, R.drawable.bird_chicken_idle_1),
                Util.getScaledBitmapAlpha8(context, R.drawable.bird_chicken_idle_2),
                Util.getScaledBitmapAlpha8(context, R.drawable.bird_chicken_idle_3),
                Util.getScaledBitmapAlpha8(context, R.drawable.bird_chicken_idle_4)
            ));

            /**
             * Duck bird
             * NOTE: Have hit sprite
             */
            birds.put(BirdTypeEnum.DUCK, Arrays.asList(
                Util.getScaledBitmapAlpha8(context, R.drawable.bird_duck_idle_1),
                Util.getScaledBitmapAlpha8(context, R.drawable.bird_duck_idle_2),
                Util.getScaledBitmapAlpha8(context, R.drawable.bird_duck_idle_3),
                Util.getScaledBitmapAlpha8(context, R.drawable.bird_duck_idle_4)
            ));

            /**
             * Grumpy bird
             */
            birds.put(BirdTypeEnum.GRUMPY, Arrays.asList(
                Util.getScaledBitmapAlpha8(context, R.drawable.bird_grumpy_idle_1),
                Util.getScaledBitmapAlpha8(context, R.drawable.bird_grumpy_idle_2),
                Util.getScaledBitmapAlpha8(context, R.drawable.bird_grumpy_idle_3),
                Util.getScaledBitmapAlpha8(context, R.drawable.bird_grumpy_idle_4)
            ));

            /**
             * Monster bird
             * NOTE: Have hit sprite
             */
            birds.put(BirdTypeEnum.MONSTER, Arrays.asList(
                Util.getScaledBitmapAlpha8(context, R.drawable.bird_monster_idle_1),
                Util.getScaledBitmapAlpha8(context, R.drawable.bird_monster_idle_2),
                Util.getScaledBitmapAlpha8(context, R.drawable.bird_monster_idle_3),
                Util.getScaledBitmapAlpha8(context, R.drawable.bird_monster_idle_4)
            ));

            /**
             * Pink bird
             * NOTE: Have hit sprite
             */
            birds.put(BirdTypeEnum.PINK, Arrays.asList(
                Util.getScaledBitmapAlpha8(context, R.drawable.bird_pink_idle_1),
                Util.getScaledBitmapAlpha8(context, R.drawable.bird_pink_idle_2)
            ));

            /**
             * Red bird
             * NOTE: Have hit sprite
             */
            birds.put(BirdTypeEnum.RED, Arrays.asList(
                Util.getScaledBitmapAlpha8(context, R.drawable.bird_red_idle_1),
                Util.getScaledBitmapAlpha8(context, R.drawable.bird_red_idle_2)
            ));

            /**
             * Rock bird
             */
            birds.put(BirdTypeEnum.ROCK, Arrays.asList(
                Util.getScaledBitmapAlpha8(context, R.drawable.bird_rock_idle_1),
                Util.getScaledBitmapAlpha8(context, R.drawable.bird_rock_idle_2)
            ));

            /**
             * Stupid bird
             * NOTE: Have hit sprite
             */
            birds.put(BirdTypeEnum.STUPID, Arrays.asList(
                Util.getScaledBitmapAlpha8(context, R.drawable.bird_stupid_idle_1),
                Util.getScaledBitmapAlpha8(context, R.drawable.bird_stupid_idle_2),
                Util.getScaledBitmapAlpha8(context, R.drawable.bird_stupid_idle_3),
                Util.getScaledBitmapAlpha8(context, R.drawable.bird_stupid_idle_4),
                Util.getScaledBitmapAlpha8(context, R.drawable.bird_stupid_idle_5),
                Util.getScaledBitmapAlpha8(context, R.drawable.bird_stupid_idle_6),
                Util.getScaledBitmapAlpha8(context, R.drawable.bird_stupid_idle_7),
                Util.getScaledBitmapAlpha8(context, R.drawable.bird_stupid_idle_8)
            ));

            /**
             * Tiny bird
             * NOTE: Have hit sprite
             */
            birds.put(BirdTypeEnum.TINY, Arrays.asList(
                Util.getScaledBitmapAlpha8(context, R.drawable.bird_tiny_idle_1),
                Util.getScaledBitmapAlpha8(context, R.drawable.bird_tiny_idle_2)
            ));

            /**
             * Weird bird
             */
            birds.put(BirdTypeEnum.WEIRD, Arrays.asList(
                Util.getScaledBitmapAlpha8(context, R.drawable.bird_weird_idle_1),
                Util.getScaledBitmapAlpha8(context, R.drawable.bird_weird_idle_2),
                Util.getScaledBitmapAlpha8(context, R.drawable.bird_weird_idle_3),
                Util.getScaledBitmapAlpha8(context, R.drawable.bird_weird_idle_4)
            ));

            /**
             * Yellow bird
             */
            birds.put(BirdTypeEnum.YELLOW, Arrays.asList(
                Util.getScaledBitmapAlpha8(context, R.drawable.bird_yellow_idle_1),
                Util.getScaledBitmapAlpha8(context, R.drawable.bird_yellow_idle_2)
            ));
        }

        return birds.get(type);
    }

    public static Bitmap getFirst(BirdTypeEnum type) {
        return birds.get(type).get(0);
    }

}
