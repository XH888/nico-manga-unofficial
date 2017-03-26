package com.xh.study.niconico.entity.renderable;

import com.xh.study.niconico.R;

import me.alexrs.recyclerviewrenderers.interfaces.Renderable;

/**
 * Created by xh on 1/6/17.
 */

public class RankItemBean implements Renderable{
    private int id ;
    private int rank;
    private com.xh.study.niconico.entity.base.MetaBean meta;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public com.xh.study.niconico.entity.base.MetaBean getMeta() {
        return meta;
    }

    public void setMeta(com.xh.study.niconico.entity.base.MetaBean meta) {
        this.meta = meta;
    }

    @Override
    public int getRenderableId() {
        return R.layout.adapter_item_ranking;
    }
}