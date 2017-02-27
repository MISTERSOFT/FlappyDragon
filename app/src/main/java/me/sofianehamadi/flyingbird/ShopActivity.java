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

    private static User user;
    private static ArrayList<Bird> birds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);

        user = Database.getInstance(this).getUser();
        birds = Database.getInstance(this).getAllBirds();

        TextView tvTitle = (TextView) findViewById(R.id.textViewShopTitle);
        tvTitle.setTypeface(FontCache.getTypeface(this, FontCache.PixelOperatorMono8));

        TextView tvCoins = (TextView) findViewById(R.id.shop_coins_earn);
        tvCoins.setTypeface(FontCache.getTypeface(this, FontCache.PixelOperatorMono8));
        tvCoins.setText(user.getMoney().toString());

        /**
         * Init listView
         */
        ListView listViewBirds = (ListView) findViewById(R.id.listViewShop);
        ListViewItemBirdAdapter birdAdapter = new ListViewItemBirdAdapter(this, R.layout.listview_item_bird, birds);
        listViewBirds.setAdapter(birdAdapter);
    }
}
