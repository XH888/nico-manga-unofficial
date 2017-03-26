package com.xh.study.niconico.adapter.viewholder;

import android.app.Service;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.lsjwzh.widget.materialloadingprogressbar.CircleProgressBar;
import com.sothree.slidinguppanel.SlidingUpPanelLayout;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.xh.study.niconico.R;
import com.xh.study.niconico.entity.renderable.FrameBean;
import com.xh.study.niconico.widget.CommentView;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.alexrs.recyclerviewrenderers.viewholder.RenderViewHolder;

/**
 * Created by xh on 2/4/17.
 */

public class EpisodeSingleFrameViewHolder extends RenderViewHolder<FrameBean> {

    @BindView(R.id.img_page)
    ImageView pageImg;

    @BindView(R.id.comment)
    CommentView commentView;

    @BindView(R.id.progress)
    CircleProgressBar progress;

    public EpisodeSingleFrameViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void onBindView(FrameBean item) {
        int width = ((WindowManager)getContext().getSystemService(Service.WINDOW_SERVICE)).getDefaultDisplay().getWidth();
        int height = width*item.getMeta().getHeight()/item.getMeta().getWidth();
//
//        pageImg.setMaxHeight(height);
//        pageImg.setMaxWidth(width);

        itemView.setLayoutParams(new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,height));

        Picasso.with(getContext())
            .load(item.getMeta().getSource_url()).centerInside().fit()
            .into(pageImg, new Callback() {
                @Override
                public void onSuccess() {
                    progress.setVisibility(View.GONE);
                }

                @Override
                public void onError() {
                }
            });
    }
}
