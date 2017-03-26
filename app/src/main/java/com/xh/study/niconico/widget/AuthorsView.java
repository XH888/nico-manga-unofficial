package com.xh.study.niconico.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.squareup.picasso.Picasso;
import com.xh.study.niconico.R;
import com.xh.study.niconico.entity.base.MetaBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xh on 1/23/17.
 */

public class AuthorsView extends ListView{
    public AuthorsView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public AuthorsView(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr,0);
    }

    BaseAdapter adapter ;

    List<MetaBean.AuthorBean> authors = new ArrayList<>();

    public void setAuthors(List<MetaBean.AuthorBean> authors) {
        this.authors.addAll(authors);
        adapter.notifyDataSetChanged();
    }

    public AuthorsView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);

        adapter = new BaseAdapter() {
            @Override
            public int getCount() {
                return authors.size();
            }

            @Override
            public Object getItem(int position) {
                return authors.get(position);
            }

            @Override
            public long getItemId(int position) {
                return position;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                if(!authors.isEmpty()) {
                    MetaBean.AuthorBean authorBean = authors.get(position);
                    if (convertView == null)
                        convertView = LayoutInflater.from(context).inflate(R.layout.widget_author,parent, false);
                    ExtraTextView authorNameView = (ExtraTextView) convertView.findViewById(R.id.author_name);
                    ImageView authorIconView = (ImageView) convertView.findViewById(R.id.author_icon);
                    authorNameView.setText(authorBean.getName());
                    Picasso.with(context)
                            .load(authorBean.getThumbnail_url())
                            .config(Bitmap.Config.RGB_565)
                            .into(authorIconView);
                }
                return convertView;
            }
        };
        setAdapter(adapter);
    }


    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return true;
    }
}
