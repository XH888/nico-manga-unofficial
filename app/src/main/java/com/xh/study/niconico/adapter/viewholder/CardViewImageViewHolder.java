package com.xh.study.niconico.adapter.viewholder;

import android.view.View;

import com.xh.study.niconico.R;
import com.xh.study.niconico.entity.renderable.CardViewImageBean;
import com.xh.study.niconico.widget.RatioImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.alexrs.recyclerviewrenderers.viewholder.RenderViewHolder;

/**
 * Created by xh on 1/18/17.
 */

public class CardViewImageViewHolder extends RenderViewHolder<CardViewImageBean> {

    @BindView(R.id.img_create)
    RatioImageView createImg;

    public CardViewImageViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }

    @Override
    public void onBindView(CardViewImageBean item) {
        createImg.setTag(item.getImgRes());
    }
}
