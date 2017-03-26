package com.xh.study.niconico.entity.renderable;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.LayoutRes;

import com.xh.study.niconico.entity.base.MetaBean;

import me.alexrs.recyclerviewrenderers.interfaces.Renderable;

/**
 * Created by xh on 1/9/17.
 */

public class OfficialItemBean implements Parcelable {

    private int id;
    private MetaBean meta;

    //-------Parcelable Start---------
    protected OfficialItemBean(Parcel in) {
        id = in.readInt();
        meta = in.readParcelable(MetaBean.class.getClassLoader());
    }

    public static final Creator<OfficialItemBean> CREATOR = new Creator<OfficialItemBean>() {
        @Override
        public OfficialItemBean createFromParcel(Parcel in) {
            return new OfficialItemBean(in);
        }

        @Override
        public OfficialItemBean[] newArray(int size) {
            return new OfficialItemBean[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeParcelable(meta, flags);
    }
    //-------Parcelable End---------


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public MetaBean getMeta() {
        return meta;
    }

    public void setMeta(MetaBean meta) {
        this.meta = meta;
    }



}
