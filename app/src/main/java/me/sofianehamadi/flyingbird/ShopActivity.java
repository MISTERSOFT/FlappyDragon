package me.sofianehamadi.flyingbird;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import me.sofianehamadi.flyingbird.common.FontCache;
import me.sofianehamadi.flyingbird.database.Database;
import me.sofianehamadi.flyingbird.models.Bird;
import me.sofianehamadi.flyingbird.models.User;
import me.sofianehamadi.flyingbird.models.adapters.ListViewItemBirdAdapter;

public class ShopActivity extends AppCompatActivity {

    private static TextView tvTitle;
    private static TextView tvCoins;

    public User user;
    private static ArrayList<Bird> birds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);


        tvTitle = (TextView) findViewById(R.id.textViewShopTitle);
        tvTitle.setTypeface(FontCache.getTypeface(this, FontCache.PixelOperatorMono8));

        tvCoins = (TextView) findViewById(R.id.shop_coins_earn);
        tvCoins.setTypeface(FontCache.getTypeface(this, FontCache.PixelOperatorMono8));
        updateMoney();

        /**
         * Init listView
         */
        birds = Database.getInstance(this).getAllBirds();
        ListView listViewBirds = (ListView) findViewById(R.id.listViewShop);
        ListViewItemBirdAdapter birdAdapter = new ListViewItemBirdAdapter(this, R.layout.listview_item_bird, birds);
        listViewBirds.setAdapter(birdAdapter);
    }

    public void updateMoney() {
        user = Database.getInstance(this).getUser();
        tvCoins.setText(user.getMoney().toString());
    }
}
