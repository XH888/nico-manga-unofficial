package com.xh.study.niconico.adapter.viewholder;

import android.view.View;
import android.widget.TextView;

import com.xh.study.niconico.R;
import com.xh.study.niconico.entity.renderable.MoreButtonBean;
import com.xh.study.niconico.entity.renderable.ShareButtonBean;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.alexrs.recyclerviewrenderers.viewholder.RenderViewHolder;

/**
 * Created by xh on 1/18/17.
 */

public class MoreButtonViewHolder extends RenderViewHolder<MoreButtonBean>{

    @BindView(R.id.btn_more)
    TextView moreText;


    public MoreButtonViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }

    @Override
    public void onBindView(MoreButtonBean item) {
        if(item.getMoreTxt() != null)
            moreText.setText(item.getMoreTxt());
    }
}
