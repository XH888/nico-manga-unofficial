package com.xh.study.niconico.adapter.viewholder;

import android.view.View;
import android.widget.TextView;

import com.xh.study.niconico.R;
import com.xh.study.niconico.entity.renderable.OfficialSerialItemBean;
import com.xh.study.niconico.widget.ExtraTextView;
import com.xh.study.niconico.widget.RatioImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.alexrs.recyclerviewrenderers.viewholder.RenderViewHolder;

/**
 * Created by xh on 1/18/17.
 */

public class OfficialSerialViewHolder extends RenderViewHolder<OfficialSerialItemBean> {

    @BindView(R.id.img_thumbnail)
    RatioImageView thumbnailImg;

    @BindView(R.id.txt_title)
    TextView titleTxt;

    @BindView(R.id.txt_author_name)
    TextView authorTxt;

    @BindView(R.id.txt_updated)
    ExtraTextView updatedTxt;

    public OfficialSerialViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }

    @Override
    public void onBindView(OfficialSerialItemBean item) {
        thumbnailImg.setTag(item.getVertical_thumbnail_url());
        titleTxt.setText(item.getMeta().getTitle());
        authorTxt.setText(item.getMeta().getDisplay_author_name());
        updatedTxt.toDrawData(item.getMeta().getUpdated_at() * 1000);
    }
}
