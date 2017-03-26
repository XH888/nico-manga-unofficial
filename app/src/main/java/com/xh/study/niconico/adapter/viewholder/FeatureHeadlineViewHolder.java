package com.xh.study.niconico.adapter.viewholder;

import android.view.View;
import android.widget.TextView;

import com.xh.study.niconico.R;
import com.xh.study.niconico.entity.renderable.FeatureItemBean;
import com.xh.study.niconico.widget.ExtraTextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.alexrs.recyclerviewrenderers.viewholder.RenderViewHolder;

/**
 * Created by xh on 1/4/17.
 */

public class FeatureHeadlineViewHolder extends RenderViewHolder<FeatureItemBean> {

    @BindView(R.id.txt_title)
    ExtraTextView textView;

    public FeatureHeadlineViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }

    @Override
    public void onBindView(FeatureItemBean item) {
        textView.setText(item.getTitle());
        textView.setDrawablePosition(0);
        textView.setDrawable(getContext().getDrawable(R.drawable.image_pickup_title));
        textView.setDraw();
    }
}
