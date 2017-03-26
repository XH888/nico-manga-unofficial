package com.xh.study.niconico.entity.renderable;

import com.xh.study.niconico.R;

import me.alexrs.recyclerviewrenderers.interfaces.Renderable;

/**
 * Created by xh on 1/6/17.
 */

public class HeadLineItem implements Renderable {

    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public int getRenderableId() {
        return R.layout.renderer_headline;
    }
}
