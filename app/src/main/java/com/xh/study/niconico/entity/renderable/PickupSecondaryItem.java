package com.xh.study.niconico.entity.renderable;

import com.xh.study.niconico.R;

import me.alexrs.recyclerviewrenderers.interfaces.Renderable;

/**
 * Created by xh on 1/6/17.
 */

public class PickupSecondaryItem extends PickupItemBean implements Renderable{
    @Override
    public int getRenderableId() {
        return R.layout.renderer_item_top_secondary;
    }
}
