package com.xh.study.niconico.entity.renderable;

import com.xh.study.niconico.R;

import me.alexrs.recyclerviewrenderers.interfaces.Renderable;

/**
 * Created by xh on 1/17/17.
 */

public class OfficialMagazineBean implements Renderable{
    private String name;
    private String description;
    private String thumbnail_url;
    private String link_url;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getThumbnail_url() {
        return thumbnail_url;
    }

    public void setThumbnail_url(String thumbnail_url) {
        this.thumbnail_url = thumbnail_url;
    }

    public String getLink_url() {
        return link_url;
    }

    public void setLink_url(String link_url) {
        this.link_url = link_url;
    }

    @Override
    public int getRenderableId() {
        return R.layout.renderer_official_magazine;
    }
}
