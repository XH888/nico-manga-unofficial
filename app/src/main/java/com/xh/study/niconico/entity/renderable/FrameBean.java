package com.xh.study.niconico.entity.renderable;

import android.os.Parcel;

import com.xh.study.niconico.R;
import com.xh.study.niconico.entity.FramesInfo;
import com.xh.study.niconico.entity.base.ObjectBean;

import me.alexrs.recyclerviewrenderers.interfaces.Renderable;

/**
 * Created by xh on 2/4/17.
 */

public class FrameBean extends ObjectBean implements Renderable {

    protected FrameBean(Parcel in) {
        super(in);
    }

    @Override
    public int getRenderableId() {
        return R.layout.renderer_scroll_single_frame;
    }
}
