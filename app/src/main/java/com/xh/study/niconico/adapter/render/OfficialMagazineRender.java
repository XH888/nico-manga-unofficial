package com.xh.study.niconico.adapter.render;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xh.study.niconico.adapter.viewholder.OfficialMagazineViewHolder;
import com.xh.study.niconico.adapter.viewholder.OfficialTrialViewHolder;

import me.alexrs.recyclerviewrenderers.renderer.Renderer;
import me.alexrs.recyclerviewrenderers.viewholder.RenderViewHolder;

/**
 * Created by xh on 1/18/17.
 */

public class OfficialMagazineRender extends Renderer {

    public OfficialMagazineRender(int id) {
        super(id);
    }

    @Override
    public RenderViewHolder onCreateViewHolder(ViewGroup viewGroup, int id) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(id,viewGroup,false);
        return new OfficialMagazineViewHolder(itemView);
    }
}
