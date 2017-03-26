package com.xh.study.niconico.adapter.viewholder;

import android.graphics.Color;
import android.view.View;
import android.widget.FrameLayout;

import com.xh.study.niconico.R;
import com.xh.study.niconico.entity.OfficialDetailInfo;
import com.xh.study.niconico.entity.base.ColorBean;
import com.xh.study.niconico.entity.renderable.PickupItemBean;
import com.xh.study.niconico.entity.renderable.PickupPrimaryItem;
import com.xh.study.niconico.widget.RatioImageView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.alexrs.recyclerviewrenderers.viewholder.RenderViewHolder;

/**
 * Created by xh on 1/17/17.
 */

public class OfficialLogoViewHolder extends RenderViewHolder<OfficialDetailInfo.DataBean.ResultBean> {

    @BindView(R.id.layout_logo)
    FrameLayout logoLayout;

    @BindView(R.id.img_logo)
    RatioImageView logoImg;

    public OfficialLogoViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);

    }

    @Override
    public void onBindView(OfficialDetailInfo.DataBean.ResultBean item) {

        ColorBean color = item.getBackground_color();
        logoLayout.setBackgroundColor(Color.rgb( color.getR(),color.getG(),color.getB() ));
        logoImg.setTag(item.getLogo_url());

    }
}
