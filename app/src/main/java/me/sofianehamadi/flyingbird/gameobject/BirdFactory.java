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

    private static BirdFactory instance;
    private static HashMap<BirdTypeEnum, List<Integer>> birds;

    private BirdFactory(Context context) {
        birds = new HashMap<>();
        /**
         * Angry bird
         */
//                Util.getScaledBitmapAlpha8(context, R.drawable.bird_angry_idle_1),
//                Util.getScaledBitmapAlpha8(context, R.drawable.bird_angry_idle_2),
//                Util.getScaledBitmapAlpha8(context, R.drawable.bird_angry_idle_3),
//                Util.getScaledBitmapAlpha8(context, R.drawable.bird_angry_idle_4)
        birds.put(BirdTypeEnum.ANGRY, Arrays.asList(
                R.drawable.bird_angry_idle_1,
                R.drawable.bird_angry_idle_2,
                R.drawable.bird_angry_idle_3,
                R.drawable.bird_angry_idle_4
        ));

        /**
         * Brown bird
         */
//        Util.getScaledBitmapAlpha8(context, R.drawable.bird_brown_idle_1),
//                Util.getScaledBitmapAlpha8(context, R.drawable.bird_brown_idle_2),
//                Util.getScaledBitmapAlpha8(context, R.drawable.bird_brown_idle_3),
//                Util.getScaledBitmapAlpha8(context, R.drawable.bird_brown_idle_4),
//                Util.getScaledBitmapAlpha8(context, R.drawable.bird_brown_idle_5),
//                Util.getScaledBitmapAlpha8(context, R.drawable.bird_brown_idle_6),
//                Util.getScaledBitmapAlpha8(context, R.drawable.bird_brown_idle_7),
//                Util.getScaledBitmapAlpha8(context, R.drawable.bird_brown_idle_8)
        birds.put(BirdTypeEnum.BROWN, Arrays.asList(
                R.drawable.bird_brown_idle_1,
                R.drawable.bird_brown_idle_2,
                R.drawable.bird_brown_idle_3,
                R.drawable.bird_brown_idle_4,
                R.drawable.bird_brown_idle_5,
                R.drawable.bird_brown_idle_6,
                R.drawable.bird_brown_idle_7,
                R.drawable.bird_brown_idle_8
        ));

        /**
         * Brush Cutter bird
         * NOTE: Have hit sprite
         */
//        Util.getScaledBitmapAlpha8(context, R.drawable.bird_brush_cutter_idle_1),
//                Util.getScaledBitmapAlpha8(context, R.drawable.bird_brush_cutter_idle_2)
        birds.put(BirdTypeEnum.BURSH_CUTTER, Arrays.asList(
                R.drawable.bird_brush_cutter_idle_1,
                R.drawable.bird_brush_cutter_idle_2
        ));

        /**
         * Chicken bird
         * NOTE: Have hit sprite
         */
//        Util.getScaledBitmapAlpha8(context, R.drawable.bird_chicken_idle_1),
//                Util.getScaledBitmapAlpha8(context, R.drawable.bird_chicken_idle_2),
//                Util.getScaledBitmapAlpha8(context, R.drawable.bird_chicken_idle_3),
//                Util.getScaledBitmapAlpha8(context, R.drawable.bird_chicken_idle_4)
        birds.put(BirdTypeEnum.CHICKEN, Arrays.asList(
                R.drawable.bird_chicken_idle_1,
                R.drawable.bird_chicken_idle_2,
                R.drawable.bird_chicken_idle_3,
                R.drawable.bird_chicken_idle_4
        ));

        /**
         * Duck bird
         * NOTE: Have hit sprite
         */
//        Util.getScaledBitmapAlpha8(context, R.drawable.bird_duck_idle_1),
//                Util.getScaledBitmapAlpha8(context, R.drawable.bird_duck_idle_2),
//                Util.getScaledBitmapAlpha8(context, R.drawable.bird_duck_idle_3),
//                Util.getScaledBitmapAlpha8(context, R.drawable.bird_duck_idle_4)
        birds.put(BirdTypeEnum.DUCK, Arrays.asList(
                R.drawable.bird_duck_idle_1,
                R.drawable.bird_duck_idle_2,
                R.drawable.bird_duck_idle_3,
                R.drawable.bird_duck_idle_4
        ));

        /**
         * Grumpy bird
         */
//        Util.getScaledBitmapAlpha8(context, R.drawable.bird_grumpy_idle_1),
//                Util.getScaledBitmapAlpha8(context, R.drawable.bird_grumpy_idle_2),
//                Util.getScaledBitmapAlpha8(context, R.drawable.bird_grumpy_idle_3),
//                Util.getScaledBitmapAlpha8(context, R.drawable.bird_grumpy_idle_4)
        birds.put(BirdTypeEnum.GRUMPY, Arrays.asList(
                R.drawable.bird_grumpy_idle_1,
                R.drawable.bird_grumpy_idle_2,
                R.drawable.bird_grumpy_idle_3,
                R.drawable.bird_grumpy_idle_4
        ));

        /**
         * Monster bird
         * NOTE: Have hit sprite
         */
//        Util.getScaledBitmapAlpha8(context, R.drawable.bird_monster_idle_1),
//                Util.getScaledBitmapAlpha8(context, R.drawable.bird_monster_idle_2),
//                Util.getScaledBitmapAlpha8(context, R.drawable.bird_monster_idle_3),
//                Util.getScaledBitmapAlpha8(context, R.drawable.bird_monster_idle_4)
        birds.put(BirdTypeEnum.MONSTER, Arrays.asList(
                R.drawable.bird_monster_idle_1,
                R.drawable.bird_monster_idle_2,
                R.drawable.bird_monster_idle_3,
                R.drawable.bird_monster_idle_4
        ));

        /**
         * Pink bird
         * NOTE: Have hit sprite
         */
//        Util.getScaledBitmapAlpha8(context, R.drawable.bird_pink_idle_1),
//                Util.getScaledBitmapAlpha8(context, R.drawable.bird_pink_idle_2)
        birds.put(BirdTypeEnum.PINK, Arrays.asList(
                R.drawable.bird_pink_idle_1,
                R.drawable.bird_pink_idle_2
        ));

        /**
         * Red bird
         * NOTE: Have hit sprite
         */
//        Util.getScaledBitmapAlpha8(context, R.drawable.bird_red_idle_1),
//                Util.getScaledBitmapAlpha8(context, R.drawable.bird_red_idle_2)
        birds.put(BirdTypeEnum.RED, Arrays.asList(
                R.drawable.bird_red_idle_1,
                R.drawable.bird_red_idle_2
        ));

        /**
         * Rock bird
         */
//        Util.getScaledBitmapAlpha8(context, R.drawable.bird_rock_idle_1),
//                Util.getScaledBitmapAlpha8(context, R.drawable.bird_rock_idle_2)
        birds.put(BirdTypeEnum.ROCK, Arrays.asList(
                R.drawable.bird_rock_idle_1,
                R.drawable.bird_rock_idle_2
        ));

        /**
         * Stupid bird
         * NOTE: Have hit sprite
         */
//        Util.getScaledBitmapAlpha8(context, R.drawable.bird_stupid_idle_1),
//                Util.getScaledBitmapAlpha8(context, R.drawable.bird_stupid_idle_2),
//                Util.getScaledBitmapAlpha8(context, R.drawable.bird_stupid_idle_3),
//                Util.getScaledBitmapAlpha8(context, R.drawable.bird_stupid_idle_4),
//                Util.getScaledBitmapAlpha8(context, R.drawable.bird_stupid_idle_5),
//                Util.getScaledBitmapAlpha8(context, R.drawable.bird_stupid_idle_6),
//                Util.getScaledBitmapAlpha8(context, R.drawable.bird_stupid_idle_7),
//                Util.getScaledBitmapAlpha8(context, R.drawable.bird_stupid_idle_8)
        birds.put(BirdTypeEnum.STUPID, Arrays.asList(
                R.drawable.bird_stupid_idle_1,
                R.drawable.bird_stupid_idle_2,
                R.drawable.bird_stupid_idle_3,
                R.drawable.bird_stupid_idle_4,
                R.drawable.bird_stupid_idle_5,
                R.drawable.bird_stupid_idle_6,
                R.drawable.bird_stupid_idle_7,
                R.drawable.bird_stupid_idle_8
        ));

        /**
         * Tiny bird
         * NOTE: Have hit sprite
         */
//        Util.getScaledBitmapAlpha8(context, R.drawable.bird_tiny_idle_1),
//                Util.getScaledBitmapAlpha8(context, R.drawable.bird_tiny_idle_2)
        birds.put(BirdTypeEnum.TINY, Arrays.asList(
                R.drawable.bird_tiny_idle_1,
                R.drawable.bird_tiny_idle_2
        ));

        /**
         * Weird bird
         */
//        Util.getScaledBitmapAlpha8(context, R.drawable.bird_weird_idle_1),
//                Util.getScaledBitmapAlpha8(context, R.drawable.bird_weird_idle_2),
//                Util.getScaledBitmapAlpha8(context, R.drawable.bird_weird_idle_3),
//                Util.getScaledBitmapAlpha8(context, R.drawable.bird_weird_idle_4)
        birds.put(BirdTypeEnum.WEIRD, Arrays.asList(
                R.drawable.bird_weird_idle_1,
                R.drawable.bird_weird_idle_2,
                R.drawable.bird_weird_idle_3,
                R.drawable.bird_weird_idle_4
        ));

        /**
         * Yellow bird
         */
//        Util.getScaledBitmapAlpha8(context, R.drawable.bird_yellow_idle_1),
//                Util.getScaledBitmapAlpha8(context, R.drawable.bird_yellow_idle_2)
        birds.put(BirdTypeEnum.YELLOW, Arrays.asList(
                R.drawable.bird_yellow_idle_1,
                R.drawable.bird_yellow_idle_2
        ));
    }

    public static BirdFactory getInstance(Context context) {
        if (instance == null) {
            instance = new BirdFactory(context);
        }
        return instance;
    }

    public Collection<Integer> getBirdSprites(BirdTypeEnum type) {
        return birds.get(type);
    }

    public Integer getFirst(BirdTypeEnum type) {
        return birds.get(type).get(0);
    }

}
