package com.xh.study.niconico.entity;

import com.xh.study.niconico.entity.base.StatusMetaBean;
import com.xh.study.niconico.entity.renderable.AttentionItemBean;

import java.util.List;

/**
 * Created by xh on 12/28/16.
 * https://ssl.seiga.nicovideo.jp/api/v1/app/manga/contents/attention?limit=20
 *
 */

public class AttentionInfo {
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
        private List<AttentionItemBean> result;

        public List<AttentionItemBean> getResult() {
            return result;
        }

        public void setResult(List<AttentionItemBean> result) {
            this.result = result;
        }


    }
}
