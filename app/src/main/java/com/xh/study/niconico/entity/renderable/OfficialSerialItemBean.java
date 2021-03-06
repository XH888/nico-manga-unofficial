package com.xh.study.niconico.entity.renderable;

import android.os.Parcel;
import android.support.annotation.LayoutRes;

import com.xh.study.niconico.R;

import me.alexrs.recyclerviewrenderers.interfaces.Renderable;

/**
 * Created by xh on 1/17/17.
 */

public class OfficialSerialItemBean extends OfficialItemBean implements Renderable {

    protected OfficialSerialItemBean(Parcel in) {
        super(in);
    }
    
    private String square_thumbnail_url;
    private String vertical_thumbnail_url;


    public String getSquare_thumbnail_url() {
        return square_thumbnail_url;
    }

    public void setSquare_thumbnail_url(String square_thumbnail_url) {
        this.square_thumbnail_url = square_thumbnail_url;
    }

    public String getVertical_thumbnail_url() {
        return vertical_thumbnail_url;
    }

    public void setVertical_thumbnail_url(String vertical_thumbnail_url) {
        this.vertical_thumbnail_url = vertical_thumbnail_url;
    }

    @Override
    public int getRenderableId() {
        return R.layout.renderer_official_serial_content;
    }
}
