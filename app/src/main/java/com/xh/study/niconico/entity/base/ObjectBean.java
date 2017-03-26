package com.xh.study.niconico.entity.base;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import com.xh.study.niconico.callback.ItemListener;

/**
 * Created by xh on 12/22/16.
 */

public class ObjectBean implements Parcelable,Comparable<ObjectBean>{
    private String link;
    private int id;
    private MetaBean meta;

    public ObjectBean() {}

    //按照ID的大小排序
    @Override
    public int compareTo(ObjectBean anotherObj) {
        return anotherObj.getId() - this.getId()   ;
    }

    //点击事件
    private ItemListener itemListener;


    //-------Parcelable Start---------
    protected ObjectBean(Parcel in) {
        link = in.readString();
        id = in.readInt();
        meta = in.readParcelable(MetaBean.class.getClassLoader());
    }

    public static final Creator<ObjectBean> CREATOR = new Creator<ObjectBean>() {
        @Override
        public ObjectBean createFromParcel(Parcel in) {
            return new ObjectBean(in);
        }

        @Override
        public ObjectBean[] newArray(int size) {
            return new ObjectBean[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(link);
        dest.writeInt(id);
        dest.writeParcelable(meta, flags);
    }
    //-------Parcelable End ---------


    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public MetaBean getMeta() {
        return meta;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public void setMeta(MetaBean meta) {
        this.meta = meta;
    }

    public ItemListener getItemListener() {
        return itemListener;
    }

    public void setItemListener(ItemListener itemListener) {
        this.itemListener = itemListener;
    }


}
