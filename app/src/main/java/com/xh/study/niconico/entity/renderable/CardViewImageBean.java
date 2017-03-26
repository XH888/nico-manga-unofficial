package com.xh.study.niconico.entity.renderable;

import android.support.annotation.LayoutRes;

import com.xh.study.niconico.R;
import me.alexrs.recyclerviewrenderers.interfaces.Renderable;

/**
 * Created by xh on 1/18/17.
 */

public class CardViewImageBean implements Renderable{

    @LayoutRes
    private int imgRes;

    public CardViewImageBean(int imgRes){
        this.imgRes = imgRes;
    }


    public int getImgRes() {
        return imgRes;
    }

    public void setImgRes(int imgRes) {
        this.imgRes = imgRes;
    }

    @Override
    public int getRenderableId() {
        return R.layout.renderer_how_to_create;
    }
}
