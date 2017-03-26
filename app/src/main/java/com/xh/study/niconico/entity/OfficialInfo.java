package com.xh.study.niconico.entity;

import com.xh.study.niconico.entity.base.MetaBean;
import com.xh.study.niconico.entity.base.StatusMetaBean;
import com.xh.study.niconico.entity.renderable.OfficialItemBean;

import java.util.List;

/**
 * Created by xh on 12/28/16.
 * https://ssl.seiga.nicovideo.jp/api/v1/app/manga/officials
 */

public class OfficialInfo {
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
        private List<OfficialItemBean> result;

        public List<OfficialItemBean> getResult() {
            return result;
        }

        public void setResult(List<OfficialItemBean> result) {
            this.result = result;
        }

    }
}
