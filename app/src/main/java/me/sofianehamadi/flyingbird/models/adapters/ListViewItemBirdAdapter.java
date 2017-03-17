package me.sofianehamadi.flyingbird.models.adapters;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import me.sofianehamadi.flyingbird.R;
import me.sofianehamadi.flyingbird.ShopActivity;
import me.sofianehamadi.flyingbird.common.AudioGame;
import me.sofianehamadi.flyingbird.common.FontCache;
import me.sofianehamadi.flyingbird.database.Database;
import me.sofianehamadi.flyingbird.models.Bird;
import me.sofianehamadi.flyingbird.models.adapters.holders.ViewHolderItemBird;

/**
 * Created by MISTERSOFT on 26/02/2017.
 */

/**
 * List view adapter for birds
 */
public class ListViewItemBirdAdapter extends ArrayAdapter<Bird> {
    /**
     * Context
     */
    private final Context context;
    /**
     * List of birds
     */
    private List<Bird> birds;
    /**
     * AudioGame
     */
    private AudioGame audio;

    /**
     * Contructor
     * @param context
     * @param resource
     * @param objects
     */
    public ListViewItemBirdAdapter(Context context, int resource, List<Bird> objects) {
        super(context, resource, objects);
        this.context = context;
        this.birds = objects;
        this.audio = new AudioGame(context);
    }

    @NonNull
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolderItemBird viewHolderItemBird;
        if (convertView == null) {
            LayoutInflater inflater = ((Activity) this.context).getLayoutInflater();
            convertView = inflater.inflate(R.layout.listview_item_bird, parent, false);

            viewHolderItemBird = new ViewHolderItemBird();

            viewHolderItemBird.birdImage = (ImageView) convertView.findViewById(R.id.listItemBirdImage);
            viewHolderItemBird.birdName = (TextView) convertView.findViewById(R.id.listItemBirdName);
            viewHolderItemBird.birdPrice = (TextView) convertView.findViewById(R.id.listItemBirdPrice);

            viewHolderItemBird.buyBird = (Button) convertView.findViewById(R.id.shopBuyButton);
            viewHolderItemBird.equipBird = (Button) convertView.findViewById(R.id.shopEquipButton);

            convertView.setTag(viewHolderItemBird);
        }
        else {
            viewHolderItemBird = (ViewHolderItemBird) convertView.getTag();
        }

        Bird item = getItem(position);
        if (item != null) {
            int resID = context.getResources().getIdentifier(birds.get(position).getResourceName(), "drawable", "me.sofianehamadi.flyingbird");
            viewHolderItemBird.birdImage.setImageResource(resID);

            viewHolderItemBird.birdName.setText(birds.get(position).getName());
            viewHolderItemBird.birdName.setTextSize(FontCache.TINY);
            viewHolderItemBird.birdName.setTypeface(FontCache.getTypeface(context, FontCache.PixelOperatorMono8));

            viewHolderItemBird.birdPrice.setText(birds.get(position).getPrice().toString() + " Coins");
            viewHolderItemBird.birdPrice.setTextSize(FontCache.SMALL);
            viewHolderItemBird.birdPrice.setTypeface(FontCache.getTypeface(context, FontCache.PixelOperatorMono8));

            viewHolderItemBird.buyBird.setEnabled(!birds.get(position).isBought());
            if (birds.get(position).isBought()) {
                viewHolderItemBird.buyBird.setText(R.string.bought);
            }
            else {
                viewHolderItemBird.buyBird.setText(R.string.buy);
            }
            viewHolderItemBird.buyBird.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // If user have enough money
                    if (((ShopActivity) context).user.getMoney() - birds.get(position).getPrice() > 0) {
                        audio.playFX(AudioGame.BUY_BIRD);

                        // update bird
                        Bird bird = birds.get(position);
                        bird.setBought(true);
                        Database.getInstance(context).updateBird(bird, false);
                        birds = Database.getInstance(context).getAllBirds();

                        // update user money
                        ((ShopActivity) context).user.minus(birds.get(position).getPrice());
                        Database.getInstance(context).updateUser(((ShopActivity) context).user);
                        ((ShopActivity) context).updateMoney();
                        notifyDataSetChanged();
                    }
                }
            });

            if (!birds.get(position).isBought()) {
                viewHolderItemBird.equipBird.setEnabled(false);
            }
            else {
                viewHolderItemBird.equipBird.setEnabled(!birds.get(position).isEquiped());
            }

            if (birds.get(position).isEquiped()) {
                viewHolderItemBird.equipBird.setText(R.string.equiped);
            }
            else {
                viewHolderItemBird.equipBird.setText(R.string.equip);
            }

            viewHolderItemBird.equipBird.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bird bird = birds.get(position);
                    bird.setEquiped(true);
                    Database.getInstance(context).updateBird(bird, true);
                    birds = Database.getInstance(context).getAllBirds();
                    notifyDataSetChanged();
                }
            });
        }

        return convertView;
    }
}
