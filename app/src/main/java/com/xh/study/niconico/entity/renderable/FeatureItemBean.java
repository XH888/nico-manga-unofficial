package com.xh.study.niconico.entity.renderable;

import android.os.Parcel;

import com.xh.study.niconico.R;
import com.xh.study.niconico.callback.ItemListener;
import com.xh.study.niconico.entity.base.MetaBean;
import com.xh.study.niconico.entity.base.ObjectBean;

import java.util.List;

import me.alexrs.recyclerviewrenderers.interfaces.Renderable;

/**
 * Created by xh on 1/3/17.
 */
public class FeatureItemBean implements Renderable {
    private String title ;
    private String discription;
    private List<Content> contents;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDiscription() {
        return discription;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }

    public List<Content> getContents() {
        return contents;
    }

    public void setContents(List<Content> contents) {
        this.contents = contents;
    }


    @Override
    public int getRenderableId() {
        return R.layout.renderer_feature_headline;
    }

    public static class Content extends ObjectBean implements Renderable{

        protected Content(Parcel in) {
            super(in);
        }

        @Override
        public int getRenderableId() {
            return R.layout.renderer_item_feature;
        }
    }
}