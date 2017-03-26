package com.xh.study.niconico.adapter.viewholder;

import android.view.View;
import android.widget.TextView;

import com.xh.study.niconico.R;
import com.xh.study.niconico.entity.renderable.OfficialMagazineBean;
import com.xh.study.niconico.entity.renderable.OfficialTrialItemBean;
import com.xh.study.niconico.widget.RatioImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.alexrs.recyclerviewrenderers.viewholder.RenderViewHolder;

/**
 * Created by xh on 1/18/17.
 */

public class OfficialMagazineViewHolder extends RenderViewHolder<OfficialMagazineBean> {

    @BindView(R.id.img_magazine_thumbnail)
    RatioImageView thumbnailImg;

    @BindView(R.id.txt_name)
    TextView nameTxt;

    @BindView(R.id.txt_description)
    TextView descTxt;

    public OfficialMagazineViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }

    @Override
    public void onBindView(OfficialMagazineBean item) {
        thumbnailImg.setTag(item.getThumbnail_url());
        nameTxt.setText(item.getName());
        descTxt.setText(item.getDescription());
    }
}
