package com.xh.study.niconico.entity;

import com.xh.study.niconico.entity.base.StatusMetaBean;
import com.xh.study.niconico.entity.renderable.FeatureItemBean;
import com.xh.study.niconico.entity.renderable.PickupItemBean;
import com.xh.study.niconico.entity.renderable.PickupPrimaryItem;
import com.xh.study.niconico.entity.renderable.PickupSecondaryItem;

import java.util.List;

/**
 * Created by xh on 12/21/16.
 * Home - Top
 *
 * https://ssl.seiga.nicovideo.jp/api/v1/app/manga/aggregate/homescreen
 */

public class TopInfo {

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
        private ResultBean result;

        public ResultBean getResult() {
            return result;
        }

        public void setResult(ResultBean result) {
            this.result = result;
        }

        public static class ResultBean{
            private PickUpBean pickup;
            private List<FeatureItemBean> features;

            public List<FeatureItemBean> getFeatures() {
                return features;
            }

            public void setFeatures(List<FeatureItemBean> features) {
                this.features = features;
            }

            public PickUpBean getPickup() {
                return pickup;
            }

            public void setPickup(PickUpBean pickup) {
                this.pickup = pickup;
            }

            public static class PickUpBean{

                private List<PickupPrimaryItem> primary;
                private List<PickupSecondaryItem> secondary;

                public List<PickupPrimaryItem> getPrimary() {
                    return primary;
                }

                public void setPrimary(List<PickupPrimaryItem> primary) {
                    this.primary = primary;
                }

                public List<PickupSecondaryItem> getSecondary() {
                    return secondary;
                }

                public void setSecondary(List<PickupSecondaryItem> secondary) {
                    this.secondary = secondary;
                }
            }

        }
    }

}
