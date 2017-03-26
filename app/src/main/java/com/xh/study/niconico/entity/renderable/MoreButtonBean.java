package com.xh.study.niconico.entity.renderable;

import com.xh.study.niconico.R;

import me.alexrs.recyclerviewrenderers.interfaces.Renderable;

/**
 * Created by xh on 1/18/17.
 */

public class MoreButtonBean implements Renderable{

    private String moreTxt;

    public MoreButtonBean(String moreTxt){
        this.moreTxt = moreTxt;
    }

    public String getMoreTxt() {
        return moreTxt;
    }

    @Override
    public int getRenderableId() {
        return R.layout.renderer_btn_more;
    }
}
