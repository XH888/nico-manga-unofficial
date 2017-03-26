package com.xh.study.niconico.entity;

import com.xh.study.niconico.entity.base.StatusMetaBean;
import com.xh.study.niconico.entity.renderable.RankItemBean;

import java.util.List;

/**
 * Created by xh on 12/28/16.
 * https://ssl.seiga.nicovideo.jp/api/v1/app/manga/contents/ranking?limit=20&offset=20&span=daily&category=all 
 */

public class RankingInfo {

    private StatusMetaBean meta;
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
        private List<RankItemBean> result;
        private ExtraBean extra;

        public List<RankItemBean> getResult() {
            return result;
        }

        public void setResult(List<RankItemBean> result) {
            this.result = result;
        }

        public ExtraBean getExtra() {
            return extra;
        }

        public void setExtra(ExtraBean extra) {
            this.extra = extra;
        }

        public static class ExtraBean{
            private int total;

            public int getTotal() {
                return total;
            }

            public void setTotal(int total) {
                this.total = total;
            }
        }

    }
}
