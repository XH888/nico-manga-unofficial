package com.xh.study.niconico.adapter.render;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.xh.study.niconico.adapter.viewholder.OfficialLogoViewHolder;

import me.alexrs.recyclerviewrenderers.renderer.Renderer;
import me.alexrs.recyclerviewrenderers.viewholder.RenderViewHolder;

/**
 * Created by xh on 1/17/17.
 */

public class OfficialLogoRender extends Renderer{

    public OfficialLogoRender(int id) {
        super(id);
    }

    @Override
    public RenderViewHolder onCreateViewHolder(ViewGroup viewGroup, int id) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(id,viewGroup,false);
        return new OfficialLogoViewHolder(itemView);
    }
}
