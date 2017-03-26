package com.xh.study.niconico.entity.renderable;

import com.xh.study.niconico.R;
import com.xh.study.niconico.entity.base.MetaBean;

import me.alexrs.recyclerviewrenderers.interfaces.Renderable;

/**
 * Created by xh on 1/3/17.
 */

public class AttentionItemBean implements Renderable {
    private int id;

    private String reason;

    private MetaBean meta;

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public MetaBean getMeta() {
        return meta;
    }

    public void setMeta(MetaBean meta) {
        this.meta = meta;
    }

    @Override
    public int getRenderableId() {
        return R.layout.renderer_item_attention;
    }
}
