package com.xh.study.niconico.entity;

import com.xh.study.niconico.R;
import com.xh.study.niconico.entity.base.ColorBean;
import com.xh.study.niconico.entity.base.StatusMetaBean;
import com.xh.study.niconico.entity.renderable.OfficialMagazineBean;
import com.xh.study.niconico.entity.renderable.OfficialItemBean;
import com.xh.study.niconico.entity.renderable.PickupPrimaryItem;
import com.xh.study.niconico.entity.renderable.OfficialSerialItemBean;
import com.xh.study.niconico.entity.renderable.OfficialTrialItemBean;

import java.util.List;

import me.alexrs.recyclerviewrenderers.interfaces.Renderable;

/**
 * Created by xh on 1/15/17.
 */

public class OfficialDetailInfo {

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
        private ResultBean result ;

        public ResultBean getResult() {
            return result;
        }

        public void setResult(ResultBean result) {
            this.result = result;
        }

        public static class ResultBean implements Renderable{
            private String logo_url;
            private ColorBean background_color;
            private ContentBean contents;
            private List<PickupPrimaryItem> pickups;
            private List<OfficialMagazineBean> magazines;

            public String getLogo_url() {
                return logo_url;
            }

            public void setLogo_url(String logo_url) {
                this.logo_url = logo_url;
            }

            public ColorBean getBackground_color() {
                return background_color;
            }

            public void setBackground_color(ColorBean background_color) {
                this.background_color = background_color;
            }

            public ContentBean getContents() {
                return contents;
            }

            public void setContents(ContentBean contents) {
                this.contents = contents;
            }

            public List< PickupPrimaryItem> getPickups() {
                return pickups;
            }

            public void setPickups(List<PickupPrimaryItem> pickups) {
                this.pickups = pickups;
            }

            public List<OfficialMagazineBean> getMagazines() {
                return magazines;
            }

            public void setMagazines(List<OfficialMagazineBean> magazines) {
                this.magazines = magazines;
            }

            @Override
            public int getRenderableId() {
                return R.layout.renderer_logo_image;
            }

            public class ContentBean {

                private List<OfficialSerialItemBean> serial;
                private List<OfficialTrialItemBean> trial;
                private List<OfficialItemBean> concluded;

                public List<OfficialSerialItemBean> getSerial() {
                    return serial;
                }

                public void setSerial(List<OfficialSerialItemBean> serial) {
                    this.serial = serial;
                }

                public List<OfficialTrialItemBean> getTrial() {
                    return trial;
                }

                public void setTrial(List<OfficialTrialItemBean> trial) {
                    this.trial = trial;
                }

                public List<OfficialItemBean> getConcluded() {
                    return concluded;
                }

                public void setConcluded(List<OfficialItemBean> concluded) {
                    this.concluded = concluded;
                }
            }

        }
    }
}
