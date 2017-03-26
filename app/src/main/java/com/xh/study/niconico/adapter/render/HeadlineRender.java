package com.xh.study.niconico.adapter.render;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xh.study.niconico.R;
import com.xh.study.niconico.adapter.viewholder.FeatureHeadlineViewHolder;
import com.xh.study.niconico.adapter.viewholder.HeadlineViewHolder;

import me.alexrs.recyclerviewrenderers.renderer.Renderer;
import me.alexrs.recyclerviewrenderers.viewholder.RenderViewHolder;

/**
 * Created by xh on 1/4/17.
 */

public class HeadlineRender extends Renderer {

    public HeadlineRender(int id) {
        super(id);
    }

    @Override
    public RenderViewHolder onCreateViewHolder(ViewGroup viewGroup, int id) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(id, viewGroup, false);
        if(id == R.layout.renderer_feature_headline)
            return new FeatureHeadlineViewHolder(itemView);
        else
            return new HeadlineViewHolder(itemView);
    }
}
