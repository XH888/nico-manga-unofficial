package com.xh.study.niconico.adapter.viewholder;

import android.view.View;
import android.widget.TextView;

import com.xh.study.niconico.R;
import com.xh.study.niconico.entity.renderable.AttentionItemBean;
import com.xh.study.niconico.widget.RatioImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.alexrs.recyclerviewrenderers.viewholder.RenderViewHolder;

/**
 * Created by xh on 1/3/17.
 */

public class AttentionItemViewHolder extends RenderViewHolder<AttentionItemBean> {

    @BindView(R.id.img_thumbnail)
    RatioImageView thumbnailImageView;

    @BindView(R.id.txt_title)
    TextView titleTxt;

    @BindView(R.id.txt_author_name)
    TextView authorTxt;

    @BindView(R.id.txt_attention_reason)
    TextView reasonTxt;

    public AttentionItemViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }

    @Override
    public void onBindView(AttentionItemBean item) {
        titleTxt.setText(item.getMeta().getTitle());
        authorTxt.setText(item.getMeta().getDisplay_author_name());

        reasonTxt.setText(item.getReason());
        thumbnailImageView.setTag(item.getMeta().getThumbnail_url());
    }

}
