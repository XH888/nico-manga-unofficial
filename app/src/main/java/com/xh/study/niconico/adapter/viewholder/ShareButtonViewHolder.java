package com.xh.study.niconico.adapter.viewholder;

import android.view.View;
import android.widget.TextView;

import com.xh.study.niconico.R;
import com.xh.study.niconico.entity.renderable.ShareButtonBean;
import com.xh.study.niconico.widget.ExtraTextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.alexrs.recyclerviewrenderers.viewholder.RenderViewHolder;

/**
 * Created by xh on 1/18/17.
 */

public class ShareButtonViewHolder extends RenderViewHolder<ShareButtonBean>{

    @BindView(R.id.txt_share)TextView shareText;
    //ExtraTextView shareText;


    public ShareButtonViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }

    @Override
    public void onBindView(ShareButtonBean item) {
        if(item.getShareText()!=null)
            shareText.setText(item.getShareText());
    }
}
