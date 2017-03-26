package com.xh.study.niconico.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.snappydb.DB;
import com.snappydb.DBFactory;
import com.snappydb.SnappydbException;
import com.xh.study.niconico.R;
import com.xh.study.niconico.entity.base.ObjectBean;
import com.xh.study.niconico.util.DBUtil;
import com.xh.study.niconico.util.NumberUtil;
import com.xh.study.niconico.widget.ExtraTextView;
import com.xh.study.niconico.widget.RatioImageView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by xh on 1/21/17.
 * 章节列表信息适配器
 */
public class EpisodeAdapter extends BaseAdapter {

    private List<ObjectBean> list;

    public EpisodeAdapter(List<ObjectBean> list) {
        this.list = list==null?new ArrayList<>():list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_episode, parent, false);
        }
        ObjectBean objectBean = list.get(position);
        TextView counterTxt = (TextView) convertView.findViewById(R.id.txt_counter_frame); //页数
        TextView titleTxt = (TextView) convertView.findViewById(R.id.txt_title);
        ExtraTextView commentCounterTxt = (ExtraTextView) convertView.findViewById(R.id.txt_counter_comment);
        ExtraTextView viewCounterTxt = (ExtraTextView) convertView.findViewById(R.id.txt_counter_view);
        RatioImageView ratioImageView = (RatioImageView) convertView.findViewById(R.id.img_episode_thumbnail);
        ExtraTextView updatedTxt = (ExtraTextView) convertView.findViewById(R.id.txt_updated);
        ImageView readMarkImg = (ImageView) convertView.findViewById(R.id.img_last_read_mark);

        ratioImageView.setTag(objectBean.getMeta().getThumbnail_url());
        counterTxt.setText(NumberUtil.converString(objectBean.getMeta().getCounter().getFrame()));
        titleTxt.setText(objectBean.getMeta().getTitle());
        updatedTxt.toDrawData(objectBean.getMeta().getUpdated_at() * 1000);
        commentCounterTxt.setText(NumberUtil.converString(objectBean.getMeta().getCounter().getComment()));
        viewCounterTxt.setText(NumberUtil.converString(objectBean.getMeta().getCounter().getView()));
        if(DBUtil.findReadedEpisode(convertView.getContext(),objectBean.getId())==null){
            readMarkImg.setVisibility(View.GONE);
        }else {
            readMarkImg.setVisibility(View.VISIBLE);
        }
        return convertView;
    }
}