package com.xh.study.niconico.adapter.viewholder;

import android.view.View;
import android.widget.TextView;

import com.xh.study.niconico.R;
import com.xh.study.niconico.entity.renderable.FeatureItemBean;
import com.xh.study.niconico.entity.renderable.HeadLineItem;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.alexrs.recyclerviewrenderers.viewholder.RenderViewHolder;

/**
 * Created by xh on 1/4/17.
 */

public class HeadlineViewHolder extends RenderViewHolder<HeadLineItem> {

    @BindView(R.id.txt_title)
    TextView textView;

    public HeadlineViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }

    @Override
    public void onBindView(HeadLineItem item) {
        textView.setText(item.getTitle());
    }

}
