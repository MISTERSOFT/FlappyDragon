package me.sofianehamadi.flyingbird.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import me.sofianehamadi.flyingbird.models.Bird;
import me.sofianehamadi.flyingbird.models.BirdTypeEnum;
import me.sofianehamadi.flyingbird.models.User;

/**
 * Created by MISTERSOFT on 26/02/2017.
 */

public class Database extends SQLiteOpenHelper {

    private static Database instance;

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "db_flyingbird";

    private static final String TABLE_BIRD = "Bird";
    private static final String TABLE_USER = "User";

    public static Database getInstance(Context context) {
        if (instance == null) {
            instance = new Database(context.getApplicationContext());
        }
        return instance;
    }

    private Database(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        context.deleteDatabase(DATABASE_NAME);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Bird table
        String createBirdTable = "CREATE TABLE " + TABLE_BIRD + " ( " +
                                 "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                                 "name TEXT, " +
                                 "price INTEGER, " +
                                 "type TEXT, " +
                                 "resourceName TEXT, " +
                                 "isBought INTEGER, " +
                                 "isEquiped INTEGER )";
        // User table
        String createUserTable = "CREATE TABLE " + TABLE_USER + " (id INTEGER PRIMARY KEY AUTOINCREMENT, money INTEGER)";

        // Create Bird table
        db.execSQL(createBirdTable);
        db.execSQL(createUserTable);

        // Feed bird table
        db.execSQL("INSERT INTO " + TABLE_BIRD + " (id, name, price, type, resourceName, isBought, isEquiped) VALUES(null, 'Joe Angry',    500, '" + BirdTypeEnum.ANGRY       .name() + "', 'bird_angry_idle_1',        0, 0)");
        db.execSQL("INSERT INTO " + TABLE_BIRD + " (id, name, price, type, resourceName, isBought, isEquiped) VALUES(null, 'Mac Brown',    250, '" + BirdTypeEnum.BROWN       .name() + "', 'bird_brown_idle_1',        0, 0)");
        db.execSQL("INSERT INTO " + TABLE_BIRD + " (id, name, price, type, resourceName, isBought, isEquiped) VALUES(null, 'Brushy',       100, '" + BirdTypeEnum.BURSH_CUTTER.name() + "', 'bird_brush_cutter_idle_1', 0, 0)");
        db.execSQL("INSERT INTO " + TABLE_BIRD + " (id, name, price, type, resourceName, isBought, isEquiped) VALUES(null, 'Fry Chicky',   500, '" + BirdTypeEnum.CHICKEN     .name() + "', 'bird_chicken_idle_1',      0, 0)");
        db.execSQL("INSERT INTO " + TABLE_BIRD + " (id, name, price, type, resourceName, isBought, isEquiped) VALUES(null, 'Ducky',        100, '" + BirdTypeEnum.DUCK        .name() + "', 'bird_duck_idle_1',         0, 0)");
        db.execSQL("INSERT INTO " + TABLE_BIRD + " (id, name, price, type, resourceName, isBought, isEquiped) VALUES(null, 'Grumpy Bird',  350, '" + BirdTypeEnum.GRUMPY      .name() + "', 'bird_grumpy_idle_1',       0, 0)");
        db.execSQL("INSERT INTO " + TABLE_BIRD + " (id, name, price, type, resourceName, isBought, isEquiped) VALUES(null, 'Monster Bird', 400, '" + BirdTypeEnum.MONSTER     .name() + "', 'bird_monster_idle_1',      0, 0)");
        db.execSQL("INSERT INTO " + TABLE_BIRD + " (id, name, price, type, resourceName, isBought, isEquiped) VALUES(null, 'Pinku',        200, '" + BirdTypeEnum.PINK        .name() + "', 'bird_pink_idle_1',         0, 0)");
        db.execSQL("INSERT INTO " + TABLE_BIRD + " (id, name, price, type, resourceName, isBought, isEquiped) VALUES(null, 'Red Fly',      500, '" + BirdTypeEnum.RED         .name() + "', 'bird_red_idle_1',          0, 0)");
        db.execSQL("INSERT INTO " + TABLE_BIRD + " (id, name, price, type, resourceName, isBought, isEquiped) VALUES(null, 'Rocky',        125, '" + BirdTypeEnum.ROCK        .name() + "', 'bird_rock_idle_1',         0, 0)");
        db.execSQL("INSERT INTO " + TABLE_BIRD + " (id, name, price, type, resourceName, isBought, isEquiped) VALUES(null, 'Baka',         300, '" + BirdTypeEnum.STUPID      .name() + "', 'bird_stupid_idle_1',       0, 0)");
        db.execSQL("INSERT INTO " + TABLE_BIRD + " (id, name, price, type, resourceName, isBought, isEquiped) VALUES(null, 'Tiny',         100, '" + BirdTypeEnum.TINY        .name() + "', 'bird_tiny_idle_1',         0, 0)");
        db.execSQL("INSERT INTO " + TABLE_BIRD + " (id, name, price, type, resourceName, isBought, isEquiped) VALUES(null, 'Whaly',        200, '" + BirdTypeEnum.WEIRD       .name() + "', 'bird_weird_idle_1',        0, 0)");
        db.execSQL("INSERT INTO " + TABLE_BIRD + " (id, name, price, type, resourceName, isBought, isEquiped) VALUES(null, 'Yellow Boy',   200, '" + BirdTypeEnum.YELLOW      .name() + "', 'bird_yellow_idle_1',       0, 0)");

        // Feed user table
        db.execSQL("INSERT INTO " + TABLE_USER + " VALUES(1, 0)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        db.delete(TABLE_USER, null, null);
//        db.delete(TABLE_BIRD, null, null);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_BIRD);
//        onCreate(db);
    }

    public User getUser() {
        User user = new User();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_USER + " WHERE id = 1", null);
        cursor.moveToFirst();

        user.setId(cursor.getInt(0));
        user.setMoney(cursor.getInt(1));

        cursor.close();
        db.close();

        return user;
    }

    public void updateUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("money", user.getMoney());

        db.update(TABLE_USER, values, "id = " + user.getId(), null);

        db.close();
    }

    public ArrayList<Bird> getAllBirds() {
        ArrayList<Bird> birds = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_BIRD, null);

        while(cursor.moveToNext()) {
            Bird bird = new Bird();
            bird.setId(cursor.getInt(cursor.getColumnIndex("id")));
            bird.setName(cursor.getString(cursor.getColumnIndex("name")));
            bird.setPrice(cursor.getInt(cursor.getColumnIndex("price")));
            bird.setBirdType(BirdTypeEnum.valueOf(cursor.getString(cursor.getColumnIndex("type"))));
            bird.setResourceName(cursor.getString(cursor.getColumnIndex("resourceName")));
            bird.setBought(this.integerToBoolean(cursor.getInt(cursor.getColumnIndex("isBought"))));
            bird.setEquiped(this.integerToBoolean(cursor.getInt(cursor.getColumnIndex("isEquiped"))));

            birds.add(bird);
        }

        cursor.close();
        db.close();

        return birds;
    }

    public Bird getEquipedBird() {
        Bird bird = new Bird();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_BIRD + " WHERE isEquiped = 1", null);
        cursor.moveToFirst();

        bird.setId(cursor.getInt(0));
        bird.setBirdType(BirdTypeEnum.valueOf(cursor.getString(cursor.getColumnIndex("type"))));

        cursor.close();
        db.close();

        return bird;
    }

    private boolean integerToBoolean(int value) {
        return value != 0;
    }

}
