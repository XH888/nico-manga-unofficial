package com.xh.study.niconico.adapter.viewholder;

import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.xh.study.niconico.R;
import com.xh.study.niconico.callback.ItemListener;
import com.xh.study.niconico.entity.renderable.FeatureItemBean;
import com.xh.study.niconico.widget.RatioImageView;

import me.alexrs.recyclerviewrenderers.viewholder.RenderViewHolder;

/**
 * Created by xh on 1/3/17.
 */

public class TopContentItemViewHolder extends RenderViewHolder<FeatureItemBean.Content> {
    RatioImageView img_ratioImageView;

    TextView title_textView,author_txtView;

    public TopContentItemViewHolder(View itemView) {
        super(itemView);
        img_ratioImageView = (RatioImageView) itemView.findViewById(R.id.img_thumbnail);
        title_textView = (TextView) itemView.findViewById(R.id.txt_title);
        author_txtView = (TextView) itemView.findViewById(R.id.txt_author_name);

        itemView.findViewById(R.id.card_pickup).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getItem().getItemListener().onClick(getAdapterPosition(),v);
            }
        });
    }

    @Override
    public void onBindView(FeatureItemBean.Content item) {
        title_textView.setText(item.getMeta().getTitle());
        author_txtView.setText(item.getMeta().getDisplay_author_name());
        img_ratioImageView.setTag(item.getMeta().getThumbnail_url());


    }

}
