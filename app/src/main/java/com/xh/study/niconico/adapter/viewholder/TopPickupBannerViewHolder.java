package com.xh.study.niconico.adapter.viewholder;

import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.TextView;

import com.xh.study.niconico.R;
import com.xh.study.niconico.entity.base.ColorBean;
import com.xh.study.niconico.entity.renderable.PickupItemBean;
import com.xh.study.niconico.entity.renderable.PickupPrimaryItem;
import com.xh.study.niconico.widget.ExtraTextView;
import com.xh.study.niconico.widget.RatioImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.alexrs.recyclerviewrenderers.viewholder.RenderViewHolder;

/**
 * Created by xh on 1/3/17.
 */

public class TopPickupBannerViewHolder extends RenderViewHolder<PickupPrimaryItem> {

    @BindView(R.id.card_pickup)
    CardView cardView;

    @BindView(R.id.img_thumbnail)
    RatioImageView ratioImageView;

    @BindView(R.id.txt_label)
    ExtraTextView label_txtView;

    @BindView(R.id.txt_message)
    TextView msg_txtView;


    public TopPickupBannerViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }

    @Override
    public void onBindView(PickupPrimaryItem item) {
        if(item.getLabel() == null) {
            label_txtView.setVisibility(View.GONE);
        }else{
            label_txtView.setVisibility(View.VISIBLE);
            label_txtView.setTextColor(Color.rgb(item.getLabel_color().getR(), item.getLabel_color().getG(), item.getLabel_color().getB()));
            label_txtView.setText(item.getLabel());
            label_txtView.toDrawGradient();
        }

        ColorBean colorBean = item.getBackground_color();

        //设置背景CardView颜色
        if(colorBean!=null)
            cardView.setCardBackgroundColor(Color.rgb(colorBean.getR(),colorBean.getG(),colorBean.getB()));

        colorBean = item.getContainerColor();

        //设置背景CardView外边距颜色
        if(colorBean!=null)
            itemView.setBackgroundColor(Color.rgb(colorBean.getR(),colorBean.getG(),colorBean.getB()));

        msg_txtView.setText(item.getMessage());
        ratioImageView.setTag(item.getThumbnail_url());
    }
}
