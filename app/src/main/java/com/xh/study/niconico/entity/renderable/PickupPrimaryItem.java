package com.xh.study.niconico.entity.renderable;

import com.xh.study.niconico.R;
import com.xh.study.niconico.entity.base.ColorBean;

import me.alexrs.recyclerviewrenderers.interfaces.Renderable;

/**
 * Created by xh on 1/6/17.
 */

public class PickupPrimaryItem extends PickupItemBean implements Renderable {

    private ColorBean containerColor;

    public ColorBean getContainerColor() {
        return containerColor;
    }

    public void setContainerColor(ColorBean containerColor) {
        this.containerColor = containerColor;
    }

    @Override
    public int getRenderableId() {
        if(getSize().equals("banner"))
            return R.layout.renderer_item_top_primary_v;
        return R.layout.renderer_item_top_primary_h;
    }
}
