package com.xh.study.niconico.adapter.render;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xh.study.niconico.adapter.viewholder.EpisodeSingleFrameViewHolder;

import me.alexrs.recyclerviewrenderers.renderer.Renderer;
import me.alexrs.recyclerviewrenderers.viewholder.RenderViewHolder;

/**
 * Created by xh on 2/4/17.
 */

public class EpisodeSingleFrameRender extends Renderer {

    public EpisodeSingleFrameRender(int id) {
        super(id);
    }

    @Override
    public RenderViewHolder onCreateViewHolder(ViewGroup viewGroup, int id) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(id,viewGroup,false);
        return new EpisodeSingleFrameViewHolder(itemView);
    }
}
