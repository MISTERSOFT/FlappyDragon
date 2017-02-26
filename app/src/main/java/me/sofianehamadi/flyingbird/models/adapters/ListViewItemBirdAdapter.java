package me.sofianehamadi.flyingbird.models.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import me.sofianehamadi.flyingbird.R;
import me.sofianehamadi.flyingbird.common.FontCache;
import me.sofianehamadi.flyingbird.models.Bird;
import me.sofianehamadi.flyingbird.models.adapters.holders.ViewHolderItemBird;

/**
 * Created by MISTERSOFT on 26/02/2017.
 */

public class ListViewItemBirdAdapter extends ArrayAdapter<Bird> {
    private final Context context;
    private final List<Bird> birds;

    public ListViewItemBirdAdapter(Context context, int resource, List<Bird> objects) {
        super(context, resource, objects);
        this.context = context;
        this.birds = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolderItemBird viewHolderItemBird;
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
            int resID = this.context.getResources().getIdentifier(birds.get(position).getResourceName(), "drawable", "me.sofianehamadi.flyingbird");

            viewHolderItemBird.birdImage.setImageResource(resID);

            viewHolderItemBird.birdName.setText(birds.get(position).getName());
            viewHolderItemBird.birdName.setTextSize(FontCache.TINY);
            viewHolderItemBird.birdName.setTypeface(FontCache.getTypeface(context, FontCache.PixelOperatorMono8));

            viewHolderItemBird.birdPrice.setText(birds.get(position).getPrice().toString() + " Coins");
            viewHolderItemBird.birdPrice.setTextSize(FontCache.SMALL);
            viewHolderItemBird.birdPrice.setTypeface(FontCache.getTypeface(context, FontCache.PixelOperatorMono8));

            viewHolderItemBird.buyBird.setEnabled(!birds.get(position).isBought());

            viewHolderItemBird.equipBird.setEnabled(!birds.get(position).isEquiped());
        }

        return convertView;
    }
}
