package com.xh.study.niconico.entity.renderable;

import com.xh.study.niconico.entity.base.ColorBean;
import com.xh.study.niconico.entity.base.ObjectBean;



public abstract class PickupItemBean  {
    private String type;
    private String size;
    private String label;
    private ColorBean label_color;
    private ColorBean background_color;
    private String message;
    private ObjectBean object;
    private String thumbnail_url;


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public ColorBean getLabel_color() {
        return label_color;
    }

    public void setLabel_color(ColorBean label_color) {
        this.label_color = label_color;
    }

    public ColorBean getBackground_color() {
        return background_color;
    }

    public void setBackground_color(ColorBean background_color) {
        this.background_color = background_color;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ObjectBean getObject() {
        return object;
    }

    public void setObject(ObjectBean object) {
        this.object = object;
    }

    public String getThumbnail_url() {
        return thumbnail_url;
    }

    public void setThumbnail_url(String thumbnail_url) {
        this.thumbnail_url = thumbnail_url;
    }


}