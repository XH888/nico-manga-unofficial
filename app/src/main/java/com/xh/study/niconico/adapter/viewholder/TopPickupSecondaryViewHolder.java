package com.xh.study.niconico.adapter.viewholder;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.TextView;

import com.xh.study.niconico.R;
import com.xh.study.niconico.entity.renderable.PickupItemBean;
import com.xh.study.niconico.entity.renderable.PickupSecondaryItem;
import com.xh.study.niconico.widget.ExtraTextView;
import com.xh.study.niconico.widget.RatioImageView;

import me.alexrs.recyclerviewrenderers.viewholder.RenderViewHolder;

/**
 * Created by xh on 1/3/17.
 */

public class TopPickupSecondaryViewHolder extends RenderViewHolder<PickupSecondaryItem> {

    CardView cardView;
    RatioImageView ratioImageView;
    ExtraTextView label_txtView;
    TextView msg_txtView;
    TextView title_txtView;
    TextView author_txtView;

    public TopPickupSecondaryViewHolder(View itemView) {
        super(itemView);
        cardView = (CardView) itemView.findViewById(R.id.card_pickup);
        ratioImageView = (RatioImageView) itemView.findViewById(R.id.img_thumbnail);
        label_txtView = (ExtraTextView) itemView.findViewById(R.id.txt_label);
        msg_txtView = (TextView) itemView.findViewById(R.id.txt_message);
        title_txtView = (TextView) itemView.findViewById(R.id.txt_title);
        author_txtView = (TextView) itemView.findViewById(R.id.txt_author_name);
    }

    @Override
    public void onBindView(PickupSecondaryItem item) {
        if(item.getLabel()==null) {
            label_txtView.setVisibility(View.GONE);
        }else{
            label_txtView.setVisibility(View.VISIBLE);
            label_txtView.setTextColor(Color.rgb(item.getLabel_color().getR(), item.getLabel_color().getG(), item.getLabel_color().getB()));
            label_txtView.setText(item.getLabel());
            label_txtView.toDrawGradient();
        }

        if(item.getObject().getMeta()==null){
            title_txtView.setVisibility(View.GONE);
            author_txtView.setVisibility(View.GONE);
        }else{
            title_txtView.setVisibility(View.VISIBLE);
            author_txtView.setVisibility(View.VISIBLE);
            title_txtView.setText(item.getObject().getMeta().getTitle());
            author_txtView.setText(item.getObject().getMeta().getDisplay_author_name());
        }

        cardView.setCardBackgroundColor(Color.rgb(item.getBackground_color().getR(),item.getBackground_color().getG(),item.getBackground_color().getB()));

        msg_txtView.setText(item.getMessage());
        ratioImageView.setTag(item.getThumbnail_url());
    }
}
