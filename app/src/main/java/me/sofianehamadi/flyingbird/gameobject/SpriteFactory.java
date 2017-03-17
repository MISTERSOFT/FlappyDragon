package me.sofianehamadi.flyingbird.gameobject;

import android.content.Context;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import me.sofianehamadi.flyingbird.R;
import me.sofianehamadi.flyingbird.models.BirdTypeEnum;

/**
 * Created by MISTERSOFT on 27/02/2017.
 */

/**
 * Contain all resource ids of each sprites
 */
public class SpriteFactory {
    /**
     * SpriteFactory instance
     */
    private static SpriteFactory instance;
    /**
     * List of birds bind to its own sprites
     */
    private static HashMap<BirdTypeEnum, List<Integer>> birds;
    /**
     * List of enemy sprites
     */
    private static Collection<Integer> enemies;
    /**
     * List of coin sprites
     */
    private static Collection<Integer> coins;

    /**
     * Constructor
     * @param context Context
     */
    private SpriteFactory(Context context) {
        /************************************
         * Birds
         */
        birds = new HashMap<>();
        /**
         * Angry bird
         */
        birds.put(BirdTypeEnum.ANGRY, Arrays.asList(
            R.drawable.bird_angry_idle_1,
            R.drawable.bird_angry_idle_2,
            R.drawable.bird_angry_idle_3,
            R.drawable.bird_angry_idle_4
        ));

        /**
         * Brown bird
         */
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
         * NOTE: Have hit sprite (not used)
         */
        birds.put(BirdTypeEnum.BURSH_CUTTER, Arrays.asList(
            R.drawable.bird_brush_cutter_idle_1,
            R.drawable.bird_brush_cutter_idle_2
        ));

        /**
         * Chicken bird
         * NOTE: Have hit sprite (not used)
         */
        birds.put(BirdTypeEnum.CHICKEN, Arrays.asList(
            R.drawable.bird_chicken_idle_1,
            R.drawable.bird_chicken_idle_2,
            R.drawable.bird_chicken_idle_3,
            R.drawable.bird_chicken_idle_4
        ));

        /**
         * Duck bird
         * NOTE: Have hit sprite (not used)
         */
        birds.put(BirdTypeEnum.DUCK, Arrays.asList(
            R.drawable.bird_duck_idle_1,
            R.drawable.bird_duck_idle_2,
            R.drawable.bird_duck_idle_3,
            R.drawable.bird_duck_idle_4
        ));

        /**
         * Grumpy bird
         */
        birds.put(BirdTypeEnum.GRUMPY, Arrays.asList(
            R.drawable.bird_grumpy_idle_1,
            R.drawable.bird_grumpy_idle_2,
            R.drawable.bird_grumpy_idle_3,
            R.drawable.bird_grumpy_idle_4
        ));

        /**
         * Monster bird
         * NOTE: Have hit sprite (not used)
         */
        birds.put(BirdTypeEnum.MONSTER, Arrays.asList(
            R.drawable.bird_monster_idle_1,
            R.drawable.bird_monster_idle_2,
            R.drawable.bird_monster_idle_3,
            R.drawable.bird_monster_idle_4
        ));

        /**
         * Pink bird
         * NOTE: Have hit sprite (not used)
         */
        birds.put(BirdTypeEnum.PINK, Arrays.asList(
            R.drawable.bird_pink_idle_1,
            R.drawable.bird_pink_idle_2
        ));

        /**
         * Red bird
         * NOTE: Have hit sprite (not used)
         */
        birds.put(BirdTypeEnum.RED, Arrays.asList(
            R.drawable.bird_red_idle_1,
            R.drawable.bird_red_idle_2
        ));

        /**
         * Rock bird
         */
        birds.put(BirdTypeEnum.ROCK, Arrays.asList(
            R.drawable.bird_rock_idle_1,
            R.drawable.bird_rock_idle_2
        ));

        /**
         * Stupid bird
         * NOTE: Have hit sprite (not used)
         */
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
         * NOTE: Have hit sprite (not used)
         */
        birds.put(BirdTypeEnum.TINY, Arrays.asList(
            R.drawable.bird_tiny_idle_1,
            R.drawable.bird_tiny_idle_2
        ));

        /**
         * Weird bird
         */
        birds.put(BirdTypeEnum.WEIRD, Arrays.asList(
            R.drawable.bird_weird_idle_1,
            R.drawable.bird_weird_idle_2,
            R.drawable.bird_weird_idle_3,
            R.drawable.bird_weird_idle_4
        ));

        /**
         * Yellow bird
         */
        birds.put(BirdTypeEnum.YELLOW, Arrays.asList(
            R.drawable.bird_yellow_idle_1,
            R.drawable.bird_yellow_idle_2
        ));

        /****************************
         * ENEMIES
         */
        enemies = Arrays.asList(
            R.drawable.enemy_idle_1,
            R.drawable.enemy_idle_2,
            R.drawable.enemy_idle_3,
            R.drawable.enemy_idle_4,
            R.drawable.enemy_idle_5,
            R.drawable.enemy_idle_6
        );

        /*****************************
         * Coins
         */
        coins = Arrays.asList(
            R.drawable.coin1,
            R.drawable.coin2,
            R.drawable.coin3,
            R.drawable.coin4,
            R.drawable.coin5,
            R.drawable.coin6,
            R.drawable.coin7,
            R.drawable.coin8
        );
    }

    /**
     * Get SpriteFactory instance
     * @param context Context
     * @return SpriteFactory
     */
    public static SpriteFactory getInstance(Context context) {
        if (instance == null) {
            instance = new SpriteFactory(context);
        }
        return instance;
    }

    /**
     * Get all sprite of a bird
     * @param type Type of bird
     * @return List of resource id
     */
    public Collection<Integer> getBirdSprites(BirdTypeEnum type) {
        return birds.get(type);
    }

    /**
     * Get all sprite of enemy
     * @return List of resource id
     */
    public Collection<Integer> getEnemySprites() {
        return enemies;
    }

    /**
     * Get all sprite of coin
     * @return List of resource id
     */
    public Collection<Integer> getCoinsSprites() {
        return coins;
    }

}
