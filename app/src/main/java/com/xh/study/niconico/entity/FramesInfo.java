package com.xh.study.niconico.entity;

import com.xh.study.niconico.entity.base.ObjectBean;
import com.xh.study.niconico.entity.base.StatusMetaBean;
import com.xh.study.niconico.entity.renderable.FrameBean;

import java.util.List;

/**
 * Created by xh on 1/24/17.
 */

public class FramesInfo {

    private StatusMetaBean meta ;

    private DataBean data;

    public StatusMetaBean getMeta() {
        return meta;
    }

    public void setMeta(StatusMetaBean meta) {
        this.meta = meta;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean{
        private List<FrameBean> result;

        public List<FrameBean> getResult() {
            return result;
        }

        public void setResult(List<FrameBean> result) {
            this.result = result;
        }
    }
}
