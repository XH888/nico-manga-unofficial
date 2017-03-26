package com.xh.study.niconico.entity.renderable;

import com.xh.study.niconico.R;

import me.alexrs.recyclerviewrenderers.interfaces.Renderable;

/**
 * Created by xh on 1/18/17.
 */

public class ShareButtonBean implements Renderable {

    private String shareText;

    public ShareButtonBean(String shareText){
        this.shareText = shareText;
    }

    public String getShareText() {
        return shareText;
    }

    @Override
    public int getRenderableId() {
        return R.layout.renderer_share;
    }
}
