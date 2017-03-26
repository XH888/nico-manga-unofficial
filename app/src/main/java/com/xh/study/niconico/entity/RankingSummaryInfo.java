package com.xh.study.niconico.entity;

import com.xh.study.niconico.entity.base.StatusMetaBean;
import com.xh.study.niconico.entity.renderable.RankItemBean;

import java.util.List;

/**
 * Created by xh on 12/28/16.
 * https://ssl.seiga.nicovideo.jp/api/v1/app/manga/contents/ranking/summary?limit=3&span=hourly
 */

public class RankingSummaryInfo {

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
        private ResultBean result;


        public ResultBean getResult() {
            return result;
        }

        public void setResult(ResultBean result) {
            this.result = result;
        }



        public static class ResultBean{
            private List<RankItemBean> shonen;
            private List<RankItemBean> seinen;
            private List<RankItemBean> shojo;
            private List<RankItemBean> yonkoma;
            private List<RankItemBean> other;
            private List<RankItemBean> fan;
            private List<RankItemBean> all;


            public List<RankItemBean> getShonen() {
                return shonen;
            }

            public void setShonen(List<RankItemBean> shonen) {
                this.shonen = shonen;
            }

            public List<RankItemBean> getSeinen() {
                return seinen;
            }

            public void setSeinen(List<RankItemBean> seinen) {
                this.seinen = seinen;
            }

            public List<RankItemBean> getShojo() {
                return shojo;
            }

            public void setShojo(List<RankItemBean> shojo) {
                this.shojo = shojo;
            }

            public List<RankItemBean> getYonkoma() {
                return yonkoma;
            }

            public void setYonkoma(List<RankItemBean> yonkoma) {
                this.yonkoma = yonkoma;
            }

            public List<RankItemBean> getOther() {
                return other;
            }

            public void setOther(List<RankItemBean> other) {
                this.other = other;
            }

            public List<RankItemBean> getFan() {
                return fan;
            }

            public void setFan(List<RankItemBean> fan) {
                this.fan = fan;
            }

            public List<RankItemBean> getAll() {
                return all;
            }

            public void setAll(List<RankItemBean> all) {
                this.all = all;
            }


        }
    }
}
