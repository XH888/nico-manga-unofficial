package com.xh.study.niconico.adapter;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.xh.study.niconico.R;
import com.xh.study.niconico.entity.renderable.RankItemBean;
import com.xh.study.niconico.widget.ContentLayout;


import java.util.List;

/**
 * Created by xh on 1/8/17.
 */

public class RankingSummaryItemAdapter extends BaseAdapter{

    private SparseArray<List<RankItemBean>> list;

    private Context context;

    public RankingSummaryItemAdapter(Context context,SparseArray<List<RankItemBean>> list){
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(list.keyAt(position));
    }

    @Override
    public long getItemId(int position) {
        return list.keyAt(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View itemView;
        if(convertView!=null){
            itemView = convertView;
        }else {
            itemView = LayoutInflater.from(context).inflate(R.layout.adapter_ranking_summary,parent,false);
        }
        int titleRes = list.keyAt(position);
        List<RankItemBean> itemBeen = list.get(list.keyAt(position));
        ((TextView)itemView.findViewById(R.id.txt_headline)).setText(context.getResources().getString(titleRes));
        ((TextView)itemView.findViewById(R.id.txt_ranking_link)).setText(context.getResources().getString(R.string.rank_link_to,context.getResources().getString(titleRes)));
        ContentLayout contentLayout = (ContentLayout) itemView.findViewById(R.id.layout_content);
        contentLayout.clearItemList();
        contentLayout.addRankItemList(itemBeen);

        return itemView;
    }


}
